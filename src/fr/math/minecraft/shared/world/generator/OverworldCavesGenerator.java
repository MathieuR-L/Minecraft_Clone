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

import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.World;
import org.joml.Vector2i;
import java.util.Random;

import java.util.Map;

public class OverworldCavesGenerator implements CavesGenerator {


    @Override
    public void generateCaves(World world, Chunk chunk) {
        PerlinNoiseGenerator noiseGenerator = new PerlinNoiseGenerator(0.015f, 0.2f, 3, 1.175f, 0);

        Map<Vector2i, Integer> heightMap = chunk.getHeightMap(world);

        for (int x = 0; x < Chunk.SIZE; x++) {
            for (int z = 0; z < Chunk.SIZE; z++) {

                int worldX = chunk.getPosition().x * Chunk.SIZE + x;
                int worldZ = chunk.getPosition().z * Chunk.SIZE + z;

                int worldHeight = heightMap.get(new Vector2i(x, z));

                for (int y = 0; y < Chunk.SIZE; y++) {

                    int worldY = chunk.getPosition().y * Chunk.SIZE + y;

                    if (worldY <= 7) {
                        continue;
                    }

                    if (worldY < worldHeight - 10) {
                        if (Math.abs(noiseGenerator.getNoise(world.getSeed(), worldX, worldY, worldZ)) >= 0.32f) {
                            chunk.setBlock(x, y, z, Material.AIR.getId());
                        }
                    }

                }
            }
        }
    }

}
