package test.java.shared.world;

import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.World;
import org.junit.Assert;
import org.junit.Test;

public class TestChunk {

    @Test
    public void testDeterministChunkGeneration() {

        Chunk chunk = new Chunk(0, 5, 0);
        Chunk otherChunk = new Chunk(0, 5, 0);

        World world = new World();

        chunk.generate(world, world.getTerrainGenerator());
        otherChunk.generate(world, world.getTerrainGenerator());

        Assert.assertArrayEquals(chunk.getBlocks(), otherChunk.getBlocks());
    }

}
