package fr.math.minecraft.server.blockFunctionality;

import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.world.Material;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class MailTeleportationBlock extends UsableBlock{

    private Vector3f mailHubPosition;

    public MailTeleportationBlock(Material material, Vector3i position) {
        super(material, position);
        this.mailHubPosition = new Vector3f(0.0f, 100.0f, 0.0f);
    }

    @Override
    public void run(Client client, MinecraftServer server) {
        client.setPosition(mailHubPosition);
    }
}
