package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.ServerConfiguration;
import fr.math.minecraft.server.TimeoutHandler;
import fr.math.minecraft.server.api.Server;
import fr.math.minecraft.server.websockets.MinecraftWebSocketServer;
import fr.math.minecraft.server.websockets.ServerStatus;
import fr.math.minecraft.server.api.MinecraftApiFacade;
import fr.math.minecraft.shared.ChatColor;
import fr.math.minecraft.shared.Utils;
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

        String uuid = null;
        String skinUrl = null;
        ServerConfiguration configuration = ServerConfiguration.getInstance();

        try {
            String token = packetData.get("token").asText();
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode tokenNode = mapper.createObjectNode();
            tokenNode.put("token", token);
            HttpResponse response = HttpUtils.POST(configuration.getAuthEndpoint() + "/auth/validtoken", tokenNode);
            JsonNode responseData = mapper.readTree(response.getResponse().toString());
            JsonNode playerData = responseData.get("user");
            uuid = playerData.get("id").asText();
            skinUrl = playerData.get("skin").get("link").asText();
            String playerName = playerData.get("name").asText();

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

            byte[] buffer = mapper.writeValueAsString(node).getBytes(StandardCharsets.UTF_8);

            synchronized (server.getClients()) {
                server.getClients().put(uuid, client);
                try {
                    BufferedImage skin = Utils.loadBase64Skin(skinUrl);
                    client.setSkin(skin);
                    ImageIO.write(skin, "png", new File("skins/" + uuid + ".png"));
                    logger.info("Le skin du joueur" + playerName + " (" + uuid + ") a été sauvegardé avec succès.");
                } catch (IOException e) {
                    buffer = "SERVER_ERROR".getBytes(StandardCharsets.UTF_8);
                    server.sendPacket(new DatagramPacket(buffer, buffer.length, address, clientPort));
                    logger.error(e.getMessage());
                    return;
                }
            }

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, clientPort);
            logger.info(playerName + " (" + uuid + ") a rejoint le serveur ! (" + clients.size() + "/???)");
            server.getPluginManager().invokePlayerJoin(server.getClients().size());
            server.announceMessage(playerName + " a rejoint le serveur.", ChatColor.YELLOW);

            if (!lastActivities.containsKey(uuid)) {
                lastActivities.put(uuid, System.currentTimeMillis());
                TimeoutHandler handler = new TimeoutHandler(server, uuid);
                handler.start();
            }

            server.sendPacket(packet);

            MinecraftWebSocketServer webSocketServer = server.getWebSocketServer();
            Server serverData = server.getServerData();
            if (serverData != null) {
                ServerStatus serverStatus = new ServerStatus(serverData.getIp(), server.getClients().size(), server.getChatMessages().size());
                webSocketServer.broadcastStatus(serverStatus);
                MinecraftApiFacade api = new MinecraftApiFacade();
                api.updateServer(serverData, serverStatus);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            byte[] buffer = "INVALID_TOKEN".getBytes(StandardCharsets.UTF_8);
            server.sendPacket(new DatagramPacket(buffer, buffer.length, address, clientPort));
        }
    }
}
