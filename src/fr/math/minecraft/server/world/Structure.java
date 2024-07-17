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

import fr.math.minecraft.shared.world.Coordinates;
import org.joml.Vector3i;
import java.util.ArrayList;
import java.util.HashMap;

public class Structure {

    private HashMap<Coordinates, Byte> structureMap;
    private ArrayList<Coordinates> structures;
    private ArrayList<Vector3i> chunckVisited;
    public Structure() {
        this.structureMap = new HashMap<>();
        this.structures = new ArrayList<>();
        this.chunckVisited = new ArrayList<>();
    }

    public void setBlock(int worldX, int worldY, int worldZ, byte block) {
        Coordinates coordinates = new Coordinates(worldX, worldY, worldZ);
        this.structureMap.put(coordinates, block);
    }

    public HashMap<Coordinates, Byte> getStructureMap() {
        return structureMap;
    }

    public ArrayList<Coordinates> getStructures() {
        return structures;
    }

    public ArrayList<Vector3i> getChunckVisited() {
        return chunckVisited;
    }
}
