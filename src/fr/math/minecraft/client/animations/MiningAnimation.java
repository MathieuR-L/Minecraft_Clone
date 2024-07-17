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

import fr.math.minecraft.client.Shader;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.shared.PlayerAction;

public class MiningAnimation extends Animation {

    private final float MINING_ANIMATION_SPEED = 10.0f;
    private float tick;
    private float rotation;
    private boolean rotatingForward, rotatingBackward;

    public MiningAnimation() {
        this.tick = 0;
        this.rotation = 0;
        this.rotatingForward = true;
        this.rotatingBackward = false;
    }

    public void update(Player player) {
        if (player.getAction() != PlayerAction.MINING) {
            tick *= 0.75f;
            rotation *= 0.75f;
            return;
        }
        tick += 0.01f;
        if (rotatingForward) {
            rotation += 5.0f;
            if (rotation > 120.0f) {
                rotatingBackward = true;
                rotatingForward = false;
            }
        } else {
            rotation -= 5.0f;
            if (rotation < 0.0f) {
                rotatingBackward = false;
                rotatingForward = true;
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void sendUniforms(Shader shader) {
        shader.sendFloat("rotationAngleX", rotation);
    }

    public float getRotation() {
        return rotation;
    }

    public float getTick() {
        return tick;
    }
}
