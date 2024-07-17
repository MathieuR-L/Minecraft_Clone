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

package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.InetAddress;

public class ChunkRequestHandler extends PacketHandler implements Runnable {

    public ChunkRequestHandler(JsonNode packetData, InetAddress address, int clientPort) {
        super(packetData, address, clientPort);
    }

    @Override
    public void run() {
        /*
        String uuid = packetData.get("uuid").asText();
        // ArrayNode knownChunks = (ArrayNode) packetData.get("knownChunks");

        MinecraftServer server = MinecraftServer.getInstance();
        World world = server.getWorld();

        Client client = server.getClients().get(uuid);

        if (client == null) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<ServerChunk> chunks = new ArrayList<>();

        int startX = (int) (client.getPosition().x / ServerChunk.SIZE - GameConfiguration.CHUNK_RENDER_DISTANCE);
        int startZ = (int) (client.getPosition().z / ServerChunk.SIZE - GameConfiguration.CHUNK_RENDER_DISTANCE);

        int endX = (int) (client.getPosition().x / ServerChunk.SIZE + GameConfiguration.CHUNK_RENDER_DISTANCE);
        int endZ = (int) (client.getPosition().z / ServerChunk.SIZE + GameConfiguration.CHUNK_RENDER_DISTANCE);

        for (int x = startX; x < endX; x++) {
           for (int y = -3; y < 10; y++) {
               for (int z = startZ; z < endZ; z++) {
                   Chunk chunk = world.getChunk(x, y, z);

                   if (chunk == null) {
                       chunk = new Chunk(x, y, z);
                       chunk.generate(world.getTerrainGenerator());
                       world.addChunk(chunk);
                   }

                   chunks.add(chunk);
               }
           }
        }


        for (ServerChunk chunk : chunks) {
            try {
                String chunkJSONData = mapper.writeValueAsString(chunk.toJSONObject());
                byte[] buffer = chunkJSONData.getBytes(StandardCharsets.UTF_8);

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, clientPort);

                server.sendPacket(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         */
    }
}
