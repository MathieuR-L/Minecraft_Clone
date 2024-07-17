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

public class PlayerCraftingTableInventory extends Inventory{

    public PlayerCraftingTableInventory() {
        super();
        this.items = new ItemStack[9];
    }

    @Override
    public ItemStack[] getItems() {
        return super.getItems();
    }

    @Override
    public float getItemX(int slot) {
        return 0;
    }

    @Override
    public float getItemY(int slot) {
        return 0;
    }
}
