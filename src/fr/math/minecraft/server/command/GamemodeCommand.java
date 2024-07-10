package fr.math.minecraft.server.command;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.ChatMessage;
import fr.math.minecraft.shared.network.GameMode;
import org.apache.log4j.Logger;

public class GamemodeCommand extends Command{

    private final static Logger logger = LoggerUtility.getServerLogger(GamemodeCommand.class, LogType.TXT);


    public GamemodeCommand(String name, String descpription, Team team) {
        super(name, descpription, team);
    }

    @Override
    public void run(String[] message, String sender, Client client, MinecraftServer server) {

        logger.debug("Je run 1 fois le [GAMEMODE]");

        if (message[0].equalsIgnoreCase("/gamemode")) {
            String gamemode = message[1];
            if ((gamemode.equalsIgnoreCase(String.valueOf(GameMode.SURVIVAL)) || gamemode.equals("0")) && client.getGameMode() != GameMode.SURVIVAL) {
                client.setGameMode(GameMode.SURVIVAL);
            } else if ((gamemode.equalsIgnoreCase(String.valueOf(GameMode.CREATIVE)) || gamemode.equals("1")) && client.getGameMode() != GameMode.CREATIVE) {
                client.setGameMode(GameMode.CREATIVE);
            }
        } else {
            return;
        }

        logger.trace("Le joueur " + client.getName() + " a changé son [GAMEMODE] en " + client.getGameMode().toString() + ".");

    }
}
