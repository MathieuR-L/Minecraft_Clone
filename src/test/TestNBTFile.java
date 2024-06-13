package test;

import fr.math.minecraft.shared.nbt.NbtHandler;
import org.jnbt.*;

import java.util.ArrayList;


public class TestNBTFile {

    public static void main(String[] args){
        String filePath = "res/schematics/montagne.schematic";

        NbtHandler nbtHandler = new NbtHandler(filePath);
        CompoundTag compoundTag = nbtHandler.getCompoundTag();

        nbtHandler.setMappingStruc(compoundTag);

        System.out.println(nbtHandler.getMappingStruc());

        //System.out.println(bloc.get(6381));
    }
}
