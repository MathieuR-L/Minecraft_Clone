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

public class BlockVertex extends Vertex {

    private final float index;
    private final float face;

    public BlockVertex(Vector3f position, Vector2f textureCoords, int index, int face) {
        super(position, textureCoords);
        this.index = index;
        this.face = face;
    }

    public BlockVertex(Vector3f position, Vector2f textureCoords) {
        this(position, textureCoords, -1, -1);
    }

    public float getIndex() {
        return index;
    }

    public float getFace() {
        return face;
    }
}
