package test;

import fr.math.minecraft.shared.nbt.NbtHandler;
import org.jnbt.*;

import java.util.ArrayList;


public class TestNBTFile {

    public static void main(String[] args){
        String filePath = "res/schematics/t.schematic";

        NbtHandler nbtHandler = new NbtHandler(filePath);
        CompoundTag compoundTag = nbtHandler.getCompoundTag();

        nbtHandler.setMappingStruc(compoundTag);

        System.out.println(nbtHandler.getMappingStruc());

        System.out.println(nbtHandler.getCleanNbtBlocksArray(compoundTag));
    }
}
