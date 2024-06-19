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

    private int step;
    private final static Logger logger = LoggerUtility.getServerLogger(Villager.class, LogType.TXT);


    public Villager(String name) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.speed = 0.5f;
        this.maxSpeed = 0.5f;
        this.step = 0;
        this.gravity = new Vector3f(0, 0 ,0);
    }

    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera, this);
    }

}
