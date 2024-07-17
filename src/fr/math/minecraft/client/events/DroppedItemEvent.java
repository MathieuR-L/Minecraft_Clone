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

import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.World;
import org.joml.Vector3f;

public class DroppedItemEvent {

    private final World world;
    private final ArrayNode itemsData;

    public DroppedItemEvent(World world, ArrayNode itemsData) {
        this.world = world;
        this.itemsData = itemsData;
    }

    public World getWorld() {
        return world;
    }

    public ArrayNode getItemsData() {
        return itemsData;
    }
}
