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

import fr.math.minecraft.server.world.Structure;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.Region;
import fr.math.minecraft.shared.world.World;
import fr.math.minecraft.shared.world.generator.NoiseGenerator;

public class SuperFlatBiome extends AbstractBiome{

    public SuperFlatBiome() {
        this.noise = new NoiseGenerator(9, 30, 1000.0f, 0.6f, 25);
        this.biomeName = "SuperFlat";
        this.biomeID = 4;
    }

    @Override
    public float getHeight(int x, int z,float seed) {
        return 1f;
    }

    @Override
    public Material getUpperBlock() {
        return Material.GRASS;
    }

    @Override
    public Material getSecondBlock() {
        return Material.DIRT;
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
