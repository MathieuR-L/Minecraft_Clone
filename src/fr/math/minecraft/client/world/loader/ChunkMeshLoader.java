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

package fr.math.minecraft.client.world.loader;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.client.world.worker.ChunkMeshWorker;

import static org.lwjgl.glfw.GLFW.*;

public class ChunkMeshLoader extends Thread {

    private final Game game;

    public ChunkMeshLoader(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (!glfwWindowShouldClose(game.getWindow())) {
            Chunk chunk;
            synchronized (game.getPendingMeshs()) {
                if (game.getPendingMeshs().isEmpty()) {
                    continue;
                }
                chunk = game.getPendingMeshs().poll();
            }
            ChunkMeshWorker worker = new ChunkMeshWorker(chunk);
            //worker.run();
            game.getChunkMeshLoadingQueue().submit(worker);

        }
    }

}
