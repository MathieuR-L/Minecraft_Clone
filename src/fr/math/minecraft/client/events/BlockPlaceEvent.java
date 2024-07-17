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

package fr.math.minecraft.client.events;

import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.shared.world.Material;
import org.joml.Vector3i;

public class BlockPlaceEvent {
    private final Player player;
    private final Vector3i position;
    private final Material material;

    public BlockPlaceEvent(Player player, Vector3i position, Material material) {
        this.player = player;
        this.position = position;
        this.material = material;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector3i getPosition() {
        return position;
    }

    public Material getMaterial() {
        return material;
    }
}
