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

import fr.math.minecraft.shared.world.World;
import org.joml.SimplexNoise;

public class NoiseGenerator {

    private final float smoothness;
    private final float offset;
    private final float roughness;
    private final float octaves;
    private final float amplitude;

    public NoiseGenerator(float octaves, float amplitude, float smoothness, float roughness, float offset) {
        this.octaves = octaves;
        this.smoothness = smoothness;
        this.roughness = roughness;
        this.offset = offset;
        this.amplitude = amplitude;
    }

    public float getNoise(int x, int y) {
        float totalAmplitude = 0.0f;
        float noiseValue = 0.0f;
        for (int i = 0; i < octaves; i++) {
            float frequency = (float) Math.pow(2.0, i);
            float amplitude = (float) Math.pow(roughness, i);

            float noiseX = x * frequency / smoothness;
            float noiseY = y * frequency / smoothness;

            float noise = SimplexNoise.noise(noiseX, noiseY);
            noise = (noise + 1.0f) / 2.0f;
            noiseValue += noise * amplitude;
            totalAmplitude += amplitude;
        }

        return noiseValue / totalAmplitude;
    }

    public float getHeight(int x, int y, float seed) {
        float noiseValue = 0.0f;
        for (int i = 0; i < octaves; i++) {
            float frequency = (float) Math.pow(2.0, i);
            float amplitude = (float) Math.pow(roughness, i);

            float noiseX = x * frequency / smoothness;
            float noiseY = y * frequency / smoothness;

            noiseValue += SimplexNoise.noise(noiseX+seed, noiseY+seed) * amplitude;
        }

        float height = (((noiseValue / 2.1f) + 1.2f) * amplitude) + offset;

        return height > 0 ? height : 1;
    }

    public float getAmplitude() {
        return this.amplitude;
    }

    public float getOffset() {
        return this.offset;
    }
}
