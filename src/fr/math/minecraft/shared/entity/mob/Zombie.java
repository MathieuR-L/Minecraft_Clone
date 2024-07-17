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

package fr.math.minecraft.shared.entity.mob;

import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.client.animations.*;
import fr.math.minecraft.client.entity.Ray;
import fr.math.minecraft.client.events.listeners.EntityUpdate;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.PlayerAction;
import fr.math.minecraft.shared.Sprite;
import fr.math.minecraft.shared.entity.Entity;
import fr.math.minecraft.shared.entity.EntityType;
import fr.math.minecraft.shared.inventory.*;
import fr.math.minecraft.shared.network.Hitbox;
import fr.math.minecraft.shared.network.PlayerInputData;
import fr.math.minecraft.shared.world.*;
import org.joml.Math;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.UUID;

public class Zombie extends Entity {

    public Zombie() {
        this(EntityType.ZOMBIE.getName());
    }

    public Zombie(String name) {
        super(UUID.randomUUID().toString(), EntityType.ZOMBIE);
        this.name = name;
        this.hitbox = new Hitbox(new Vector3f(0, 0, 0), new Vector3f(0.25f, 1.0f, 0.25f));
        animations.add(new ZombieWalkAnimation(this));
        this.damage = 1.0f;
        this.gravity = new Vector3f(0, -0.025f, 0);
    }

    @Override
    public void render(Camera camera, Renderer renderer) {
        renderer.render(camera, this);
    }
}
