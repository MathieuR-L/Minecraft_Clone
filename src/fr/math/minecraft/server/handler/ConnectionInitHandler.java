package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.TimeoutHandler;
import fr.math.minecraft.shared.ChatColor;
import fr.math.minecraft.shared.network.HttpResponse;
import fr.math.minecraft.shared.network.HttpUtils;
import fr.math.minecraft.shared.world.World;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

public class ConnectionInitHandler extends PacketHandler implements Runnable {

    private final static Logger logger = LoggerUtility.getClientLogger(ConnectionInitHandler.class, LogType.TXT);

    public ConnectionInitHandler(JsonNode packetData, InetAddress address, int clientPort) {
        super(packetData, address, clientPort);
    }

    @Override
    public void run() {
        MinecraftServer server = MinecraftServer.getInstance();
        Map<String, Client> clients = server.getClients();
        Map<String, Long> lastActivities = server.getLastActivities();
        String playerName = packetData.get("playerName").asText();
        String token = packetData.get("token").asText();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode tokenNode = mapper.createObjectNode();
        tokenNode.put("token", token);
        String uuid = null;

        try {
            HttpResponse response = HttpUtils.POST("http://localhost:3001/auth/validtoken", tokenNode);
            JsonNode responseData = mapper.readTree(response.getResponse().toString());
            JsonNode playerData = responseData.get("user");
            uuid = playerData.get("id").asText();
        } catch (IOException e) {
            logger.error(e.getMessage());
            byte[] buffer = "INVALID_TOKEN".getBytes(StandardCharsets.UTF_8);
            server.sendPacket(new DatagramPacket(buffer, buffer.length, address, clientPort));
            return;
        }

        if (uuid == null) {
            return;
        }

        /*
        for (Client client : clients.values()) {
            if (client.getName().equalsIgnoreCase(playerName)) {
                byte[] buffer = "USERNAME_NOT_AVAILABLE".getBytes(StandardCharsets.UTF_8);
                return new DatagramPacket(buffer, buffer.length, address, clientPort);
            }
        }
         */

        ObjectNode node = mapper.createObjectNode();
        Client client = new Client(uuid, playerName, address, clientPort);
        World world = server.getWorld();

        client.getPosition().x = world.getSpawnPosition().x;
        client.getPosition().y = world.getSpawnPosition().y;
        client.getPosition().z = world.getSpawnPosition().z;

        node.put("uuid", uuid);
        node.put("spawnX", world.getSpawnPosition().x);
        node.put("spawnY", world.getSpawnPosition().y);
        node.put("spawnZ", world.getSpawnPosition().z);
        node.put("seed", world.getSeed());
        node.set("worldData", world.toJSONObject());

        try {
            byte[] buffer = mapper.writeValueAsString(node).getBytes(StandardCharsets.UTF_8);

            synchronized (server.getClients()) {
                server.getClients().put(uuid, client);
                byte[] skinBytes = Base64.getDecoder().decode(packetData.get("skin").asText());
                try {
                    BufferedImage skin = ImageIO.read(new ByteArrayInputStream(skinBytes));
                    client.setSkin(skin);
                    ImageIO.write(skin, "png", new File("skins/" + uuid + ".png"));
                    logger.info("Le skin du joueur" + playerName + " (" + uuid + ") a été sauvegardé avec succès.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, clientPort);
            logger.info(playerName + " (" + uuid + ") a rejoint le serveur ! (" + clients.size() + "/???)");
            server.announceMessage(playerName + " a rejoint le serveur.", ChatColor.YELLOW);

            if (!lastActivities.containsKey(uuid)) {
                lastActivities.put(uuid, System.currentTimeMillis());
                TimeoutHandler handler = new TimeoutHandler(server, uuid);
                handler.start();
            }

            server.sendPacket(packet);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
