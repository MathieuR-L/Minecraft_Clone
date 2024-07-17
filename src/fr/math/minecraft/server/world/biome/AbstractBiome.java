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

package fr.math.minecraft.server.world.biome;

import fr.math.minecraft.server.world.*;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.Region;
import fr.math.minecraft.shared.world.World;
import fr.math.minecraft.shared.world.generator.NoiseGenerator;

public abstract class AbstractBiome {

    protected NoiseGenerator noise, treeNoise, weedNoise;
    protected String biomeName;
    protected int biomeID;
    
    public abstract Material getUpperBlock();
    public abstract Material getSecondBlock();
    public abstract void buildTree(int worldX, int worldY, int worldZ, Structure structure, World world);
    public abstract void buildWeeds(int worldX, int worldY, int worldZ, Structure structure, World world);
    public abstract void buildVillage(int worldX, int worldY, int worldZ, Structure structure, World world, Region region);

    public float getHeight(int x, int z,float seed) {
        return noise.getHeight(x, z,seed);
    }
    
    public String getBiomeName() {
        return biomeName;
    }
    
    public int getBiomeID() {
        return biomeID;
    }
}
