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
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.command.Command;
import fr.math.minecraft.shared.ChatMessage;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.util.Arrays;

public class CommandHandler extends PacketHandler{

    private final static Logger logger = LoggerUtility.getServerLogger(CommandHandler.class, LogType.TXT);

    private String message, sender;
    private Client client;

    public CommandHandler(JsonNode packetData, InetAddress address, int clienPort) {
        super(packetData, address, clienPort);
    }

    public void run() {
        MinecraftServer server = MinecraftServer.getInstance();
        String message = packetData.get("content").asText();
        String sender = packetData.get("sender").asText();
        Client client = server.getClients().get(sender);

        if(client == null || !client.isActive()) {
            logger.warn("Le client (" + sender + ") est inconnue, sa commande était : " + message);
            return;
        }

        logger.trace("[Command] " + client.getName() + " : " + message);

        decodeCommand(message, sender, client, server);

        ChatMessage chatMessage = new ChatMessage(client.getUuid(), client.getName(), message);
        server.getChatMessages().add(chatMessage);

    }

    private void decodeCommand(String message, String sender, Client client, MinecraftServer server) {
        String[] words = message.split(" ");
        String command = words[0];
        if(server.getCommands().containsKey(command)) {
            Command commandRequested = server.getCommands().get(command);
            commandRequested.run(words, sender, client, server);
        } else {
            logger.warn("La commande :" + command + " n'existe pas");
        }
    }
}
