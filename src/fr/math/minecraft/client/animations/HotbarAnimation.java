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

public class HotbarAnimation extends Animation{

    private float yTranslation;
    private boolean firstPhase, secondPhase, completed;

    public HotbarAnimation() {
        this.yTranslation = 0;
        this.firstPhase = false;
        this.secondPhase = false;
        this.completed = true;
    }

    public void start() {
        firstPhase = true;
        secondPhase = false;
        completed = false;
    }

    public void end() {
        firstPhase = false;
        secondPhase = false;
        completed = true;
    }

    @Override
    public void update() {
        if (completed) {
            return;
        }

        if (firstPhase && !secondPhase) {
            yTranslation -= 0.1f;
            if (yTranslation < -1.0f) {
                secondPhase = true;
                firstPhase = false;
            }
        }

        if (secondPhase && !firstPhase) {
            yTranslation += 0.1f;
            if (yTranslation >= 0.0f) {
                this.end();
            }
        }
    }

    @Override
    public void sendUniforms(Shader shader) {
        shader.sendFloat("yTranslation", yTranslation);
        System.out.println(yTranslation);
    }

    public float getYTranslation() {
        return yTranslation;
    }

    public boolean isCompleted() {
        return completed;
    }
}
