package fr.math.minecraft.server.blockFunctionality;

import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.world.Material;
import org.joml.Vector3i;

public class CraftingTable extends UsableBlock{
    public CraftingTable(Material material, Vector3i position) {
        super(material, position);
    }

    @Override
    public void run(Client client, MinecraftServer server) {
        client.getCraftingTableInventory().setOpen(true);
    }
}
