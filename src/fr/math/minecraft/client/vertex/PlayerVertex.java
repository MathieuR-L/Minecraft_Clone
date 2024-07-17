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

public class PlayerVertex extends Vertex {

    public final static float HEAD_PART_ID = 1.0f;
    public final static float CHEST_PART_ID = 2.0f;
    public final static float LEFT_HAND_PART_ID = 3.0f;
    public final static float RIGHT_HAND_PART_ID = 3.5f;
    public final static float LEFT_LEG_PART_ID = 4.0f;
    public final static float RIGHT_LEG_PART_ID = 4.5f;

    private final float partId;

    public PlayerVertex(Vector3f position, Vector2f texCoords, float partId) {
        super(position, texCoords);
        this.partId = partId;
    }

    public float getPartId() {
        return partId;
    }
}
