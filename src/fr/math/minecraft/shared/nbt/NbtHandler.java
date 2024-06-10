package fr.math.minecraft.shared.nbt;

import fr.math.minecraft.shared.world.Material;
import org.jnbt.*;
import org.joml.Vector3i;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NbtHandler {

    private String filePath;
    private Tag mainTag;

    private HashMap<Integer, Material> mappingStruc;

    public NbtHandler(String filePath) {
        this.filePath = filePath;
        try (FileInputStream fis = new FileInputStream(filePath)){
            NBTInputStream nbtInputStream = new NBTInputStream(fis);
            this.mainTag = nbtInputStream.readTag();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mappingStruc = new HashMap<>();
    }

    public CompoundTag nbtMainCompoud() {
        if(mainTag instanceof CompoundTag) {
            CompoundTag compound = (CompoundTag) mainTag;
            return compound;
        } else {
            System.out.println("Le ficher nbt ne contient pas de Tag Compound");
            return null;
        }
    }

    public CompoundTag nbtMainCompoud(Tag mainTag) {
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

    public Vector3i getBlockPosition(int indice, short length, short width){
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
