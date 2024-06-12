package fr.math.minecraft.server.command;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.nbt.NbtHandler;
import fr.math.minecraft.shared.world.Material;
import org.apache.log4j.Logger;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class LoadSchematicCommand extends Command{

    private final static Logger logger = LoggerUtility.getServerLogger(TeleportCommand.class, LogType.TXT);

    private NbtHandler nbtHandler;
    private String filePath = "res/schematics/";
    private CompoundTag compoundTag;
    private int arrayLength;
    private ByteArrayTag byteArrayTag;
    private ShortTag length, width;

    public LoadSchematicCommand(String name, String descpription, Team team) {
        super(name, descpription, team);
    }

    @Override
    public void run(String[] message, String sender, Client client, MinecraftServer server) {

        logger.debug("Je run 1 fois le [LOAD]");

        filePath += message[1];

        logger.debug("Fichier à ouvrir :" + filePath);

        nbtHandler = new NbtHandler(filePath);
        compoundTag = nbtHandler.getCompoundTag();
        nbtHandler.setMappingStruc(compoundTag);

        byteArrayTag = nbtHandler.getNbtBlocksArray(compoundTag);
        arrayLength = byteArrayTag.getValue().length;

        Vector3f senderPosition = client.getPosition();
        senderPosition.x = (int) senderPosition.x;
        senderPosition.y = (int) senderPosition.y;
        senderPosition.z = (int) senderPosition.z;

        length = nbtHandler.getNbtLength(compoundTag);
        width = nbtHandler.getNbtWidth(compoundTag);

        for (int i = 1; i < arrayLength; i++) {
            int element = byteArrayTag.getValue()[i];
            Material currentMaterial = nbtHandler.getMappingStruc().get(element);

            Vector3i blockPosition = nbtHandler.getBlockPosition(i, length.getValue(), width.getValue());

            server.getWorld().setBlock(blockPosition, currentMaterial.getId());
            logger.debug("Block de " + currentMaterial + "placé en :" + blockPosition);
        }
    }
}
