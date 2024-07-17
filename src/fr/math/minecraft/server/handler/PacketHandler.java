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

public abstract class PacketHandler {

    protected JsonNode packetData;
    protected InetAddress address;
    protected int clientPort;

    public PacketHandler(JsonNode packetData, InetAddress address, int clientPort) {
        this.packetData = packetData;
        this.address = address;
        this.clientPort = clientPort;
    }

}
