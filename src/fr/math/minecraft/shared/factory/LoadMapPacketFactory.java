package fr.math.minecraft.shared.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.shared.world.PlacedBlock;
import fr.math.minecraft.shared.world.World;

public class LoadMapPacketFactory {

    public LoadMapPacketFactory() {}

    public static ObjectNode createMapNode(World world, int serie, int maxBlock) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode blocksNode = mapper.createArrayNode();

        int blockToSend, nextSerie;
        int blockInMap = world.getLoadMapData().size();

        for (int i = serie * maxBlock; i < (serie + 1)* maxBlock; i++) {
            if(i >= blockInMap) continue;
            PlacedBlock placedBlock = world.getLoadMapData().get(i);
            blocksNode.add(placedBlock.toJSONObject());
        }

        if((serie + 1) * maxBlock < blockInMap) {
            nextSerie = serie + 1;
        } else {
            nextSerie = -1;
        }
        ObjectNode node = mapper.createObjectNode();
        node.put("mapData", blocksNode);
        node.put("serie", serie);
        node.put("nextSerie", nextSerie);
        node.put("type", "LOADING_MAP");
        return node;
    }
}
