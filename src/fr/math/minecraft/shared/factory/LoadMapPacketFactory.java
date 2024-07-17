/**
*  Minecraft Clone Math edition : Cybersecurity - A serious game to learn network and cybersecurity
*  Copyright (C) 2024 MeAndTheHomies (Math)
*
*  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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

        int nextSerie;
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
        node.set("mapData", blocksNode);
        node.put("serie", serie);
        node.put("nextSerie", nextSerie);
        node.put("type", "LOADING_MAP");
        node.put("uuid", "server");
        return node;
    }
}
