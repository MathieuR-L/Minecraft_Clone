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

import org.joml.Vector3f;

public class CactusModel {
    public final static Vector3f[] PZ_POS = {
            new Vector3f(-0.5f, -0.5f, 0.435f),
            new Vector3f(-0.5f, 0.5f, 0.435f),
            new Vector3f(0.5f, 0.5f, 0.435f),
            new Vector3f(0.5f, -0.5f, 0.435f),
    };

    public final static Vector3f[] NZ_POS = {
            new Vector3f(-0.5f, -0.5f, -0.435f),
            new Vector3f(-0.5f, 0.5f, -0.435f),
            new Vector3f(0.5f, 0.5f, -0.435f),
            new Vector3f(0.5f, -0.5f, -0.435f),
    };


    public final static Vector3f[] PX_POS = {
            new Vector3f(0.435f, -0.5f, -0.5f),
            new Vector3f(0.435f, 0.5f, -0.5f),
            new Vector3f(0.435f, 0.5f, 0.5f),
            new Vector3f(0.435f, -0.5f, 0.5f),
    };

    public final static Vector3f[] NX_POS = {
            new Vector3f(-0.435f, -0.5f, -0.5f),
            new Vector3f(-0.435f, 0.5f, -0.5f),
            new Vector3f(-0.435f, 0.5f, 0.5f),
            new Vector3f(-0.435f, -0.5f, 0.5f),
    };

    public final static Vector3f[] PY_POS = {
            new Vector3f(-0.5f, 0.5f, 0.5f),
            new Vector3f(-0.5f, 0.5f, -0.5f),
            new Vector3f(0.5f, 0.5f, -0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
    };

    public final static Vector3f[] NY_POS = {
            new Vector3f(-0.5f, -0.5f, 0.5f),
            new Vector3f(-0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, 0.5f),
    };
}
