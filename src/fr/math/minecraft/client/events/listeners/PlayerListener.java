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

package fr.math.minecraft.client.events.listeners;

import com.fasterxml.jackson.databind.JsonNode;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.audio.Sounds;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.events.BlockBreakEvent;
import fr.math.minecraft.client.events.BlockPlaceEvent;
import fr.math.minecraft.client.events.ItemGiveEvent;
import fr.math.minecraft.client.events.PlayerJoinEvent;
import fr.math.minecraft.client.events.PlayerMoveEvent;
import fr.math.minecraft.client.manager.ChunkManager;
import fr.math.minecraft.client.manager.SoundManager;
import fr.math.minecraft.client.manager.WorldManager;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Utils;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.World;
import org.apache.log4j.Logger;
import org.joml.Vector3i;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class PlayerListener implements EventListener {

    private final WorldManager worldManager;
    private final Game game;
    private static final Logger logger = LoggerUtility.getClientLogger(PlayerListener.class, LogType.TXT);

    public PlayerListener(Game game) {
        this.game = game;
        this.worldManager = game.getWorldManager();
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
        // worldManager.loadChunks();
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {

        JsonNode playerData = event.getPlayerData().get("playerData");
        String playerName = playerData.get("name").asText();
        String uuid = playerData.get("uuid").asText();
        String base64Skin = playerData.get("skin").asText();
        Game game = Game.getInstance();

        logger.info(playerName + " a rejoint le serveur !");

        Player player = new Player(playerName);
        player.setUuid(uuid);

        byte[] skinBytes = Base64.getDecoder().decode(base64Skin);
        try {
            BufferedImage skin = ImageIO.read(new ByteArrayInputStream(skinBytes));
            player.setSkin(skin);
            synchronized (game.getPlayers()) {
                game.getPlayers().put(uuid, player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        ChunkManager chunkManager = new ChunkManager();
        Vector3i blockPosition = Utils.worldToLocal(event.getPosition());
        chunkManager.removeBlock(Game.getInstance().getWorld().getChunkAt(event.getPosition()), blockPosition, Game.getInstance().getWorld());
        logger.info("Le joueur :" + event.getPlayer().getName() + " a cassé un bloc en :" + event.getPosition());
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        ChunkManager chunkManager = new ChunkManager();
        Vector3i blockPosition = Utils.worldToLocal(event.getPosition());
        World world = game.getWorld();
        chunkManager.placeBlock(world.getChunkAt(event.getPosition()), blockPosition, Game.getInstance().getWorld(), event.getMaterial());
        logger.info("Le joueur :" + event.getPlayer().getName() + " a placé un bloc en :" + event.getPosition());
    }

    @Override
    public void onItemGive(ItemGiveEvent event) {
        World world = game.getWorld();
        Player player = game.getPlayer();
        SoundManager soundManager = game.getSoundManager();

        soundManager.play(Sounds.POP);

        synchronized (world.getDroppedItems()) {
            world.getDroppedItems().remove(event.getDroppedItemId());
        }

        player.addItem(event.getItem());
    }

}
