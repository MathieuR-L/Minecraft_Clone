package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.factory.LoadMapPacketFactory;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class LoadingMapACKHandler extends  PacketHandler implements Runnable{

    public LoadingMapACKHandler(JsonNode packetData, InetAddress address, int clientPort) {
        super(packetData, address, clientPort);
    }

    @Override
    public void run() {

        MinecraftServer server = MinecraftServer.getInstance();

        int serie = packetData.get("serie").asInt();
        serie++;

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = LoadMapPacketFactory.createMapNode(server.getWorld(), serie, GameConfiguration.MAX_BLOCK_PACKET);

        try {
            byte[] buffer = mapper.writeValueAsString(node).getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, clientPort);
            server.sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
