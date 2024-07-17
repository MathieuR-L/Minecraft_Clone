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

package fr.math.minecraft.shared.entity;

import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.entity.network.NetworkEntityBehavior;
import fr.math.minecraft.shared.inventory.Trame;
import fr.math.minecraft.shared.network.Hitbox;
import org.apache.log4j.Logger;
import org.joml.*;
import org.joml.Math;

import java.util.*;

public class Villager extends Entity {

    private final static Logger logger = LoggerUtility.getServerLogger(Villager.class, LogType.TXT);
    private Entity serviceRequested;
    private Client sender;
    private Trame trame;

    private Vector3f firstPosition;
    public Villager(String name) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.speed = 0.11f;
        this.maxSpeed = 0.11f;
        this.gravity = new Vector3f(0, 0,0);
        this.firstPosition = new Vector3f(0,0,0);
        this.serviceRequested = null;
        this.sender = null;
        this.hasTrame = false;
    }

    public Villager(String name, Trame trame) {
        super(UUID.randomUUID().toString(), EntityType.VILLAGER);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        this.speed = 0.11f;
        this.maxSpeed = 0.11f;
        this.gravity = new Vector3f(0, 0,0);
        this.firstPosition = new Vector3f(0,0,0);
        this.serviceRequested = null;
        this.sender = null;
        this.hasTrame = true;
        this.trame = trame;
    }

    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera, this);
    }

    public void updateTrame(Entity serverOrigin ,Entity serviceRequested, String data) {
         trame.setType("IP");
         trame.setProtocole("TCP");
         trame.setIpSource(serverOrigin.getIP());
         trame.setIpDestination(serviceRequested.getIP());
         trame.setData(data);
    }

    public Trame createTrame(Entity serverOrigin ,Entity serviceRequested, String data) {
        Trame newTrame = new Trame();
        newTrame.setType("IP");
        newTrame.setProtocole("TCP");
        newTrame.setIpSource(serverOrigin.getIP());
        newTrame.setIpDestination(serviceRequested.getIP());
        newTrame.setData(data);

        return newTrame;
    }

    public void createInternTrame(Entity serverOrigin ,Entity serviceRequested, String data) {
        Trame newTrame = new Trame();
        newTrame.setType("IP");
        newTrame.setProtocole("TCP");
        newTrame.setIpSource(serverOrigin.getIP());
        newTrame.setIpDestination(serviceRequested.getIP());
        newTrame.setData(data);

        this.trame = newTrame;
    }

    public void addCheckpoint(Vector3f checkpoint) {
        this.getCheckpoints().add(checkpoint);
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

    public Vector3f getFirstPosition() {
        return firstPosition;
    }

    public Entity getServiceRequested() {
        return serviceRequested;
    }

    public void setServiceRequested(Entity serviceRequested) {
        this.serviceRequested = serviceRequested;
    }

    public Client getSender() {
        return sender;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public Trame getTrame() {
        return trame;
    }

}
