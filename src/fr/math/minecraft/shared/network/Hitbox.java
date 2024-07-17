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

package fr.math.minecraft.shared.network;

import org.joml.Vector3f;

public class Hitbox {

    private final Vector3f start;
    private final Vector3f end;

    public Hitbox(Vector3f start, Vector3f end) {
        this.start = start;
        this.end = end;
    }

    public float getWidth() {
        return Math.abs(end.x - start.x);
    }

    public float getHeight() {
        return Math.abs(end.y - start.y);
    }

    public float getDepth() {
        return Math.abs(end.z - start.z);
    }



}
