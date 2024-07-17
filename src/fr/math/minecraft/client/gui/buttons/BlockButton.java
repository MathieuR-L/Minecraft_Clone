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

package fr.math.minecraft.client.gui.buttons;

import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.client.meshs.ButtonMesh;
import fr.math.minecraft.client.visitor.ButtonActionVisitor;
import fr.math.minecraft.client.visitor.ButtonVisitor;

import static org.lwjgl.glfw.GLFW.*;

public abstract class BlockButton {

    private final String text;
    private final float x, y, z;
    private boolean hovered;

    public BlockButton(String text, float x, float y) {
        this(text, x, y, -10);
    }

    public BlockButton(String text, float x, float y, float z) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hovered = false;
    }

    public String getText() {
        return text;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public boolean contains(double mouseX, double mouseY) {
        if (mouseX < 0 || mouseY < 0) {
            return false;
        }
        mouseY = GameConfiguration.WINDOW_HEIGHT - mouseY;
        return (
            x <= mouseX && mouseX <= x + ButtonMesh.BUTTON_WIDTH &&
            y <= mouseY && mouseY <= y + ButtonMesh.BUTTON_HEIGHT
        );
    }

    public abstract <T> T accept(ButtonVisitor<T> visitor);

    public void handleInputs(long window, double mouseX, double mouseY) {
        if (!this.contains(mouseX, mouseY)) {
            hovered = false;
            return;
        }

        hovered = true;
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS) {
            this.accept(new ButtonActionVisitor());
        }
    }

    public float getZ() {
        return z;
    }
}
