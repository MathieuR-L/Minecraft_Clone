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
