package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.world.PlacedBlock;
import fr.math.minecraft.shared.world.World;

import java.net.InetAddress;

public class LoadingMapHandler extends PacketHandler{

    public final int maxBlockPacket = 500;
    public final int blockInMap = 90;

    public LoadingMapHandler(JsonNode packetData, InetAddress address, int clientPort) {
        super(packetData, address, clientPort);
    }

    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode blocksNode = mapper.createArrayNode();
        ObjectNode node = mapper.createObjectNode();
        MinecraftServer server = MinecraftServer.getInstance();
        String uuid = packetData.get("uuid").asText();

        World world = MinecraftServer.getInstance().getWorld();

        if(server.getClients().get(uuid) == null) {
            return;
        }

        int clientLoadMapState = server.getClients().get(uuid).getLoadMapState();
        int blockToSend;
        //Combien de bloc je dois envoyer
        if(maxBlockPacket + clientLoadMapState < blockInMap) {
            blockToSend = maxBlockPacket + clientLoadMapState;
        } else {
            blockToSend = blockInMap - clientLoadMapState;
        }

        for (int i = clientLoadMapState; i < blockToSend; i++) {
            PlacedBlock placedBlock = world.getLoadMapData().get(i);
            blocksNode.add(placedBlock.toJSONObject());
        }




    }
}
