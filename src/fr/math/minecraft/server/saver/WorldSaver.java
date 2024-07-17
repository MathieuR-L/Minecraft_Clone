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

package fr.math.minecraft.server.saver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.shared.world.BreakedBlock;
import fr.math.minecraft.shared.world.PlacedBlock;
import fr.math.minecraft.shared.world.World;

import java.io.File;
import java.io.IOException;

public class WorldSaver {

    private final ObjectMapper mapper;
    private final static String SAVES_FOLDER = "saves/";

    public WorldSaver() {
        this.mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public void save(World world, Client client) throws IOException {

        JsonNode clientNode = client.toJSON();
        File file = new File(SAVES_FOLDER + "players/" + client.getUuid() + ".json");

        mapper.writeValue(file, clientNode);
    }

    public void save(World world) throws IOException {

        File file = new File(SAVES_FOLDER + "world.json");

        ObjectNode worldNode = mapper.createObjectNode();
        ArrayNode brokenBlocksArray = mapper.createArrayNode();
        ArrayNode placedBlocksArray = mapper.createArrayNode();

        for (BreakedBlock breakedBlock : world.getBrokenBlocks().values()) {
            brokenBlocksArray.add(breakedBlock.toJSONObject());
        }

        for (PlacedBlock placedBlock : world.getPlacedBlocks().values()) {
            brokenBlocksArray.add(placedBlock.toJSONObject());
        }

        worldNode.set("brokenBlocks", brokenBlocksArray);
        worldNode.set("placedBlocksArray", placedBlocksArray);

        mapper.writeValue(file, worldNode);
    }

}