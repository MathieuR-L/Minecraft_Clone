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

package fr.math.minecraft.client.gui;

import fr.math.minecraft.shared.GameConfiguration;

public class GuiText {

    private String text;
    private int rgb;
    private float x, y, z, rotateAngle, scale;

    public GuiText(String text, float x, float y, int rgb) {
        this(text, x, y, -10, rgb, 0.0f);
    }

    public GuiText(String text, float x, float y, float z, int rgb) {
        this(text, x, y, z, rgb, 0.0f);
    }

    public GuiText(String text, float x, float y, float z, int rgb, float rotateAngle) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.z = z;
        this.rgb = rgb;
        this.rotateAngle = rotateAngle;
        this.scale = GameConfiguration.DEFAULT_SCALE;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public int getRgb() {
        return rgb;
    }

    public float getRotateAngle() {
        return rotateAngle;
    }

    public float getScale() {
        return scale;
    }

    public void rotate(float rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public void scale(float scale) {
        this.scale = scale;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setRotateAngle(float rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
