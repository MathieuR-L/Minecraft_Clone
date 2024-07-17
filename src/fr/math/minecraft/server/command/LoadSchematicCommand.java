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
import fr.math.minecraft.shared.nbt.NbtHandler;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.PlacedBlock;
import org.apache.log4j.Logger;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;
import org.joml.Vector3i;
import java.util.ArrayList;

public class LoadSchematicCommand extends Command{

    private final static Logger logger = LoggerUtility.getServerLogger(LoadSchematicCommand.class, LogType.TXT);
    private NbtHandler nbtHandler;
    private String filePath;
    private CompoundTag compoundTag;
    private int segmentationNumber;
    private ByteArrayTag byteArrayTag;
    private ShortTag length, width;
    private ArrayList<PlacedBlock> placedBlocs;
    private int x, y, z;
    public final static int maxBlocPerList = 1000;


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

        System.out.println(nbtHandler.getMappingStruc());

        ArrayList<ArrayList<Integer>> segmenttationList = nbtHandler.getCleanNbtBlocksArray(compoundTag);
        segmentationNumber = segmenttationList.size();
        System.out.println("Nombre de seg" + segmentationNumber);
        length = nbtHandler.getNbtLength(compoundTag);
        width = nbtHandler.getNbtWidth(compoundTag);
        ByteArrayTag dataArray = nbtHandler.getNbtDataArray(compoundTag);

        for (int j = 0; j < segmentationNumber; j++) {
            ArrayList<Integer> blockList = segmenttationList.get(j);
            for (int i = 0; i < blockList.size(); i++) {
                Material currentMaterial;
                int indice = j*maxBlocPerList + i;
                int element = blockList.get(i);
                if(element < 0) continue;
                if(nbtHandler.getMappingStruc().get(""+element) != null) {
                    currentMaterial = nbtHandler.getMappingStruc().get(""+element);
                } else {
                    String elementVariante = "" + element + ":" + dataArray.getValue()[indice];
                    currentMaterial = nbtHandler.getMappingStruc().get(elementVariante);
                }

                if(currentMaterial == Material.BLACK_CONCRETE) {
                    System.out.println("COUCOUCUUU" + "Texture:" + currentMaterial.getX() + "|" + currentMaterial.getY());
                }

                if(currentMaterial == null) {
                    currentMaterial = Material.DEBUG;
                }
                if(currentMaterial == Material.AIR) continue;
                Vector3i blockPosition = nbtHandler.getBlockPosition(i + (j*maxBlocPerList), length.getValue(), width.getValue());
                Vector3i blockWorldPosition = new Vector3i(blockPosition.x + x, blockPosition.y + y, blockPosition.z + z);

                Vector3i blockLocalPosition = Utils.worldToLocal(blockWorldPosition);

                PlacedBlock placedBlock = new PlacedBlock(client.getUuid(), blockWorldPosition, blockLocalPosition, currentMaterial.getId());

                Chunk aimedChunk = server.getWorld().getChunkAt(blockWorldPosition);

                if(aimedChunk == null) {
                    Vector3i chunkPos = Utils.getChunkPosition(blockWorldPosition.x, blockWorldPosition.y, blockWorldPosition.z);
                    aimedChunk = new Chunk(chunkPos.x, chunkPos.y, chunkPos.z);
                    System.out.println("Génération d'un chunk...");
                    aimedChunk.generate(server.getWorld(), server.getWorld().getTerrainGenerator());
                    server.getWorld().addChunk(aimedChunk);
                }
                synchronized (server.getWorld().getPlacedBlocks()) {
                    server.getWorld().getPlacedBlocks().put(placedBlock.getWorldPosition(), placedBlock);
                }
                aimedChunk.setBlock(blockLocalPosition.x, blockLocalPosition.y, blockLocalPosition.z, placedBlock.getBlock());

            }
        }




    }
}
