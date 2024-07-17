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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.Game;
import org.joml.Vector3i;
@Deprecated
public class ChunkACKPacket extends ClientPacket {

    private final Vector3i chunkPosition;

    public ChunkACKPacket(Vector3i chunkPosition) {
        this.chunkPosition = chunkPosition;
    }

    @Override
    public String toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("type", "CHUNK_REQUEST_ACK");
        node.put("uuid", Game.getInstance().getPlayer().getUuid());
        node.put("x", chunkPosition.x);
        node.put("y", chunkPosition.y);
        node.put("z", chunkPosition.z);

        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getResponse() {
        return null;
    }
}
