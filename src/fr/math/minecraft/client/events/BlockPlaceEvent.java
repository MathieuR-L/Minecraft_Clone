package fr.math.minecraft.client.events;

import fr.math.minecraft.client.entity.Player;
import org.joml.Vector3i;

public class BlockPlaceEvent {
    private final Player player;
    private final Vector3i position;

    public BlockPlaceEvent(Player player, Vector3i position) {
        this.player = player;
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector3i getPosition() {
        return position;
    }
}
