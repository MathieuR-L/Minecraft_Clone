package fr.math.minecraft.server.manager;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Plugin;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginManager {

    private final List<Plugin> plugins;
    private static final Logger logger = LoggerUtility.getServerLogger(PluginManager.class, LogType.TXT);

    public PluginManager() {
        this.plugins = new ArrayList<>();
    }

    public void loadPlugins(String pluginsDirectory) throws Exception {
        File directory = new File(pluginsDirectory);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    URLClassLoader classLoader = new URLClassLoader(new URL[]{ file.toURI().toURL() });
                    Class<?> pluginClass = classLoader.loadClass("fr.math.plugin.Main");
                    Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
                    plugins.add(plugin);

                    plugin.onEnable();
                    logger.info("Plugin " + plugin + " chargé ");
                }
            }
        }
    }

    public void invokePlayerJoin(int onlinePlayers) {
        for (Plugin plugin : plugins) {
            plugin.onPlayerJoin(onlinePlayers);
        }
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }
}
