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

import fr.math.minecraft.shared.inventory.*;
import fr.math.minecraft.shared.inventory.items.CraftingTableCraft;
import fr.math.minecraft.shared.inventory.items.StickCraft;
import fr.math.minecraft.shared.world.Material;
import org.junit.Assert;
import org.junit.Test;

public class TestCraftPlayerInventory {

    @Test
    public void testStickCraft() {
        PlayerCraftInventory inventory = new PlayerCraftInventory();
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 0);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 2);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new StickCraft());
        CraftRecipes craftRecipes = craftController.getCraft(inventory);

        Assert.assertEquals(craftRecipes.getCraft().getMaterial(), Material.STICK);
    }

    @Test
    public void testCraftingTableCraft() {
        PlayerCraftInventory inventory = new PlayerCraftInventory();
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 0);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 1);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 2);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 3);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new CraftingTableCraft());
        CraftRecipes craftRecipes = craftController.getCraft(inventory);

        Assert.assertEquals(craftRecipes.getCraft().getMaterial(), Material.CRAFTING_TABLE);
    }

}
