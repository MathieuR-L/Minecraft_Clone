package fr.math.minecraft.server.world.biome;

import fr.math.minecraft.client.world.Chunk;
import fr.math.minecraft.server.world.Material;
import fr.math.minecraft.server.world.ServerChunk;

public class DesertBiome extends AbstractBiome{

    public Material getUpperBlock() {
        return Material.SAND;
    }

    @Override
    public Material getSecondBlock() {
        return Material.SAND;
    }

    @Override
    public void buildTree(ServerChunk chunk, int x, int y, int z) {
        int cactusSize = 3;
        for(int i=1; i<=cactusSize;i++){
            chunk.setBlock(x,y+i,z,Material.CACTUS.getId());
        }
    }

    @Override
    public void buildWeeds(ServerChunk chunk, int x, int y, int z) {

    }
}