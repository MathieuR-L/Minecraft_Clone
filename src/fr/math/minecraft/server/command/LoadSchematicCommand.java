package fr.math.minecraft.server.command;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.Utils;
import fr.math.minecraft.shared.nbt.NbtHandler;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.PlacedBlock;
import org.apache.log4j.Logger;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadSchematicCommand extends Command{

    private final static Logger logger = LoggerUtility.getServerLogger(TeleportCommand.class, LogType.TXT);
    private NbtHandler nbtHandler;
    private String filePath;
    private CompoundTag compoundTag;
    private int arrayLength;
    private ByteArrayTag byteArrayTag;
    private ShortTag length, width;
    private ArrayList<PlacedBlock> placedBlocs;
    private int x, y, z;

    public LoadSchematicCommand(String name, String descpription, Team team) {
        super(name, descpription, team);
        placedBlocs = new ArrayList<>();
    }

    @Override
    public void run(String[] message, String sender, Client client, MinecraftServer server) {
        placedBlocs.clear();
        logger.debug("Je run 1 fois le [LOAD]");
        filePath = "res/schematics/";
        filePath += message[1];
        filePath += ".schematic";

        String newPosition = message[2];
        String[] coordinates = newPosition.split(":");
        x = Integer.parseInt(coordinates[0]);
        y = Integer.parseInt(coordinates[1]);
        z = Integer.parseInt(coordinates[2]);


        logger.debug("Fichier à ouvrir :" + filePath);

        nbtHandler = new NbtHandler(filePath);
        compoundTag = nbtHandler.getCompoundTag();
        nbtHandler.setMappingStruc(compoundTag);

        ArrayList<Byte> blockList = nbtHandler.getCleanNbtBlocksArray(compoundTag);
        arrayLength = blockList.size();

        length = nbtHandler.getNbtLength(compoundTag);
        width = nbtHandler.getNbtWidth(compoundTag);

        for (int i = 0; i < arrayLength; i++) {
            int element = blockList.get(i);

            Material currentMaterial = nbtHandler.getMappingStruc().get(element);

            Vector3i blockPosition = nbtHandler.getBlockPosition(i, length.getValue(), width.getValue());
            System.out.println("Bloc pos :" + blockPosition);
            Vector3i blockWorldPosition = new Vector3i(blockPosition.x + x, blockPosition.y + y, blockPosition.z + z);

            Vector3i blockLocalPosition = Utils.worldToLocal(blockWorldPosition);

            PlacedBlock placedBlock = new PlacedBlock(client.getUuid(), blockWorldPosition, blockLocalPosition, currentMaterial.getId());

            synchronized (server.getWorld().getPlacedBlocks()) {
                server.getWorld().getPlacedBlocks().put(placedBlock.getWorldPosition(), placedBlock);
                Chunk aimedChunk = server.getWorld().getChunkAt(blockWorldPosition);
                aimedChunk.setBlock(blockLocalPosition.x, blockLocalPosition.y, blockLocalPosition.z, placedBlock.getBlock());
            }


            logger.debug("Block de " + currentMaterial + " ajouté à la liste en :" + blockWorldPosition);
        }




    }
}
