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

package fr.math.minecraft.client.handler;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.network.packet.PingPacket;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.client.manager.WorldManager;
import fr.math.minecraft.shared.world.World;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class TickHandler extends Thread {

    public TickHandler() {
        this.setName("TickHandler");
    }

    @Override
    public void run() {

        Game game = Game.getInstance();
        WorldManager worldManager = game.getWorldManager();
        World world = game.getWorld();
        double lastTime = glfwGetTime();
        double lastPingTime = glfwGetTime();
        double tickTimer = 0;

        while (!glfwWindowShouldClose(game.getWindow())) {

            double currentTime = glfwGetTime();
            double deltaTime = currentTime - lastTime;

            if (currentTime - lastPingTime >= 1.0) {
                new PingPacket().send();
                lastPingTime = currentTime;
            }

            tickTimer += deltaTime;

            lastTime = currentTime;

            while (tickTimer >= GameConfiguration.TICK_RATE) {
                worldManager.loadChunks(world);
                tickTimer -= GameConfiguration.TICK_RATE;
            }
        }
    }
}
