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
import org.apache.log4j.Logger;

public abstract class Command {

    private final static Logger logger = LoggerUtility.getServerLogger(Command.class, LogType.TXT);
    private String name;
    private String descpription;
    private Team team;
    private Node tree;
    private int maxOptions;

    public Command(String name, String descpription, Team team) {
        this.name = name;
        this.descpription = descpription;
        this.team = team;
        this.tree = new Node("");
        this.maxOptions = 0;
    }

    public int incrementOption(int currentOption) {
        if (currentOption < maxOptions) {
            currentOption++;
        } else {
            currentOption = 0;
        }
        return currentOption;
    }

    public void run(String[] message, String sender, Client client, MinecraftServer server) {}
    public Node initTree(MinecraftServer server) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescpription() {
        return descpription;
    }

    public void setDescpription(String descpription) {
        this.descpription = descpription;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Node getTree() {
        return tree;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }

    public int getMaxOptions() {
        return maxOptions;
    }

    public void setMaxOptions(int maxOptions) {
        this.maxOptions = maxOptions;
    }
}
