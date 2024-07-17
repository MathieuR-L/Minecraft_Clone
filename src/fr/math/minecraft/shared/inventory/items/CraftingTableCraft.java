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

package fr.math.minecraft.shared.inventory.items;

import fr.math.minecraft.shared.inventory.CraftData;
import fr.math.minecraft.shared.inventory.CraftRecipes;
import fr.math.minecraft.shared.inventory.ItemStack;
import fr.math.minecraft.shared.world.Material;

public class CraftingTableCraft extends CraftRecipes {

    public CraftingTableCraft() {
        super(new ItemStack(Material.CRAFTING_TABLE, 1));
        fillRecipe();
    }

    @Override
    public void fillRecipe() {

        CraftData dataPlayerInventory_1 = new CraftData(new byte[]
            {
                Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(),
                Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId()
            }
        );

        playerInventory.add(dataPlayerInventory_1);

        CraftData dataCraftingTable_1 = new CraftData(new byte[]
                {
                        Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(), Material.AIR.getId(),
                        Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(), Material.AIR.getId(),
                        Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()
                }
        );

        CraftData dataCraftingTable_2 = new CraftData(new byte[]
                {
                        Material.AIR.getId(), Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(),
                        Material.AIR.getId(), Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(),
                        Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()
                }
        );

        CraftData dataCraftingTable_3 = new CraftData(new byte[]
                {
                        Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(),
                        Material.AIR.getId(), Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(),
                        Material.AIR.getId(), Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId()
                }
        );

        CraftData dataCraftingTable_4 = new CraftData(new byte[]
                {
                        Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(),
                        Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(), Material.AIR.getId(),
                        Material.OAK_PLANKS.getId(), Material.OAK_PLANKS.getId(), Material.AIR.getId()
                }
        );

        craftingTable.add(dataCraftingTable_1);
        craftingTable.add(dataCraftingTable_2);
        craftingTable.add(dataCraftingTable_3);
        craftingTable.add(dataCraftingTable_4);
    }
}
