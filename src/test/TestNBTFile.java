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


        ArrayList<ArrayList<Integer>> segmentationList = nbtHandler.getCleanNbtBlocksArray(compoundTag);
        int segmentationNumber = segmentationList.size();
        ByteArrayTag dataArray = nbtHandler.getNbtDataArray(compoundTag);


        int cpt = 0;
        int nbError = 0;
        for (int i = 0; i < segmentationNumber; i++) {
            ArrayList<Integer> blockList = segmentationList.get(i);
            for (int j = 0; j < blockList.size(); j++) {

                int indiceSus = (i * 1000) + j;
                int element = blockList.get(j);

                if (element < 0) continue;

                String elementVariant;
                if (nbtHandler.getMappingStruc().get("" + element) != null) {
                    elementVariant ="0";
                } else {
                    elementVariant = "" + element + ":" + dataArray.getValue()[indiceSus];
                }

                int originBlock = tabArray.get(cpt)[0];
                int originMat = tabArray.get(cpt)[1];
                int dataSus = Byte.toUnsignedInt(dataArray.getValue()[indiceSus]);
                if((originBlock != element ) || (originMat !=  dataSus)) {
                    System.out.println("Erreur à l'indice origine:" + cpt +" sus:" + indiceSus + "\noB:" + originBlock + " oD: " + originMat + "| element : " + element + " variant :" + elementVariant);
                    nbError++;
                }
                cpt++;
                //System.out.println("[" + segmentationNumber + "|" + blockList.size() + "|" + j + "]" + " Current Material : " + currentMaterial);
            }
        }

        System.out.println("Nombre d'erreur :" + nbError + "/" + cpt);

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
    }
}
