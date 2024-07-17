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

package fr.math.minecraft.client.fonts;

import org.joml.Vector2f;

public class CharInfo {

    private final int sourceX, sourceY, width, height;
    private final Vector2f[] textureCoords = new Vector2f[2];

    public CharInfo(int sourceX, int sourceY, int width, int height) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.width = width;
        this.height = height;
    }

    public int getSourceX() {
        return sourceX;
    }


    public int getSourceY() {
        return sourceY;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public void calculateTextureCoordinates(int fontWidth, int fontHeight) {
        float x0 = (float) sourceX / (float) fontWidth;
        float x1 = (float) (sourceX + width) / (float) fontWidth;
        float y0 = 1 - (float) (sourceY - height) / (float) fontHeight;
        float y1 = 1 - (float) sourceY / (float) fontHeight;

        textureCoords[0] = new Vector2f(x0, y1);
        textureCoords[1] = new Vector2f(x1, y0);
    }

    public Vector2f[] getTextureCoords() {
        return textureCoords;
    }
}
