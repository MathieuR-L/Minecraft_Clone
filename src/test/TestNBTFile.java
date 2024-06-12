package test;

import fr.math.minecraft.shared.nbt.NbtHandler;
import org.jnbt.*;

import java.util.ArrayList;


public class TestNBTFile {

    public static void main(String[] args){
        String filePath = "res/schematics/test.schematic";

        NbtHandler nbtHandler = new NbtHandler(filePath);
        CompoundTag compoundTag = nbtHandler.getCompoundTag();

        nbtHandler.setMappingStruc(compoundTag);

        System.out.println(nbtHandler.getMappingStruc());

        ArrayList<Byte> bloc = nbtHandler.getCleanNbtBlocksArray(compoundTag);

        for (int i = 0; i < bloc.size(); i++) {
            System.out.println("i :"+i +"|" + bloc.get(i));
            nbtHandler.getBlockPosition(i, (short) 2, (short) 2);

        }
    }
}
