package fr.math.minecraft.server.manager;

import fr.math.minecraft.server.world.biome.*;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.world.generator.NoiseGenerator;

public class BiomeManager {
    private NoiseGenerator noise;
    private final AbstractBiome desertBiome;
    private final AbstractBiome forestBiome;
    private final AbstractBiome plainBiome;
    private final AbstractBiome montainsBiome;
    private final AbstractBiome superflatBiome;
    private boolean isSuperFlat;

    public BiomeManager() {
        if(GameConfiguration.WORLD_TYPE.equals("CLASSIC_WORLD")) {
            this.noise = new NoiseGenerator(9, 30, 1500.0f, .25f, 25);
            this.isSuperFlat = false;
        } else {
            this.noise = new NoiseGenerator(0, 0, 0, 0, 0);
            this.isSuperFlat = true;
        }
        this.desertBiome= new DesertBiome();
        this.forestBiome = new ForestBiome();
        this.plainBiome = new PlainBiome();
        this.montainsBiome = new MountainsBiome();
        this.superflatBiome = new SuperFlatBiome();
    }

    public AbstractBiome getBiome(int x, int z,float seed){
        float res = noise.getNoise(x+(int)seed, z+(int)seed);
        if(isSuperFlat) {
            return this.superflatBiome;
        }
        if (res < 0.23f){
            return this.desertBiome;
        } else if (res < 0.42f) {
            return this.forestBiome;
        } else if (res < 0.62f) {
            return this.montainsBiome;
        } else {
            return this.plainBiome;
        }
    }

    public void setNoise(NoiseGenerator noise) {
        this.noise = noise;
    }
}