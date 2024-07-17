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

package fr.math.minecraft.client.math;

import org.joml.Vector3f;

public class ViewBobbing {

    private float sinTime;
    private float effectSpeed;
    private float effectIntensityX;
    private float effectIntensity;
    private Vector3f position;
    private float y;

    public ViewBobbing() {
        this(0, 0.035f, 0.2f, 0.15f);
    }

    public ViewBobbing(float sinTime, float effectSpeed, float effectIntensityX, float effectIntensity) {
        this.sinTime = sinTime;
        this.effectSpeed = effectSpeed;
        this.effectIntensityX = effectIntensityX;
        this.effectIntensity = effectIntensity;
        this.y = 0;
        this.position = new Vector3f();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getSinTime() {
        return sinTime;
    }

    public void setSinTime(float sinTime) {
        this.sinTime = sinTime;
    }

    public float getEffectSpeed() {
        return effectSpeed;
    }

    public void setEffectSpeed(float effectSpeed) {
        this.effectSpeed = effectSpeed;
    }

    public float getEffectIntensityX() {
        return effectIntensityX;
    }

    public void setEffectIntensityX(float effectIntensityX) {
        this.effectIntensityX = effectIntensityX;
    }

    public float getEffectIntensity() {
        return effectIntensity;
    }

    public void setEffectIntensity(float effectIntensity) {
        this.effectIntensity = effectIntensity;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
}
