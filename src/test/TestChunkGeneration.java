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

package test;

import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Region;
import fr.math.minecraft.shared.world.World;
import org.junit.Assert;
import org.junit.Test;

public class TestChunkGeneration {

    private World world;

    @Test
    public void testGeneration() {

        this.world = new World();
        Region region = new Region(0, 0, 0);
        region.generateStructure(world);
        world.addRegion(region);

        Chunk chunk = new Chunk(-1, 10, 0);
        Chunk sameChunk = new Chunk(-1, 10, 0);

        chunk.generate(world, world.getTerrainGenerator());
        sameChunk.generate(world, world.getTerrainGenerator());

        int i = 0;
        while (i < Chunk.VOLUME && chunk.getBlocks()[i] == sameChunk.getBlocks()[i]) {
            i++;
        }

        Assert.assertEquals(Chunk.VOLUME, i);
    }

    @Test
    public void testWorldPosToChunkPos() {

        int worldX = -1000;
        int worldY = 1000;
        int worldZ = 1000;

        int chunkX = (int) Math.floor(worldX / (double) Chunk.SIZE);
        int chunkY = (int) Math.floor(worldY / (double) Chunk.SIZE);
        int chunkZ = (int) Math.floor(worldZ / (double) Chunk.SIZE);

        Assert.assertEquals(chunkX, -63);
        Assert.assertEquals(chunkY, 62);
        Assert.assertEquals(chunkZ, 62);
    }

}
