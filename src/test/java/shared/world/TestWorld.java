package test.java.shared.world;

import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.World;
import org.joml.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestWorld {

    private World world;

    @Before
    public void setUp() {
        this.world = new World();
    }

    @Test
    public void testCalculateSpawnPosition() {
        Chunk chunk = new Chunk(0, 5, 0);

        for (int y = 0; y < 10; y++) {
            chunk.setBlock(0, y, 0, Material.STONE.getId());
        }

        world.addChunk(chunk);
        world.calculateSpawnPosition();

        Vector3f spawnPosition = world.getSpawnPosition();
        // We make the player spawn 20 blocks upper the spawn position to avoid spawning him in a block
        Assert.assertEquals(spawnPosition, new Vector3f(0, chunk.getPosition().y * Chunk.SIZE + 10 + 20, 0));
    }
}
