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
        ArrayNode blocksNode = mapper.createArrayNode();
        ObjectNode node = mapper.createObjectNode();
        MinecraftServer server = MinecraftServer.getInstance();
        String uuid = packetData.get("uuid").asText();

        World world = server.getWorld();

        blockInMap = world.getLoadMapData().size();

        if(server.getClients().get(uuid) == null) {
            return;
        }

        int serie = 0;
        int blockToSend;

        node = LoadMapPacketFactory.createMapNode(world, serie, maxBlockPacket);
        try {
            byte[] buffer = mapper.writeValueAsString(node).getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, clientPort);
            server.sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
