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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.manager.ClientManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ConnectionACKHandler extends PacketHandler implements Runnable {
    public ConnectionACKHandler(JsonNode packetData, InetAddress address, int clientPort) {
        super(packetData, address, clientPort);
    }

    @Override
    public void run() {
        MinecraftServer server = MinecraftServer.getInstance();
        String uuid = packetData.get("uuid").asText();
        Client connectedClient = server.getClients().get(uuid);

        if (connectedClient == null) {
            return;
        }

        connectedClient.setActive(true);

        synchronized (server.getClients()) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            ObjectNode clientJsonNode = connectedClient.toJSONWithSkin();
            node.put("type", "PLAYER_JOIN");
            node.set("playerData", clientJsonNode);
            try {
                String clientData = mapper.writeValueAsString(node);
                byte[] buffer = clientData.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                for (Client client : server.getClients().values()) {

                    if (uuid.equals(client.getUuid())) {
                        continue;
                    }

                    packet.setAddress(client.getAddress());
                    packet.setPort(client.getPort());
                    server.sendPacket(packet);
                    System.out.println("j'envoie un packet PLAYER_JOIN à " + client.getAddress() + ":" + client.getPort());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
