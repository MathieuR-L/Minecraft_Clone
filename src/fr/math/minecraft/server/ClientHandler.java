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

package fr.math.minecraft.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientHandler extends Thread {

    private final ObjectMapper mapper;
    private final static Logger logger = LoggerUtility.getServerLogger(ClientHandler.class, LogType.TXT);;

    public ClientHandler() {
        this.mapper = new ObjectMapper();
    }

    public JsonNode parsePacket(DatagramPacket packet) {
        try {
            byte[] buffer = packet.getData();
            String message = new String(buffer, 0, buffer.length).trim();

            return mapper.readTree(message);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void run() {

    }
}
