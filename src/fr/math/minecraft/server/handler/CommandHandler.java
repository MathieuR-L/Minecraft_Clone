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
