package fr.math.minecraft.shared.entity;

import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.pathfinding.AStar;
import fr.math.minecraft.server.pathfinding.Node;
import fr.math.minecraft.server.pathfinding.Pattern;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.network.Hitbox;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.World;
import org.joml.*;
import org.joml.Math;

import java.util.ArrayList;
import java.util.UUID;

public class Villager extends Entity {

    private ArrayList<Node> checkpoints;
    private int step;

    public Villager(String name) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.checkpoints = new ArrayList<>();
        this.speed = 0.03f;
        this.maxSpeed = 0.5f;
        this.step = 0;
    }

    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera, this);
    }

    @Override
    public void update(World world) {

        MinecraftServer server = MinecraftServer.getInstance();
        //Map<String, Client> clients = server.getClients();
        velocity.add(gravity);

        this.setPatternUpdateCooldown(this.getPatternUpdateCooldown() + 1);

        if (this.getPatternUpdateCooldown() == GameConfiguration.TICK_PER_SECONDS) {

            int chunkX = (int) java.lang.Math.floor(position.x / (double) Chunk.SIZE);
            int chunkY = (int) java.lang.Math.floor(position.y / (double) Chunk.SIZE);
            int chunkZ = (int) java.lang.Math.floor(position.z / (double) Chunk.SIZE);

            Vector3i chunkPosition = new Vector3i(chunkX, chunkY, chunkZ);

            if (!world.getGraph().getLoadedChunks().contains(chunkPosition)) {
                server.getPathfindingQueue().submit(() -> {
                    AStar.initGraph(world, position);
                });
            }

            Node startCheckpoint = new Node(new Vector3f(position));
            Node endCheckpoint = this.checkpoints.get(step);
            if (endCheckpoint.getPosition().x == position.x && endCheckpoint.getPosition().y == position.z) {
                step++;
            }

            Vector2f entityPosition = new Vector2f(position.x, position.z);
            Vector2f direction = new Vector2f(endCheckpoint.getPosition().x, endCheckpoint.getPosition().y).sub(entityPosition);
            this.setPattern(new Pattern(AStar.shortestPath(world, startCheckpoint, endCheckpoint), startCheckpoint, endCheckpoint));
            yaw = (float) Math.toDegrees(Math.atan2(direction.y, direction.x));

            this.setPatternUpdateCooldown(0);
        }

        Vector3f acceleration = new Vector3f(0, 0, 0);
        Vector3f front = new Vector3f();

        front.x = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        front.y = Math.sin(Math.toRadians(0.0f));
        front.z = Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));

        front.normalize();

        if (this.getPattern() != null && !this.getPattern().getPath().isEmpty()) {
            Node currentNode = this.getPattern().getPath().get(0);
            System.out.println("Pattern : " + this.getPattern().getPath());

            if (currentNode != null) {
                Vector2f entityPosition = new Vector2f(position.x, position.z);
                Vector2f direction = new Vector2f(currentNode.getPosition()).sub(entityPosition);
                if (direction.x != 0 && direction.y != 0) {
                    direction.normalize();
                }
                acceleration.add(direction.x, 0, direction.y);
            }
        }

        this.getVelocity().add(acceleration.mul(speed));

        if (new Vector3f(this.getVelocity().x, 0, this.getVelocity().z).length() > maxSpeed) {
            Vector3f velocityNorm = new Vector3f(this.getVelocity().x, this.getVelocity().y, this.getVelocity().z);
            velocityNorm.normalize().mul(maxSpeed);
            this.getVelocity().x = velocityNorm.x;
            this.getVelocity().z = velocityNorm.z;
        }

        position.x += this.getVelocity().x;
        handleCollisions(world, new Vector3f(this.getVelocity().x, 0, 0), true);

        position.z += this.getVelocity().z;
        handleCollisions(world, new Vector3f(0, 0, this.getVelocity().z), true);

        position.y += this.getVelocity().y;
        handleCollisions(world, new Vector3f(0, this.getVelocity().y, 0), true);
        System.out.println("X : " + position.x + " Y : " + position.y + " Z : " + position.z);
        if (this.getPattern() != null && !this.getPattern().getPath().isEmpty()) {
            float distance = (float) new Vector2i((int) position.x, (int) position.z).distance(this.getPattern().getPath().get(0).getPosition());
            //System.out.println("distance : " + distance);
            if (distance <= 1.0) {
                this.setPattern(this.getPattern().next());
            }
        }

        this.getVelocity().mul(0.95f);
    }

    public ArrayList<Node> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(ArrayList<Node> checkpoints) {
        this.checkpoints = checkpoints;
    }
}
