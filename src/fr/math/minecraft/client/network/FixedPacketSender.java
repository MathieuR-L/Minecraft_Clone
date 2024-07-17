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

package fr.math.minecraft.client.network;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.network.packet.ClientPacket;
import fr.math.minecraft.shared.GameConfiguration;

import java.util.LinkedList;
import java.util.Queue;
import static org.lwjgl.glfw.GLFW.*;

public class FixedPacketSender extends Thread {

    private static FixedPacketSender instance = null;
    private final Queue<ClientPacket> packetsQueue;
    private int ping;

    private FixedPacketSender() {
        this.setName("PacketHandler");
        this.packetsQueue = new LinkedList<>();
        this.ping = 0;
    }

    @Override
    public void run() {
        Game game = Game.getInstance();
        Player player = game.getPlayer();
        double lastPingTime = System.currentTimeMillis();
        long lastTickTime = System.currentTimeMillis();
        double tickTimer = 0;
        while (!glfwWindowShouldClose(game.getWindow())) {

            long currentTime = System.currentTimeMillis();
            long deltaTime = currentTime - lastTickTime;

            if (currentTime - lastPingTime >= 1000) {
                lastPingTime = currentTime;
            }

            tickTimer += deltaTime;

            lastTickTime = currentTime;
            tick();

            while (tickTimer >= 1000 / GameConfiguration.TICK_PER_SECONDS) {
                tickTimer -= 1000 / GameConfiguration.TICK_PER_SECONDS;
            }
        }
    }

    private void tick() {

        while (!packetsQueue.isEmpty()) {
            ClientPacket packet = packetsQueue.poll();
            if (packet == null) return;
            packet.send();
        }
    }

    public static FixedPacketSender getInstance() {
        if (instance == null) {
            instance = new FixedPacketSender();
        }
        return instance;
    }

    public synchronized void enqueue(ClientPacket packet) {
        //packetsQueue.add(packet);
        packet.send();
    }

    public Queue<ClientPacket> getPacketsQueue() {
        return packetsQueue;
    }
}
