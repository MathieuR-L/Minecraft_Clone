package test.java.shared.inventory;

import fr.math.minecraft.shared.inventory.*;
import fr.math.minecraft.shared.inventory.items.ChestCraft;
import fr.math.minecraft.shared.inventory.items.FurnaceCraft;
import fr.math.minecraft.shared.inventory.items.armor.DiamondBootsCraft;
import fr.math.minecraft.shared.inventory.items.armor.DiamondPantsCraft;
import fr.math.minecraft.shared.inventory.items.armor.IronChessplateCraft;
import fr.math.minecraft.shared.inventory.items.armor.LeatherHelmetCraft;
import fr.math.minecraft.shared.inventory.items.axe.DiamondAxeCraft;
import fr.math.minecraft.shared.inventory.items.pickaxe.WoodenPickaxeCraft;
import fr.math.minecraft.shared.inventory.items.shovel.IronShovelCraft;
import fr.math.minecraft.shared.inventory.items.sword.DiamondSwordCraft;
import fr.math.minecraft.shared.world.Material;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestCraftCraftingTable extends TestCase {

    private CraftingTableInventory inventory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.inventory = new CraftingTableInventory();
    }

    @Test
    public void testChestCraft() {

        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 0);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 1);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 2);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 3);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 5);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 6);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 7);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 8);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new ChestCraft());
        CraftRecipe recipes = craftController.getMatchingRecipe(inventory);
        ItemStack craft = recipes.getCraft();

        Assert.assertEquals(craft.getMaterial(), Material.CHEST);
    }

    @Test
    public void testFurnaceCraft() {

        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 0);
        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 1);
        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 2);
        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 3);
        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 5);
        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 6);
        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 7);
        inventory.setItem(new ItemStack(Material.COBBLESTONE, 1), 8);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new FurnaceCraft());
        CraftRecipe recipes = craftController.getMatchingRecipe(inventory);
        ItemStack craft = recipes.getCraft();

        Assert.assertEquals(craft.getMaterial(), Material.FURNACE);
    }

    @Test
    public void testDiamondSwordCraft() {
        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new DiamondSwordCraft());

        for (int i = 0; i < 3; i++) {
            inventory.clear();
            inventory.setItem(new ItemStack(Material.DIAMOND, 1), i);
            inventory.setItem(new ItemStack(Material.DIAMOND, 1), 3 + i);
            inventory.setItem(new ItemStack(Material.STICK, 1), 6 + i);
            CraftRecipe recipe = craftController.getMatchingRecipe(inventory);
            Assert.assertEquals(recipe.getCraft().getMaterial(), Material.DIAMOND_SWORD);
        }
    }

    @Test
    public void testDiamondAxeCraft() {

        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 0);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 1);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 3);
        inventory.setItem(new ItemStack(Material.STICK, 1), 4);
        inventory.setItem(new ItemStack(Material.STICK, 1), 7);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new DiamondAxeCraft());
        ItemStack itemStack = craftController.getMatchingRecipe(inventory).getCraft();

        CraftingTableInventory inventory2 = new CraftingTableInventory();
        inventory2.setItem(new ItemStack(Material.DIAMOND, 1), 1);
        inventory2.setItem(new ItemStack(Material.DIAMOND, 1), 2);
        inventory2.setItem(new ItemStack(Material.DIAMOND, 1), 5);
        inventory2.setItem(new ItemStack(Material.STICK, 1), 4);
        inventory2.setItem(new ItemStack(Material.STICK, 1), 7);

        CraftController craftController2 = CraftController.getInstance();
        craftController2.registerCraft(new DiamondAxeCraft());
        ItemStack itemStack2 = craftController2.getMatchingRecipe(inventory2).getCraft();

        Assert.assertEquals(itemStack.getMaterial(), Material.DIAMOND_AXE);
        Assert.assertEquals(itemStack2.getMaterial(), Material.DIAMOND_AXE);
    }

    @Test
    public void testIronShovelCraft() {
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 0);
        inventory.setItem(new ItemStack(Material.STICK, 1), 3);
        inventory.setItem(new ItemStack(Material.STICK, 1), 6);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new IronShovelCraft());

        ItemStack itemStack = craftController.getMatchingRecipe(inventory).getCraft();

        CraftingTableInventory inventory2 = new CraftingTableInventory();
        inventory2.setItem(new ItemStack(Material.IRON_INGOT, 1), 1);
        inventory2.setItem(new ItemStack(Material.STICK, 1), 4);
        inventory2.setItem(new ItemStack(Material.STICK, 1), 7);

        ItemStack itemStack2 = craftController.getMatchingRecipe(inventory2).getCraft();

        CraftingTableInventory inventory3 = new CraftingTableInventory();
        inventory3.setItem(new ItemStack(Material.IRON_INGOT, 1), 2);
        inventory3.setItem(new ItemStack(Material.STICK, 1), 5);
        inventory3.setItem(new ItemStack(Material.STICK, 1), 8);

        ItemStack itemStack3 = craftController.getMatchingRecipe(inventory3).getCraft();

        Assert.assertEquals(itemStack.getMaterial(), Material.IRON_SHOVEL);
        Assert.assertEquals(itemStack2.getMaterial(), Material.IRON_SHOVEL);
        Assert.assertEquals(itemStack3.getMaterial(), Material.IRON_SHOVEL);
    }

    @Test
    public void testWoodenPickaxeCraft() {
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 0);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 1);
        inventory.setItem(new ItemStack(Material.OAK_PLANKS, 1), 2);
        inventory.setItem(new ItemStack(Material.STICK, 1), 4);
        inventory.setItem(new ItemStack(Material.STICK, 1), 7);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new WoodenPickaxeCraft());
        ItemStack itemStack = craftController.getMatchingRecipe(inventory).getCraft();

        Assert.assertEquals(itemStack.getMaterial(), Material.WOODEN_PICKAXE);
    }

    @Test
    public void testLeatherHelmetCraft() {
        inventory.setItem(new ItemStack(Material.LEATHER, 1), 0);
        inventory.setItem(new ItemStack(Material.LEATHER, 1), 1);
        inventory.setItem(new ItemStack(Material.LEATHER, 1), 2);
        inventory.setItem(new ItemStack(Material.LEATHER, 1), 3);
        inventory.setItem(new ItemStack(Material.LEATHER, 1), 5);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new LeatherHelmetCraft());
        ItemStack itemStack = craftController.getMatchingRecipe(inventory).getCraft();

        CraftingTableInventory inventory2 = new CraftingTableInventory();
        inventory2.setItem(new ItemStack(Material.LEATHER, 1), 3);
        inventory2.setItem(new ItemStack(Material.LEATHER, 1), 4);
        inventory2.setItem(new ItemStack(Material.LEATHER, 1), 5);
        inventory2.setItem(new ItemStack(Material.LEATHER, 1), 6);
        inventory2.setItem(new ItemStack(Material.LEATHER, 1), 8);

        ItemStack itemStack2 = craftController.getMatchingRecipe(inventory2).getCraft();

        Assert.assertEquals(itemStack.getMaterial(), Material.LEATHER_HELMET);
        Assert.assertEquals(itemStack2.getMaterial(), Material.LEATHER_HELMET);
    }

    @Test
    public void testIronChessplateCraft() {
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 0);
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 2);
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 3);
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 4);
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 5);
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 6);
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 7);
        inventory.setItem(new ItemStack(Material.IRON_INGOT, 1), 8);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new IronChessplateCraft());
        ItemStack itemStack = craftController.getMatchingRecipe(inventory).getCraft();

        Assert.assertEquals(itemStack.getMaterial(), Material.IRON_CHESSPLATE);
    }

    @Test
    public void testDiamondPantsCraft() {
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 0);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 1);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 2);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 3);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 5);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 6);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 8);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new DiamondPantsCraft());
        ItemStack itemStack = craftController.getMatchingRecipe(inventory).getCraft();

        Assert.assertEquals(itemStack.getMaterial(), Material.DIAMOND_PANTS);
    }

    @Test
    public void testDiamondBootsCraft() {
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 0);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 2);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 3);
        inventory.setItem(new ItemStack(Material.DIAMOND, 1), 5);

        CraftController craftController = CraftController.getInstance();
        craftController.registerCraft(new DiamondBootsCraft());
        ItemStack itemStack = craftController.getMatchingRecipe(inventory).getCraft();

        CraftingTableInventory inventory2 = new CraftingTableInventory();
        inventory2.setItem(new ItemStack(Material.DIAMOND, 1), 3);
        inventory2.setItem(new ItemStack(Material.DIAMOND, 1), 5);
        inventory2.setItem(new ItemStack(Material.DIAMOND, 1), 6);
        inventory2.setItem(new ItemStack(Material.DIAMOND, 1), 8);

        CraftController craftController2 = CraftController.getInstance();
        craftController2.registerCraft(new DiamondBootsCraft());
        ItemStack itemStack2 = craftController.getMatchingRecipe(inventory2).getCraft();

        Assert.assertEquals(itemStack.getMaterial(), Material.DIAMOND_BOOTS);
        Assert.assertEquals(itemStack2.getMaterial(), Material.DIAMOND_BOOTS);
    }

}
