package fr.math.minecraft.shared.nbt;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Utils;
import fr.math.minecraft.shared.Utils.*;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.PlacedBlock;
import fr.math.minecraft.shared.world.World;
import org.apache.log4j.Logger;
import org.jnbt.*;
import org.joml.Vector3i;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NbtHandler {

    private final static Logger logger = LoggerUtility.getServerLogger(NbtHandler.class, LogType.TXT);

    private String filePath;
    private Tag mainTag;
    public final static int maxBlocPerList = 1000;

    private HashMap<String, Material> mappingStruc;

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

    public ByteArrayTag getNbtDataArray(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("Blocks")) {
            ByteArrayTag blocksArray = (ByteArrayTag) compoundTag.getValue().get("Data");
            return blocksArray;
        } else {
            return null;
        }
    }
    public ArrayList<ArrayList<Integer>> getCleanNbtBlocksArray(CompoundTag compoundTag) {
        if(compoundTag.getValue().containsKey("Blocks")) {
            ByteArrayTag blocksArray = (ByteArrayTag) compoundTag.getValue().get("Blocks");

            int size = blocksArray.getValue().length;
            int segmentationNumber = size / maxBlocPerList;
            int reste = size % maxBlocPerList;

            ArrayList<ArrayList<Integer>> segmentationList = new ArrayList<>();
            for (int i = 0; i < segmentationNumber; i++) {
                ArrayList<Integer> blocks = new ArrayList<>();
                for (int j = 0; j < maxBlocPerList; j++) {
                    blocks.add(Byte.toUnsignedInt(blocksArray.getValue()[j + i*maxBlocPerList]));
                }
                segmentationList.add(blocks);
            }
            if(reste != 0 && segmentationNumber != 0) {
                ArrayList<Integer> lastBlocks = new ArrayList<>();
                for (int i = segmentationNumber*maxBlocPerList - 1; i < reste + (segmentationNumber*maxBlocPerList - 1); i++) {
                    lastBlocks.add(Byte.toUnsignedInt(blocksArray.getValue()[i]));
                }
                segmentationList.add(lastBlocks);
            } else {
                ArrayList<Integer> littleSchem = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    littleSchem.add(Byte.toUnsignedInt(blocksArray.getValue()[i]));
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
                ArrayList<Material> materialsVariant = Material.getMaterialByName(blockName);
                for (Material materialInList : materialsVariant) {
                    String materialName = materialInList.getName();
                    String blockID = "" + blokcID;
                    if(materialName.contains(":")) {
                        String variante = materialName.split(":")[1];
                        blockID = "" + blokcID + ":" + variante;
                    }
                    if(!mappingStruc.containsKey(blockID)) {
                        mappingStruc.put(blockID, materialInList);
                    }
                }


            }
        }
    }

    public void loadSchematic(World world) {
        CompoundTag compoundTag = this.getCompoundTag();
        this.setMappingStruc(compoundTag);
        logger.info(this.getMappingStruc());

        ArrayList<ArrayList<Integer>> segmentationList = this.getCleanNbtBlocksArray(compoundTag);
        int segmentationNumber = segmentationList.size();
        ShortTag lenght = this.getNbtLength(compoundTag);
        ShortTag width = this.getNbtWidth(compoundTag);
        ByteArrayTag dataArray = this.getNbtDataArray(compoundTag);

        for (int i = 0; i < segmentationNumber; i++) {
            ArrayList<Integer> blockList = segmentationList.get(i);
            for (int j = 0; j < blockList.size(); j++) {
                Material currentMaterial;
                int element = blockList.get(j);
                if(element < 0) continue;
                if(this.getMappingStruc().get(""+element) != null) {
                    currentMaterial = this.getMappingStruc().get(""+element);
                } else {
                    String elementVariant = "" + element + ":" + dataArray.getValue()[(i * blockList.size()) + j];
                    currentMaterial = this.getMappingStruc().get(elementVariant);
                }

                if(currentMaterial == null) currentMaterial = Material.DEBUG;
                if(currentMaterial == Material.AIR) continue;

                Vector3i blockPosition = this.getBlockPosition(j + (i*maxBlocPerList), lenght.getValue(), width.getValue());
                Vector3i blockWorldPosition = new Vector3i(blockPosition.x, blockPosition.y, blockPosition.z);
                Vector3i blockLocalPosition = Utils.worldToLocal(blockWorldPosition);

                PlacedBlock placedBlock = new PlacedBlock("Server", blockWorldPosition, blockLocalPosition, currentMaterial.getId());


                Chunk aimedChunk = world.getChunkAt(blockWorldPosition);

                if(aimedChunk == null) {
                    Vector3i chunkPos = Utils.getChunkPosition(blockWorldPosition.x, blockWorldPosition.y, blockWorldPosition.y);
                    aimedChunk = new Chunk(chunkPos.x, chunkPos.y, chunkPos.z);
                    logger.debug("Génération d'un chunk");
                    aimedChunk.generate(world, world.getTerrainGenerator());
                    world.addChunk(aimedChunk);
                }
                world.getPlacedBlocks().put(placedBlock.getWorldPosition(), placedBlock);

                aimedChunk.setBlock(blockLocalPosition.x, blockLocalPosition.y, blockLocalPosition.z, currentMaterial.getId());
            }
        }
    }

    public HashMap<String, Material> getMappingStruc() {
        return mappingStruc;
    }
}
