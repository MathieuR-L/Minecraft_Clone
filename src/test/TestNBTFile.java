package test;

import fr.math.minecraft.shared.nbt.NbtHandler;
import org.jnbt.*;

import java.util.ArrayList;


public class TestNBTFile {

    public static void main(String[] args){
        String filePath = "res/schematics/dnsColors.schematic";

        NbtHandler nbtHandler = new NbtHandler(filePath);
        CompoundTag compoundTag = nbtHandler.getCompoundTag();

        nbtHandler.setMappingStruc(compoundTag);

        System.out.println(nbtHandler.getMappingStruc());

        System.out.println(nbtHandler.getCleanNbtBlocksArray(compoundTag));

        ByteArrayTag byteArrayTag = nbtHandler.getNbtBlocksArray(compoundTag);
        ByteArrayTag data = nbtHandler.getNbtDataArray(compoundTag);

        int i = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Byte block : byteArrayTag.getValue()) {
            i++;
            if(Byte.toUnsignedInt(block) == 159) {
                if(!arrayList.contains(Byte.toUnsignedInt(data.getValue()[i]))){
                    arrayList.add(Byte.toUnsignedInt(data.getValue()[i]));
                }
            }
        }
        System.out.println(arrayList);
    }
}
