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

package fr.math.minecraft.client.entity.player;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.animations.PlayerHandAnimation;
import fr.math.minecraft.client.math.ViewBobbing;
import org.joml.Vector3f;

public class PlayerHand {

    private PlayerHandAnimation animation;
    private final ViewBobbing viewBobbing;
    private int animationTick = 0;

    public PlayerHand() {
        this.animation = PlayerHandAnimation.IDLE;
        this.viewBobbing = new ViewBobbing();
    }

    public void update(Vector3f velocity) {
        Vector3f cameraRight = new Vector3f(Game.getInstance().getCamera().getRight());
        if (animation == PlayerHandAnimation.IDLE) {
            viewBobbing.getPosition().mul(0.95f);
            viewBobbing.setY(viewBobbing.getY() * 0.95f);
            return;
        }

        float magnitude = (float) Math.sqrt(Math.pow(velocity.x, 2.0f) + Math.pow(velocity.y, 2.0f) + Math.pow(velocity.z, 2.0f));

        if (magnitude > 0.0f) {
            viewBobbing.setSinTime(viewBobbing.getSinTime() + viewBobbing.getEffectSpeed());
        } else {
            viewBobbing.setSinTime(0.0f);
        }

        viewBobbing.setY((float) -Math.abs(viewBobbing.getEffectIntensity() * Math.sin(viewBobbing.getSinTime())));
        viewBobbing.setPosition(cameraRight.mul(viewBobbing.getEffectIntensity() * (float) Math.cos(viewBobbing.getSinTime()) * viewBobbing.getEffectIntensityX()));
    }

    public ViewBobbing getViewBobbing() {
        return viewBobbing;
    }

    public PlayerHandAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(PlayerHandAnimation animation) {
        this.animation = animation;
    }


}
