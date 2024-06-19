package fr.math.minecraft.shared.entity;

import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.pathfinding.AStar;
import fr.math.minecraft.server.pathfinding.Node;
import fr.math.minecraft.server.pathfinding.Pattern;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.network.Hitbox;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.World;
import org.apache.log4j.Logger;
import org.joml.*;
import org.joml.Math;

import java.util.ArrayList;
import java.util.UUID;

public class Villager extends Entity {

    private ArrayList<Vector3f> checkpoints;
    private int step;
    private final static Logger logger = LoggerUtility.getServerLogger(Villager.class, LogType.TXT);


    public Villager(String name) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.checkpoints = new ArrayList<>();
        this.speed = 0.5f;
        this.maxSpeed = 0.5f;
        this.step = 0;
        this.gravity = new Vector3f(0, 0 ,0);
        //this.setPosition(new Vector3f(0,10,0));
    }

    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera, this);
    }


    @Override
    public void update(World world) {
        logger.debug(this.getPosition());
        MinecraftServer server = MinecraftServer.getInstance();
        //Map<String, Client> clients = server.getClients();
        this.getVelocity().add(this.getGravity());

        this.setPatternUpdateCooldown(this.getPatternUpdateCooldown() + 1);

        if (this.getPatternUpdateCooldown() == GameConfiguration.TICK_PER_SECONDS) {
            int chunkX = (int) java.lang.Math.floor(this.getPosition().x / (double) Chunk.SIZE);
            int chunkY = (int) java.lang.Math.floor(this.getPosition().y / (double) Chunk.SIZE);
            int chunkZ = (int) java.lang.Math.floor(this.getPosition().z / (double) Chunk.SIZE);

            Vector3i chunkPosition = new Vector3i(chunkX, chunkY, chunkZ);

            if (!world.getGraph().getLoadedChunks().contains(chunkPosition)) {
                server.getPathfindingQueue().submit(() -> {
                    AStar.initGraph(world, this.getPosition());
                });
            }

            if (this.checkpoints != null && !this.checkpoints.isEmpty()) {
                Node startCheckpoint = new Node(new Vector3f(this.getPosition()));
                Node endCheckpoint = new Node(this.getCheckpoints().get(0));

                Vector2f entityPosition = new Vector2f(this.getPosition().x, this.getPosition().z);
                Vector2f direction = new Vector2f(endCheckpoint.getPosition().x, endCheckpoint.getPosition().y).sub(entityPosition);
                this.setPattern(new Pattern(AStar.shortestPath(world, startCheckpoint, endCheckpoint), startCheckpoint, endCheckpoint));
                this.setYaw((float) Math.toDegrees(Math.atan2(direction.y, direction.x)));

                if (endCheckpoint.getPosition().x >= (this.getPosition().x * 0.90f)&& endCheckpoint.getPosition().y >= (this.getPosition().z * 0.90f)) {
                    step++;
                    System.out.println("Step atteinte");
                }
            }
            this.setPatternUpdateCooldown(0);
        }

        Vector3f acceleration = new Vector3f(0, 0, 0);
        Vector3f front = new Vector3f();

        front.x = Math.cos(Math.toRadians(this.getYaw())) * Math.cos(Math.toRadians(this.getPitch()));
        front.y = Math.sin(Math.toRadians(0.0f));
        front.z = Math.sin(Math.toRadians(this.getYaw())) * Math.cos(Math.toRadians(this.getPitch()));

        front.normalize();

        if (this.getPattern() != null && !this.getPattern().getPath().isEmpty()) {
            Node currentNode = this.getPattern().getPath().get(0);
            //System.out.println("Pattern : " + this.getPattern().getPath());

            if (currentNode != null) {
                Vector2f entityPosition = new Vector2f(this.getPosition().x, this.getPosition().z);
                Vector2f direction = new Vector2f(currentNode.getPosition()).sub(entityPosition);
                if (direction.x != 0 && direction.y != 0) {
                    direction.normalize();
                }
                acceleration.add(direction.x, 0, direction.y);
            }
        }

        this.getVelocity().add(acceleration.mul(this.getSpeed()));

        if (new Vector3f(this.getVelocity().x, 0, this.getVelocity().z).length() > this.getMaxSpeed()) {
            Vector3f velocityNorm = new Vector3f(this.getVelocity().x, this.getVelocity().y, this.getVelocity().z);
            velocityNorm.normalize().mul(this.getMaxSpeed());
            this.getVelocity().x = velocityNorm.x;
            this.getVelocity().z = velocityNorm.z;
        }

        this.getPosition().x += this.getVelocity().x;
        handleCollisions(world, new Vector3f(this.getVelocity().x, 0, 0), true);

        this.getPosition().z += this.getVelocity().z;
        handleCollisions(world, new Vector3f(0, 0, this.getVelocity().z), true);

        this.getPosition().y += this.getVelocity().y;
        handleCollisions(world, new Vector3f(0, this.getVelocity().y, 0), true);
        //System.out.println("X : " + position.x + " Y : " + position.y + " Z : " + position.z);
        if (this.getPattern() != null && !this.getPattern().getPath().isEmpty()) {
            float distance = (float) new Vector2i((int) this.getPosition().x, (int) this.getPosition().z).distance(this.getPattern().getPath().get(0).getPosition());
            //System.out.println("distance : " + distance);
            if (distance <= 1.0) {
                this.setPattern(this.getPattern().next());
            }
        }
        //System.out.println("Vélocité Avant Mul: " + this.getVelocity());
        this.getVelocity().mul(0.95f);
        //System.out.println("Vélocité Apres Mul: " + this.getVelocity());
    }


    public ArrayList<Vector3f> getCheckpoints() {
        return checkpoints;
    }

}
