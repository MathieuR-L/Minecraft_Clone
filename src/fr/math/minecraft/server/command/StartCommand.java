package fr.math.minecraft.server.command;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.shared.ChatMessage;
import org.apache.log4j.Logger;

public class StartCommand extends Command {

    private final static Logger logger = LoggerUtility.getServerLogger(StartCommand.class, LogType.TXT);

    public StartCommand(String name, String descpription, Team team) {
        super(name, descpription, team);
    }

    @Override
    public void run(String[] message, String sender, Client client, MinecraftServer server) {

        logger.debug("Je run 1 fois le [START]");

        if (message[0].equalsIgnoreCase("/start")) {
            ChatMessage startMessage = new ChatMessage(null, "Annonce", "La partie commence.");
            server.getChatMessages().add(startMessage);
        } else {
            return;
        }

        logger.trace("Le joueur " + client.getName() + " a [START] la partie");

    }

}
