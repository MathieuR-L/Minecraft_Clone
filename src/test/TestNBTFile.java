package test;

import fr.math.minecraft.server.Utils;
import fr.math.minecraft.shared.nbt.NbtHandler;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.PlacedBlock;
import org.jnbt.*;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class TestNBTFile {

    public static void main(String[] args) {
        String filePath = "res/schematics/routeur.schematic";

        NbtHandler nbtHandler = new NbtHandler(filePath);
        CompoundTag compoundTag = nbtHandler.getCompoundTag();

        nbtHandler.setMappingStruc(compoundTag);

        System.out.println(nbtHandler.getMappingStruc());

        System.out.println(nbtHandler.getCleanNbtBlocksArray(compoundTag));

        ByteArrayTag byteArrayTag = nbtHandler.getNbtBlocksArray(compoundTag);
        ByteArrayTag data = nbtHandler.getNbtDataArray(compoundTag);

        ArrayList<int[]> tabArray = new ArrayList<>();
        for (int h = 0; h < byteArrayTag.getValue().length; h++) {
            int blocsInteger = Byte.toUnsignedInt(byteArrayTag.getValue()[h]);
            int dataInteger = Byte.toUnsignedInt(data.getValue()[h]);
            int[] tab = new int[2];
            tab[0] =  blocsInteger;
            tab[1] = dataInteger;
            tabArray.add(tab);
        }




        //int i = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();

        ArrayList<ArrayList<Integer>> segmentationList = nbtHandler.getCleanNbtBlocksArray(compoundTag);
        int segmentationNumber = segmentationList.size();
        ShortTag lenght = nbtHandler.getNbtLength(compoundTag);
        ShortTag width = nbtHandler.getNbtWidth(compoundTag);
        ByteArrayTag dataArray = nbtHandler.getNbtDataArray(compoundTag);


        int cpt = 0;
        ArrayList<int[]> secondTab = new ArrayList<>();
        for (int i = 0; i < segmentationNumber; i++) {
            ArrayList<Integer> blockList = segmentationList.get(i);
            for (int j = 0; j < blockList.size(); j++) {
                cpt++;
                Material currentMaterial;
                int element = blockList.get(j);
                if (element < 0) continue;
                String elementVariant;
                if (nbtHandler.getMappingStruc().get("" + element) != null) {
                    currentMaterial = nbtHandler.getMappingStruc().get("" + element);
                    elementVariant ="0";
                } else {
                    elementVariant = "" + element + ":" + dataArray.getValue()[(i * blockList.size()) + j];
                    currentMaterial = nbtHandler.getMappingStruc().get(elementVariant);
                }

                int originBlock = tabArray.get(cpt)[0];
                int originMat = tabArray.get(cpt)[1];
                int datak = Byte.toUnsignedInt(dataArray.getValue()[(i * blockList.size()) + j]);
                if((originBlock != element ) || (originMat !=  datak)) {
                    System.out.println("ATATATATATATA" + "\noB:" + originBlock + " oD: " + originMat + "| element : " + element + " variant :" + elementVariant);
                }
                //System.out.println("[" + segmentationNumber + "|" + blockList.size() + "|" + j + "]" + " Current Material : " + currentMaterial);
            }
        }

        //System.out.println("Compteur : " + cpt);
            //System.out.println("Longueur byteArrayTag : " + byteArrayTag.getValue().length);
            //System.out.println("Longueur data : " + data.getValue().length);

        /*
        for (Byte block : byteArrayTag.getValue()) {
            if (Byte.toUnsignedInt(block) == 159) {
                if (!arrayList.contains(Byte.toUnsignedInt(data.getValue()[k]))) {
                    arrayList.add(Byte.toUnsignedInt(data.getValue()[k]));
                }
            }
            k++;
        }

        System.out.println(arrayList);

        */

        for (int[] ints : tabArray) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
