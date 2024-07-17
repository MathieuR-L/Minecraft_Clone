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

package fr.math.minecraft;

import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.Plugin;
import fr.math.minecraft.server.pathfinding.AStar;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.world.World;
import org.joml.Vector3f;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        int seed = 0;
        int port = 50000;
        if(args.length==2){
            if (!args[0].equalsIgnoreCase("-p")) {
                throw new IllegalArgumentException("Veuillez renseigner un port ! (-p <port>)");
            }
            try {
                port = Integer.parseInt(args[1]);
            } catch(Exception e){
                port = 50000;
            }
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-seed")) {
                seed = Integer.parseInt(args[i + 1]);
            }
        }

        MinecraftServer server = MinecraftServer.getInstance();
        server.setPort(port);
        World world = server.getWorld();
        world.setSeed(seed);
        world.calculateSpawnPosition();

        Signal.handle(new Signal("INT"), new ExitHandler(server));
        Signal.handle(new Signal("TERM"), new ExitHandler(server));

        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static class ExitHandler implements SignalHandler {

        private final MinecraftServer server;

        public ExitHandler(MinecraftServer server) {
            this.server = server;
        }

        @Override
        public void handle(Signal sig) {
            for (Plugin plugin : server.getPluginManager().getPlugins()) {
                plugin.onDisable();
            }
            server.setRunning(false);
        }
    }
}
