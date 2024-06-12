package fr.math.minecraft.server.command;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.Utils;
import fr.math.minecraft.shared.world.PlacedBlock;
import org.apache.log4j.Logger;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class PlaceBlockCommand extends Command{

    private final static Logger logger = LoggerUtility.getServerLogger(TeleportCommand.class, LogType.TXT);

    private int x, y, z;

    public PlaceBlockCommand(String name, String descpription, Team team) {
        super(name, descpription, team);
    }

    @Override
    public void run(String[] message, String sender, Client client, MinecraftServer server) {

        logger.debug("Je run 1 fois le [PlaceBlock]");

        String newPosition = message[2];
        logger.debug("Bloc 2 du msg:" + newPosition);

        String[] coordinates = newPosition.split(":");
        x = Integer.parseInt(coordinates[0]);
        y = Integer.parseInt(coordinates[1]);
        z = Integer.parseInt(coordinates[2]);

        byte blockID = Byte.parseByte(message[1]);

        Vector3i worldPosition = new Vector3i(x, y, z);

        Vector3i localPosition = Utils.worldToLocal(worldPosition);

        PlacedBlock placedBlock = new PlacedBlock(client.getUuid(), worldPosition, localPosition, blockID);

        logger.trace("Le joueur " + client.getName() + " a [TP] le joueur " + message[1] + " en : [x:" + x +" y:" + y + " z:" + z +"]");
    }

}
