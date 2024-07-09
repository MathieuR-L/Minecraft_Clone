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
        world.buildSpawn();
        //world.buildMap(GameConfiguration.MAP_FILE_PATH);
        world.calculateSpawnPosition();
        //AStar.initGraph(world, world.getSpawnPosition());

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
