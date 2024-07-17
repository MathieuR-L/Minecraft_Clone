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

package fr.math.minecraft.shared.inventory;

import fr.math.minecraft.shared.world.Material;

import java.util.Arrays;

public class CraftData {

    private byte[] tabCraft;

    public CraftData(byte[] tabCraft) {
        this.tabCraft = tabCraft;
    }

    public byte[] getTabCraft() {
        return tabCraft;
    }

    public void setTabCraft(byte[] tabCraft) {
        this.tabCraft = tabCraft;
    }

    public boolean equals(PlayerCraftInventory playerCraftInventory) {
        byte[] playerCraft = new byte[playerCraftInventory.getItems().length];
        for (int i = 0; i < playerCraftInventory.getItems().length; i++) {
            ItemStack itemStack = playerCraftInventory.getItems()[i];
            if (itemStack == null) {
                playerCraft[i] = Material.AIR.getId();
            } else {
                playerCraft[i] = itemStack.getMaterial().getId();
            }
        }
        return Arrays.equals(tabCraft, playerCraft);
    }

    public boolean equals(CraftingTableInventory craftingTableInventory) {
        byte[] playerCraft = new byte[craftingTableInventory.getItems().length];
        for (int i = 0; i < craftingTableInventory.getItems().length; i++) {
            ItemStack itemStack = craftingTableInventory.getItems()[i];
            if (itemStack == null) {
                playerCraft[i] = Material.AIR.getId();
            } else {
                playerCraft[i] = itemStack.getMaterial().getId();
            }
        }
        return Arrays.equals(tabCraft, playerCraft);
    }

}
