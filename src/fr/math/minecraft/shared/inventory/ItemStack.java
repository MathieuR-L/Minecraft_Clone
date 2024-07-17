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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.shared.world.Material;
import org.joml.Math;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class ItemStack {

    private int amount;
    private final Material material;
    private final List<String> lore;
    private boolean hovered;

    public ItemStack(Material material, int amount) {
        this.amount = amount;
        this.material = material;
        this.lore = new ArrayList<>();
    }

    public ItemStack(JsonNode itemNode) {
        this.amount = itemNode.get("amount").asInt();
        this.material = Material.getMaterialById((byte) itemNode.get("block").asInt());
        this.lore = new ArrayList<>();
    }

    public ItemStack(ItemStack item) {
        this.amount = item.getAmount();
        this.material = item.getMaterial();
        this.lore = new ArrayList<>(item.getLore());
    }

    public List<String> getLore() {
        return lore;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = (int) Math.min(amount, 64.0f);
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public ObjectNode toJSONObject() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode itemNode = mapper.createObjectNode();

        itemNode.put("block", material.getId());
        itemNode.put("amount", amount);

        return itemNode;
    }

    @Override
    public String toString() {
        return "ItemStack{" +
                "amount=" + amount +
                ", material=" + material +
                ", lore=" + lore +
                ", hovered=" + hovered +
                '}';
    }
}
