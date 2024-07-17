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

package fr.math.minecraft.server.math;

public class InterpolateMath {

    public static float smoothStep(float edge0, float edge1, float x) {
        x = x * x * (3.0f - 2.0f * x);
        return edge0 * x + edge1 * (1 - x);
    }

    public static float smoothInterpolation(float bottomLeft, float bottomRight, float topLeft, float topRight, float xMin, float xMax, float zMin, float zMax, float x, float z) {

        float width = xMax - xMin;
        float height = zMax - zMin;
        float xValue = 1 - (x - xMin) / width;
        float zValue = 1 - (z - zMin) / height;

        float a = smoothStep(bottomLeft, bottomRight, xValue);
        float b = smoothStep(topLeft, topRight, xValue);

        return smoothStep(a, b, zValue);
    }

}
