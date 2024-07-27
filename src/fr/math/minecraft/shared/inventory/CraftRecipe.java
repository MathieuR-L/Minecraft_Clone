package fr.math.minecraft.shared.inventory;

import java.util.ArrayList;

public abstract class CraftRecipe {

    protected ArrayList<CraftData> playerInventory;
    protected ArrayList<CraftData> craftingTable;
    protected ItemStack craft;
    private int amount;

    public CraftRecipe(ItemStack craft) {
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
