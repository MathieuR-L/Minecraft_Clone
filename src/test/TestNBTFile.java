package test;

import fr.math.minecraft.shared.nbt.NbtUtils;
import org.jnbt.*;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.io.FileInputStream;
import java.io.IOException;

public class TestNBTFile {

    public static void main(String[] args){
        String filePath = "res/schematics/house.schematic";

        try (FileInputStream fis = new FileInputStream(filePath)) {
            NBTInputStream nbtInputStream = new NBTInputStream(fis);

            Tag tag = nbtInputStream.readTag();

            CompoundTag compoundTag = NbtUtils.nbtMainCompoud(tag);
            if(compoundTag == null) return;

            ByteArrayTag blocksArray = NbtUtils.getNbtBlocksArray(compoundTag);
            short length = (short) compoundTag.getValue().get("Length").getValue();
            short width = (short) compoundTag.getValue().get("Width").getValue();

            if(blocksArray == null) return;

            int arraySize = blocksArray.getValue().length;
            for (int i = 0; i < arraySize; i++) {
                Vector3i pos = NbtUtils.getBlockPosition(i, length, width);
                System.out.println("Le bloc "+ i +" à la pos:" + pos + " a l'id :" + blocksArray.getValue()[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
