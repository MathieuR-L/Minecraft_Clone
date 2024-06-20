package fr.math.minecraft.shared.entity;

import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.network.Hitbox;
import org.apache.log4j.Logger;
import org.joml.*;
import org.joml.Math;

import java.util.*;

public class Villager extends Entity {

    private final static Logger logger = LoggerUtility.getServerLogger(Villager.class, LogType.TXT);

    private Vector3f firstPosition;
    public Villager(String name) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.speed = 0.5f;
        this.maxSpeed = 0.5f;
        this.gravity = new Vector3f(0, 0 ,0);
        this.firstPosition = new Vector3f(0,0,0);
    }

    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera, this);
    }

    public void addCheckpoint(Vector3f checkpoint) {
        if(checkpoint.x > GameConfiguration.MAX_ASTAR_DISTANCE || checkpoint.y > GameConfiguration.MAX_ASTAR_DISTANCE || checkpoint.z > GameConfiguration.MAX_ASTAR_DISTANCE) {
            int xQuotient = (int)(checkpoint.x / GameConfiguration.MAX_ASTAR_DISTANCE);
            int yQuotient = (int)(checkpoint.y / GameConfiguration.MAX_ASTAR_DISTANCE);
            int zQuotient = (int)(checkpoint.z / GameConfiguration.MAX_ASTAR_DISTANCE);
            int max = Math.max(xQuotient, Math.max(yQuotient, zQuotient));
        } else {
            this.getCheckpoints().add(checkpoint);
        }
    }

    public void addCheckpointList(ArrayList<Vector3f> checkpointList) {
        for (Vector3f checkpoint : checkpointList) {
            this.getCheckpoints().add(checkpoint);
        }
    }

    public void reverseCheckpoints() {
        Collections.reverse(this.getCheckpoints());
    }

    public void setFirstPosition(Vector3f position) {
        this.firstPosition = position;
    }

}
