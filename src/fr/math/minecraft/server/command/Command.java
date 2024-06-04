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

    public Command(String name, String descpription, Team team) {
        this.name = name;
        this.descpription = descpription;
        this.team = team;
        this.tree = new Node("");
    }

    public void run(String[] message, String sender, Client client, MinecraftServer server) {}

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
}
