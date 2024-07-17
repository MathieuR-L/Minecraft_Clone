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

package fr.math.minecraft.server.command;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.Utils;
import fr.math.minecraft.shared.world.Chunk;
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

        synchronized (server.getWorld().getPlacedBlocks()) {
            server.getWorld().getPlacedBlocks().put(placedBlock.getWorldPosition(), placedBlock);
            Chunk aimedChunk = server.getWorld().getChunkAt(worldPosition);
            if(aimedChunk == null) {
                Vector3i chunkPos = Utils.getChunkPosition(worldPosition.x, worldPosition.y, worldPosition.z);
                aimedChunk = new Chunk(chunkPos.x, chunkPos.y, chunkPos.z);
                System.out.println("Génération d'un chunk...");
                aimedChunk.generate(server.getWorld(), server.getWorld().getTerrainGenerator());
                server.getWorld().addChunk(aimedChunk);
            }
            aimedChunk.setBlock(worldPosition.x, worldPosition.y, worldPosition.z, placedBlock.getBlock());
        }

        logger.trace("Le joueur " + client.getName() + " a [TP] le joueur " + message[1] + " en : [x:" + x +" y:" + y + " z:" + z +"]");
    }

}
