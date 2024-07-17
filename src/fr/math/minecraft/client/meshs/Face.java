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

package fr.math.minecraft.client.meshs;

import org.joml.Vector3i;

public enum Face {

    UP(new Vector3i(0, 1, 0)),
    DOWN(new Vector3i(0, -1, 0)),
    NORTH(new Vector3i(0, 0, 1)),
    SOUTH(new Vector3i(0, 0, -1)),
    EST(new Vector3i(1, 0, 0)),
    WEST(new Vector3i(-1, 0, 0));
    private Vector3i normal;

    Face(Vector3i normal) {
        this.normal = normal;
    }

    public Vector3i getNormal() {
        return normal;
    }
}
