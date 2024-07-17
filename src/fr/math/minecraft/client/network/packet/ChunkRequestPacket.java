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

package fr.math.minecraft.client.network.packet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.shared.world.World;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;

public class ChunkRequestPacket extends ClientPacket {

    private final ObjectMapper mapper;
    private JsonNode chunkData;
    private final static Logger logger = LoggerUtility.getClientLogger(ChunkRequestPacket.class, LogType.TXT);


    public ChunkRequestPacket() {
        this.mapper = new ObjectMapper();
        this.chunkData = null;
    }

    @Override
    public String toJSON() {
        ObjectNode node = mapper.createObjectNode();
        ArrayNode knownChunks = mapper.createArrayNode();
        World world = Game.getInstance().getWorld();

        node.put("type", "CHUNK_REQUEST");
        node.put("uuid", Game.getInstance().getPlayer().getUuid());

        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public String getResponse() {
        return null;
    }

    public JsonNode getChunkData() {
        return this.chunkData;
    }
}