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

package fr.math.minecraft.client.vertex;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Vertex {

    private final Vector3f position;
    private final Vector2f textureCoords;
    private final float blockID;
    private final float blockFace;
    private final int occlusion;

    public Vertex(Vector3f position, Vector2f textureCoords, float blockID, float blockFace) {
        this(position, textureCoords, blockID, blockFace, 3);
    }

    public Vertex(Vector3f position, Vector2f textureCoords, float blockID, float blockFace, int occlusion) {
        this.position = position;
        this.textureCoords = textureCoords;
        this.blockID = blockID;
        this.blockFace = blockFace;
        this.occlusion = occlusion;
    }

    public Vertex(Vector3f position, Vector2f textureCoords) {
        this.position = position;
        this.textureCoords = textureCoords;
        this.blockID = -1;
        this.blockFace = -1;
        this.occlusion = -1;
    }


    public Vector3f getPosition() {
        return position;
    }

    public Vector2f getTextureCoords() {
        return textureCoords;
    }

    public float getBlockID() {
        return blockID;
    }

    public float getBlockFace() {
        return blockFace;
    }

    public int getOcclusion() {
        return occlusion;
    }

}
