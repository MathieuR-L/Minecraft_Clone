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

package fr.math.minecraft.server.world;

import fr.math.minecraft.server.Client;
import fr.math.minecraft.shared.world.Chunk;

import java.util.Comparator;

public class ChunkComparator implements Comparator<Chunk> {

    private final Client client;

    public ChunkComparator(Client client) {
        this.client = client;
    }

    @Override
    public int compare(Chunk o1, Chunk o2) {

        float distance1 = client.getPosition().distance(o1.getPosition().x, o1.getPosition().y, o1.getPosition().z);
        float distance2 = client.getPosition().distance(o2.getPosition().x, o2.getPosition().y, o2.getPosition().z);

        if (distance1 < distance2) {
            if (o1.isEmpty()) {
                return 1;
            }
            return -1;
        } else if (distance2 < distance1) {
            if (o2.isEmpty()) {
                return -1;
            }
            return 1;
        }

        return 0;
    }
}
