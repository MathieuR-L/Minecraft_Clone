package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.factory.LoadMapPacketFactory;
import fr.math.minecraft.shared.world.PlacedBlock;
import fr.math.minecraft.shared.world.World;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class LoadingMapHandler extends PacketHandler{

    public final int maxBlockPacket = GameConfiguration.MAX_BLOCK_PACKET;
    public int blockInMap, nextSerie;

    public LoadingMapHandler(JsonNode packetData, InetAddress address, int clientPort) {
        super(packetData, address, clientPort);

    }

    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node;
        MinecraftServer server = MinecraftServer.getInstance();

        World world = server.getWorld();

        blockInMap = world.getLoadMapData().size();

        int serie = 0;

        node = LoadMapPacketFactory.createMapNode(world, serie, maxBlockPacket);
        node.put("spawnX", world.getSpawnPosition().x);
        node.put("spawnY", world.getSpawnPosition().y);
        node.put("spawnZ", world.getSpawnPosition().z);
        node.put("seed", world.getSeed());
        node.set("worldData", world.toJSONObject());
        try {
            byte[] buffer = mapper.writeValueAsString(node).getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, clientPort);
            server.sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
