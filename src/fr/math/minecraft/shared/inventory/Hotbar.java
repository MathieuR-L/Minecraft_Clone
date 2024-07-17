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

import fr.math.minecraft.client.animations.HotbarAnimation;
import fr.math.minecraft.shared.GameConfiguration;

public class Hotbar extends Inventory {

    private final HotbarAnimation animation;
    private int selectedSlot;

    public Hotbar() {
        super();
        this.type = InventoryType.HOTBAR;
        this.items = new ItemStack[9];
        this.animation = new HotbarAnimation();
        this.selectedSlot = 0;
    }

    public ItemStack[] getItems() {
        return items;
    }

    @Override
    public void setCurrentSlot(int currentSlot) {
        super.setCurrentSlot(currentSlot);
        animation.start();
    }

    @Override
    public float getItemX(int slot) {
        GameConfiguration gameConfiguration = GameConfiguration.getInstance();

        float inventoryWidth = GameConfiguration.INVENTORY_TEXTURE_WIDTH * 1.4f * gameConfiguration.getGuiScale();
        float slotScaleX = inventoryWidth / 177.0f;
        float slotWidth = 18.0f * slotScaleX;
        float inventoryX = (GameConfiguration.WINDOW_WIDTH - inventoryWidth) / 2;

        return 8.0f * slotScaleX + inventoryX + slot * slotWidth;
    }

    @Override
    public float getItemY(int slot) {
        GameConfiguration gameConfiguration = GameConfiguration.getInstance();
        float inventoryHeight = GameConfiguration.INVENTORY_TEXTURE_HEIGHT * 1.4f * gameConfiguration.getGuiScale();
        float slotScaleY = inventoryHeight / 166.0f;
        float inventoryY = (GameConfiguration.WINDOW_HEIGHT - inventoryHeight) / 2;

        return inventoryY + 8.0f * slotScaleY;
    }

    public HotbarAnimation getAnimation() {
        return animation;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(int selectedSlot) {
        this.selectedSlot = selectedSlot;
    }
}
