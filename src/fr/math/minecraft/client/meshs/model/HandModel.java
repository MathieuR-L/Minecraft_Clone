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

package fr.math.minecraft.client.meshs.model;

import fr.math.minecraft.client.vertex.HandVertex;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class HandModel {

    public static HandVertex[] HAND_VERTICES = new HandVertex[] {
        new HandVertex(new Vector3f(0.0f, -1.0f, -1.0f), new Vector2f(10 * 4.0f / 64.0f, 12.0f / 64.0f)),
        new HandVertex(new Vector3f(0.2f, -1.0f, -1.0f), new Vector2f(9 * 4.0f / 64.0f, 12.0f / 64.0f)),
        new HandVertex(new Vector3f(0.2f, 0.0f, -1.0f), new Vector2f(9 * 4.0f / 64.0f, 0.0f)),
        new HandVertex(new Vector3f(0.0f, 0.0f, -1.0f), new Vector2f(10 * 4.0f / 64.0f, 0.0f)),

        new HandVertex(new Vector3f(0.2f, -1.0f, -1.0f), new Vector2f(11 * 4.0f / 64.0f, 12.0f / 64.0f)),
        new HandVertex(new Vector3f(0.2f, -1.0f, -1.2f), new Vector2f(10 * 4.0f / 64.0f, 12.0f / 64.0f)),
        new HandVertex(new Vector3f(0.2f, 0.0f, -1.2f), new Vector2f(10 * 4.0f / 64.0f, 0.0f)),
        new HandVertex(new Vector3f(0.2f, 0.0f, -1.0f), new Vector2f(11 * 4.0f / 64.0f, 0.0f))
    };

}
