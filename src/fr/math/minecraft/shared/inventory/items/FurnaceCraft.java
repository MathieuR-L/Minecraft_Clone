package fr.math.minecraft.shared.inventory.items;

import fr.math.minecraft.shared.inventory.CraftData;
import fr.math.minecraft.shared.inventory.CraftRecipe;
import fr.math.minecraft.shared.inventory.ItemStack;
import fr.math.minecraft.shared.world.Material;

public class FurnaceCraft extends CraftRecipe {

    public FurnaceCraft() {
        super(new ItemStack(Material.FURNACE, 1));
        fillRecipe();
    }

    @Override
    public void fillRecipe() {
        CraftData dataCraftingTable = new CraftData(new byte[]
                {
                        Material.COBBLESTONE.getId(), Material.COBBLESTONE.getId(), Material.COBBLESTONE.getId(),
                        Material.COBBLESTONE.getId(), Material.AIR.getId(), Material.COBBLESTONE.getId(),
                        Material.COBBLESTONE.getId(), Material.COBBLESTONE.getId(), Material.COBBLESTONE.getId(),
                }
        );

        craftingTable.add(dataCraftingTable);
    }
}
