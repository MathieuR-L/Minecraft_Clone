package fr.math.minecraft.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joml.Vector3f;

import java.awt.image.BufferedImage;
import java.beans.VetoableChangeListener;

public class Client {

    private final String name;
    private final String uuid;
    private Vector3f position;
    private Vector3f front;
    private float yaw;
    private float bodyYaw;
    private float pitch;
    private float speed;
    private Vector3f velocity;
    private Vector3f acceleration;
    private float Vmax;
    private BufferedImage skin;
    private boolean movingLeft, movingRight, movingForward, movingBackward,flying,sneaking;

    public Client(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.front = new Vector3f(0.0f, 0.0f, 0.0f);
        this.position = new Vector3f(0.0f, 0.0f, 0.0f);
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.speed = .1f;
        this.skin = null;
        this.velocity= new Vector3f(0,0,0);
        this.Vmax = 1f;
    }

    public String getName() {
        return name;
    }

    public void updatePosition(JsonNode packetData) {
        boolean movingLeft = packetData.get("left").asBoolean();
        boolean movingRight = packetData.get("right").asBoolean();
        boolean movingForward = packetData.get("forward").asBoolean();
        boolean movingBackward = packetData.get("backward").asBoolean();
        boolean flying = packetData.get("flying").asBoolean();
        boolean sneaking = packetData.get("sneaking").asBoolean();

        float yaw = packetData.get("yaw").floatValue();
        float bodyYaw = packetData.get("bodyYaw").floatValue();
        float pitch = packetData.get("pitch").floatValue();

        this.yaw = yaw;
        this.bodyYaw = bodyYaw;
        this.pitch = pitch;

        this.movingLeft = movingLeft;
        this.movingRight = movingRight;
        this.movingForward = movingForward;
        this.movingBackward = movingBackward;
        this.flying=flying;
        this.sneaking=sneaking;

        Vector3f acceleration = new Vector3f(0,0,0);

        front.x = (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.y = (float) Math.sin(Math.toRadians(0.0f));
        front.z = (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));

        front.normalize();
        Vector3f right = new Vector3f(front).cross(new Vector3f(0, 1, 0)).normalize();

        if (movingForward) {
            acceleration = acceleration.add(front);
        }

        if (movingBackward) {
            acceleration = acceleration.sub(front);
        }

        if (movingLeft) {
            acceleration = acceleration.sub(right);
        }

        if (movingRight) {
            acceleration = acceleration.add(right);
        }

        velocity = velocity.add(acceleration.mul(speed));

        if (velocity.length()>Vmax) {
            velocity=velocity.normalize().mul(Vmax);
        }
        /*
        if (!movingBackward && !movingForward && !movingLeft && !movingRight) {
            velocity = velocity.mul(0.99f);
        }
        */
        velocity.x *= .95f;
        velocity.z *= .95f;

        position = position.add(velocity);

        if (flying)
            position = position.add(new Vector3f(0.0f, .5f, 0.0f));

        if (sneaking && !isCollidingNy())
            position = position.sub(new Vector3f(0.0f, .5f, 0.0f));

    }

    public Vector3f getPosition() {
        return position;
    }

    public ObjectNode toJSON() {
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put("name", this.name);
        node.put("uuid", this.uuid);
        node.put("x", this.position.x);
        node.put("y", this.position.y);
        node.put("z", this.position.z);
        node.put("yaw", this.yaw);
        node.put("pitch", this.pitch);
        node.put("movingLeft", this.movingLeft);
        node.put("movingRight", this.movingRight);
        node.put("movingForward", this.movingForward);
        node.put("movingBackward", this.movingBackward);
        node.put("bodyYaw", this.bodyYaw);

        return node;
    }

    public BufferedImage getSkin() {
        return skin;
    }

    public float getBodyYaw() {
        return bodyYaw;
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public boolean isCollidingNy(){
        MinecraftServer server = MinecraftServer.getInstance();
        System.out.println("Is colliding?");
        return position.y - 1.5 <= server.getWorld().getYupperBlock((int)position.x,(int)position.z);
    }

}
