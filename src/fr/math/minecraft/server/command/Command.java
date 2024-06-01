package fr.math.minecraft.server.command;

public class Command {

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
