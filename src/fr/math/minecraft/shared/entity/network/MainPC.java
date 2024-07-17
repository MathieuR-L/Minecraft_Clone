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
