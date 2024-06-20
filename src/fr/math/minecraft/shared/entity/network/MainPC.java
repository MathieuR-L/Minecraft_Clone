package fr.math.minecraft.shared.entity.network;

import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.shared.entity.Entity;
import fr.math.minecraft.shared.entity.EntityType;
import fr.math.minecraft.shared.network.Hitbox;
import org.joml.Vector3f;
import java.util.UUID;

public class MainPC extends Entity {

    private Client owner;

    public MainPC(String name) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.speed = 0.0f;
        this.maxSpeed = 0.0f;
        this.gravity = new Vector3f(0, 0 ,0);
        this.owner = null;
    }
    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera,this);
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
