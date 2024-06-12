package fr.math.minecraft.server.world.biome;

import fr.math.minecraft.server.world.Structure;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.Region;
import fr.math.minecraft.shared.world.World;

public class SuperFlatBiome extends AbstractBiome{
    @Override
    public Material getUpperBlock() {
        return null;
    }

    @Override
    public Material getSecondBlock() {
        return null;
    }

    @Override
    public void buildTree(int worldX, int worldY, int worldZ, Structure structure, World world) {

    }

    @Override
    public void buildWeeds(int worldX, int worldY, int worldZ, Structure structure, World world) {

    }

    @Override
    public void buildVillage(int worldX, int worldY, int worldZ, Structure structure, World world, Region region) {

    }
}
