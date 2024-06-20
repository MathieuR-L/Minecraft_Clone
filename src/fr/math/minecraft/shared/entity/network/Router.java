package fr.math.minecraft.shared.entity.network;

import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.entity.Entity;
import fr.math.minecraft.shared.entity.EntityType;
import fr.math.minecraft.shared.entity.Villager;
import fr.math.minecraft.shared.network.Hitbox;
import fr.math.minecraft.shared.world.World;
import org.joml.Math;
import org.joml.Vector3f;

import java.awt.List;
import java.util.*;

public class Router extends Entity {

    private HashMap<Vector3f, ArrayList<Vector3f>> routingTable;
    private ArrayList<Vector3f> defaultRoute;
    private Client owner;
    private ArrayList<Villager> packetWaiting;
    private int level;

    public Router(String name) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.speed = 0.0f;
        this.maxSpeed = 0.0f;
        this.gravity = new Vector3f(0, 0 ,0);
        this.routingTable = new HashMap<>();
        this.defaultRoute = new ArrayList<>();
        this.packetWaiting = new ArrayList<>();
        this.owner = null;
        this.level = 0;
    }
    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera,this);
    }


    public ArrayList<Vector3f> setRoute(Vector3f destination){
        ArrayList<Vector3f> route = new ArrayList<>();

        route.add(this.getPosition());

        Vector3f zVector = new Vector3f(0, 0, destination.z);
        Vector3f xVector = new Vector3f(destination.x, 0, 0);

        int xSegment = (int)Math.floor(xVector.x / GameConfiguration.MAX_ASTAR_DISTANCE);
        float xReste = xVector.x % GameConfiguration.MAX_ASTAR_DISTANCE;

        for (int i = 1; i <= xSegment; i++) {
            Vector3f checkpoint = new Vector3f(i * GameConfiguration.MAX_ASTAR_DISTANCE, 0, 0);
            route.add(checkpoint);
        }
        if(xReste != 0) {
            Vector3f lastCheckpointX = new Vector3f(xReste + xSegment * GameConfiguration.MAX_ASTAR_DISTANCE, 0, 0);
            route.add(lastCheckpointX);
        }


        int zSegment = (int)Math.floor(zVector.z / GameConfiguration.MAX_ASTAR_DISTANCE);
        float zReste = zVector.z % GameConfiguration.MAX_ASTAR_DISTANCE;

        for (int i = 1; i <= zSegment; i++) {
            Vector3f checkpoint = new Vector3f(0, 0, i*GameConfiguration.MAX_ASTAR_DISTANCE);
            route.add(checkpoint);
        }
        if(zReste != 0) {
            Vector3f lastCheckpointZ = new Vector3f(0, 0, zReste + zSegment * GameConfiguration.MAX_ASTAR_DISTANCE);
            route.add(lastCheckpointZ);
        }


        return route;
    }

    public void giveCheckpoints(Villager villager) {
        Vector3f packetDestination = villager.getServiceRequested().getPosition();

        if(routingTable.containsKey(packetDestination)) {
            villager.addCheckpointList(routingTable.get(packetDestination));
        } else {
            villager.addCheckpointList(defaultRoute);
        }
    }

    @Override
    public void update(World world) {
        MinecraftServer server = MinecraftServer.getInstance();
        Map<String, Entity> entities = server.getWorld().getEntities();

        this.setPatternUpdateCooldown(this.getPatternUpdateCooldown() + 1);

        if(this.getPatternUpdateCooldown() == GameConfiguration.TICK_PER_SECONDS) {
            synchronized (server.getWorld().getEntities()) {

                for (Entity entity : entities.values()) {
                    if(entity instanceof Villager && entity.getPosition().distance(position) <= 10) {
                        Villager packet = (Villager)entity;
                        packetWaiting.add(packet);
                    }
                }

            }

            handlePackets();

        }
    }

    private void handlePackets() {
        if(packetWaiting.isEmpty()) return;
        Villager packet = packetWaiting.get(0);
        giveCheckpoints(packet);
        packetWaiting.remove(0);
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public HashMap<Vector3f, ArrayList<Vector3f>> getRoutingTable() {
        return routingTable;
    }
}
