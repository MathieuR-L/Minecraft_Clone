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

package fr.math.minecraft.client.animations;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.Shader;
import fr.math.minecraft.shared.entity.mob.Zombie;
import org.joml.Math;


public class ZombieWalkAnimation extends Animation {

    private float legRotation;
    private final static float HAND_WALKING_ANIMATION_SPEED = 3.0f;
    private final static float MAX_HAND_ROTATION_ANGLE = 35.0f;
    private final Zombie zombie;

    public ZombieWalkAnimation(Zombie zombie) {
        this.legRotation = 0.0f;
        this.zombie = zombie;
    }

    @Override
    public void update() {
        /*
        if(!zombie.isMoving()) {
            legRotation *=0.95;
            return;
        }
         */
        legRotation = Math.sin(Game.getInstance().getTime() * HAND_WALKING_ANIMATION_SPEED) * MAX_HAND_ROTATION_ANGLE;

    }

    @Override
    public void sendUniforms(Shader shader) {
        shader.sendFloat("legRotation", Math.toRadians(legRotation));
    }
}
