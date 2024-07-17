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

import fr.math.minecraft.shared.inventory.CraftData;
import fr.math.minecraft.shared.world.Material;

import java.util.ArrayList;

public abstract class CraftRecipes {

    protected ArrayList<CraftData> playerInventory;
    protected ArrayList<CraftData> craftingTable;
    protected ItemStack craft;
    private int amount;

    public CraftRecipes(ItemStack craft) {
        this.playerInventory = new ArrayList<>();
        this.craftingTable = new ArrayList<>();
        this.craft = craft;
        this.amount = craft.getAmount();
    }

    public abstract void fillRecipe();

    public ItemStack getCraft() {
        craft.setAmount(amount);
        return craft;
    }

    public ArrayList<CraftData> getCraftingTable() {
        return craftingTable;
    }

    public ArrayList<CraftData> getPlayerInventory() {
        return playerInventory;
    }
}
