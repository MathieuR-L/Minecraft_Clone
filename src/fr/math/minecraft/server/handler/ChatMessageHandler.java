package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.databind.JsonNode;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.api.MinecraftApiFacade;
import fr.math.minecraft.server.api.Server;
import fr.math.minecraft.server.api.payload.ChatMessagePayload;
import fr.math.minecraft.server.websockets.MinecraftWebSocketServer;
import fr.math.minecraft.shared.ChatMessage;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;

public class ChatMessageHandler extends PacketHandler {

    private final static Logger logger = LoggerUtility.getServerLogger(ChatMessageHandler.class, LogType.TXT);

    public ChatMessageHandler(JsonNode packetData, InetAddress address, int clientPort) {
        super(packetData, address, clientPort);
    }

    public void run() {
        MinecraftServer server = MinecraftServer.getInstance();
        String message = packetData.get("content").asText();
        String sender = packetData.get("sender").asText();
        Client client = server.getClients().get(sender);
        MinecraftWebSocketServer webSocketServer = server.getWebSocketServer();

        if (client == null || !client.isActive()) {
            logger.warn("Le client (" + sender + ") est inconnu, son message contient : " + message);
            return;
        }

        logger.info("[Chat] " + client.getName() + " : " + message);

        ChatMessage chatMessage = new ChatMessage(client.getUuid(), client.getName(), message);
        server.getChatMessages().add(chatMessage);
        MinecraftApiFacade api = new MinecraftApiFacade();
        ChatMessage serverChatMessage = api.postMessage(new ChatMessagePayload(message, client.getUuid()));

        logger.info("Message " + serverChatMessage);

        if (serverChatMessage != null) {
            webSocketServer.broadcastChatMessage(chatMessage);
        }

    }
}
