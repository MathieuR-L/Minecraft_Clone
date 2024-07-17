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

public class BatchVertex {

    private final Vector2f position;
    private final Vector3f rgb;
    private final Vector2f texCoords;

    public BatchVertex(Vector2f position, Vector3f rgb, Vector2f texCoords) {
        this.position = position;
        this.rgb = rgb;
        this.texCoords = texCoords;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector3f getRgb() {
        return rgb;
    }

    public Vector2f getTexCoords() {
        return texCoords;
    }
}
