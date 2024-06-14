package fr.math.minecraft.shared.nbt;

import fr.math.minecraft.shared.world.Material;
import org.jnbt.*;
import org.joml.Vector3i;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NbtHandler {

    private String filePath;
    private Tag mainTag;
    private final static int maxBlocPerList = 1000;

    private HashMap<Integer, Material> mappingStruc;

    public NbtHandler(String filePath) {
        this.filePath = filePath;
        try (FileInputStream fis = new FileInputStream(filePath)){
            NBTInputStream nbtInputStream = new NBTInputStream(fis);
            this.mainTag = nbtInputStream.readTag();
        } catch (IOException e) {
        }
        this.mappingStruc = new HashMap<>();
    }

    public CompoundTag getCompoundTag() {
        if(mainTag instanceof CompoundTag) {
            CompoundTag compound = (CompoundTag) mainTag;
            return compound;
        } else {
            System.out.println("Le ficher nbt ne contient pas de Tag Compound");
            return null;
        }
    }

    public CompoundTag getCompoundTag(Tag mainTag) {
        if(mainTag instanceof CompoundTag) {
            CompoundTag compound = (CompoundTag) mainTag;
            return compound;
        } else {
            System.out.println("Le ficher nbt ne contient pas de Tag Compound");
            return null;
        }
    }

    public Tag getNbtTagValue(CompoundTag compoundTag, String nameTag) {
        for (Map.Entry<String, Tag> entry : compoundTag.getValue().entrySet()) {
            if(entry.getKey().equals(nameTag)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public ByteArrayTag getNbtBlocksArray(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("Blocks")) {
            ByteArrayTag blocksArray = (ByteArrayTag) compoundTag.getValue().get("Blocks");
            return blocksArray;
        } else {
            return null;
        }
    }

    public ArrayList<ArrayList<Byte>> getCleanNbtBlocksArray(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("Blocks")) {
            ByteArrayTag blocksArray = (ByteArrayTag) compoundTag.getValue().get("Blocks");

            int size = blocksArray.getValue().length;
            int segmentationNumber = size / maxBlocPerList;
            int reste = size % maxBlocPerList;

            ArrayList<ArrayList<Byte>> segmentationList = new ArrayList<>();
            for (int i = 0; i < segmentationNumber; i++) {
                ArrayList<Byte> blocks = new ArrayList<>();
                for (int j = 0; j < maxBlocPerList; j++) {
                    blocks.add(blocksArray.getValue()[j + i*maxBlocPerList]);
                }
                segmentationList.add(blocks);
            }
            if(reste != 0 && segmentationNumber != 0) {
                ArrayList<Byte> lastBlocks = new ArrayList<>();
                for (int i = segmentationNumber*maxBlocPerList - 1; i < reste + (segmentationNumber*maxBlocPerList - 1); i++) {
                    lastBlocks.add(blocksArray.getValue()[i]);
                }
                segmentationList.add(lastBlocks);
            } else {
                ArrayList<Byte> littleSchem = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    littleSchem.add(blocksArray.getValue()[i]);
                }
                segmentationList.add(littleSchem);
            }


            return segmentationList;
        } else {
            return null;
        }
    }

    public ShortTag getNbtLength(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("Length")) {
            ShortTag length = (ShortTag) compoundTag.getValue().get("Length");
            System.out.println("len :" + length);
            return length;
        } else {
            return null;
        }
    }

    public ShortTag getNbtWidth(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("Width")) {
            ShortTag width = (ShortTag) compoundTag.getValue().get("Width");
            System.out.println("width :" + width);
            return width;
        } else {
            return null;
        }
    }

    public Vector3i getBlockPosition(int indice, short length, short width){
        Vector3i blockPosition = new Vector3i();
        //System.out.println("Indice :"+ indice);
        int x = indice % width;
        int reste = (indice - x)/width;
        int z = reste % length;
        int y = (reste - z)/length;

        blockPosition.x = x;
        blockPosition.y = y;
        blockPosition.z = z;

        return blockPosition;
    }

    public void setMappingStruc(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("SchematicaMapping")) {
            CompoundTag mapping = (CompoundTag) compoundTag.getValue().get("SchematicaMapping");
            for (Map.Entry<String, Tag> entry : mapping.getValue().entrySet()) {
                ShortTag minecraftBloc = (ShortTag) entry.getValue();

                String blockName = minecraftBloc.getName();
                blockName = blockName.replaceAll("minecraft:", "");
                int blokcID = minecraftBloc.getValue();

                Material blockMaterial = Material.getMaterialByName(blockName);
                if(blockMaterial != null && !mappingStruc.containsKey(blockMaterial)) {
                    mappingStruc.put(blokcID, blockMaterial);
                } else if(blockMaterial == null){
                    mappingStruc.put(blokcID, Material.DEBUG);
                }

            }
        }
    }

    public HashMap<Integer, Material> getMappingStruc() {
        return mappingStruc;
    }
}
