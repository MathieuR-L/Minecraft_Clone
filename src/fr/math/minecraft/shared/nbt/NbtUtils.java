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

package fr.math.minecraft.shared.nbt;

import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;
import org.jnbt.Tag;
import org.joml.Vector3i;

import java.util.Map;
import java.util.Vector;

public class NbtUtils {

    public static CompoundTag nbtMainCompoud(Tag mainTag) {
        if(mainTag instanceof CompoundTag) {
            CompoundTag compound = (CompoundTag) mainTag;
            return compound;
        } else {
            System.out.println("Le ficher nbt ne contient pas de Tag Compound");
            return null;
        }
    }

    public static Tag getNbtTagValue(CompoundTag compoundTag, String nameTag) {
        for (Map.Entry<String, Tag> entry : compoundTag.getValue().entrySet()) {
            if(entry.getKey().equals(nameTag)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static ByteArrayTag getNbtBlocksArray(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("Blocks")) {
            ByteArrayTag blocksArray = (ByteArrayTag) compoundTag.getValue().get("Blocks");
            return blocksArray;
        } else {
            return null;
        }
    }

    public static Vector3i getBlockPosition(int indice, short length, short width){
        Vector3i blockPosition = new Vector3i();

        int x = indice % width;
        int reste = (indice - x)/width;
        int z = reste % length;
        int y = (reste - z)/length;

        blockPosition.x = x;
        blockPosition.y = y;
        blockPosition.z = z;

        return blockPosition;
    }

}
