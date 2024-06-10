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
