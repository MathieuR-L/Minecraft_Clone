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

package fr.math.minecraft.server.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.world.PlacedBlock;

import java.net.DatagramPacket;

public class PlacedBlockPayload {

    private final PlacedBlock placedBlock;

    public PlacedBlockPayload(PlacedBlock placedBlock) {
        this.placedBlock = placedBlock;
    }

    public void send(Client client) {
        ObjectMapper mapper = new ObjectMapper();
        MinecraftServer server = MinecraftServer.getInstance();
        ObjectNode blockNode = placedBlock.toJSONObject();
        blockNode.put("type", "PLACED_BLOCK_STATE");
        try {
            byte[] buffer = mapper.writeValueAsBytes(blockNode);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            packet.setAddress(client.getAddress());
            packet.setPort(client.getPort());

            server.sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
