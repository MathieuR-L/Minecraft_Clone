package fr.math.minecraft.server.blockFunctionality;

import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.world.Material;
import org.joml.Vector3i;

public abstract class UsableBlock {

    private Material material;
    private Vector3i position;

    public UsableBlock(Material material, Vector3i position) {
        this.material = material;
        this.position = position;
    }

    public void run(Client client, MinecraftServer server){}

    public Material getMaterial() {
        return material;
    }

    public Vector3i getPosition() {
        return position;
    }

    public void setPosition(Vector3i position) {
        this.position = position;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
