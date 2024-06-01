package fr.math.minecraft.server.handler;

import com.fasterxml.jackson.databind.JsonNode;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import org.apache.log4j.Logger;

import java.net.InetAddress;

public class CommandHandler extends PacketHandler{

    private final static Logger logger = LoggerUtility.getServerLogger(CommandHandler.class, LogType.TXT);

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
    }


}
