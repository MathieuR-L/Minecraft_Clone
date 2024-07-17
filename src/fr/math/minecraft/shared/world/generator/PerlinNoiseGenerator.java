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

package fr.math.minecraft.shared.world.generator;

import fr.math.minecraft.ServerMain;
import org.joml.SimplexNoise;

public class PerlinNoiseGenerator {

    private final float frequency;
    private final float persistence;
    private final int octaves;
    private final float lacunarity;
    private final int seed;

    public PerlinNoiseGenerator(float frequency, float persistence, int octaves, float lacunarity, int seed) {
        this.frequency = frequency;
        this.persistence = persistence;
        this.octaves = octaves;
        this.lacunarity = lacunarity;
        this.seed = seed;
    }

    public float getNoise(float seed, float x, float y, float z) {

        float noiseValue = 0.0f;
        float currentPersistence = 1.0f;
        x *= frequency;
        y *= frequency;
        z *= frequency;

        for (int i = 0; i < octaves; i++) {

            float noise = SimplexNoise.noise(x + seed, y + seed, z + seed);
            noiseValue += noise * currentPersistence;

            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;

            currentPersistence *= persistence;

        }

        return noiseValue;
    }
}
