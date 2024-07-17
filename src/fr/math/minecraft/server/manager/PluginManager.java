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

package fr.math.minecraft.server.manager;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Plugin;
import org.apache.log4j.Logger;

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
