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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.shared.Utils;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClientMain {

    private final static Logger logger = LoggerUtility.getClientLogger(ClientMain.class, LogType.TXT);

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "true");
        String skinPath = "res/textures/skin.png";
        String serverIp = "localhost";
        int serverPort = 50000;
        Float seedNumber = 0f;
        if (args.length == 2) {
            if (!args[0].equalsIgnoreCase("--name")) {
                throw new IllegalArgumentException("Veuillez renseigner un pseudo ! (--name <pseudo>)");
            }
        } else if (args.length == 4) {
            if (!args[0].equalsIgnoreCase("--name")) {
                throw new IllegalArgumentException("Veuillez renseigner un pseudo ! (--name <pseudo>)");
            }
            if (!args[2].equalsIgnoreCase("--skin")) {
                throw new IllegalArgumentException("Veuillez renseigner un skin ! (--skin chemin/vers/skin.png)");
            }
            skinPath = args[3];
        } else {
            throw new IllegalArgumentException("Veuillez renseigner un pseudo et/ou un skin ! (--name <pseudo> --skin chemin/vers/skin.png)");
        }

        if (!new File(skinPath).exists()) {
            throw new IllegalArgumentException("Le fichier spécifié est introuvable ! " + skinPath);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode configNode = mapper.readTree(new File("res/client_config.json"));
            JsonNode portNode = configNode.get("port");
            JsonNode ipNode = configNode.get("ip");

            if (portNode == null || ipNode == null) {
                throw new IOException();
            }

            serverPort = portNode.asInt();
            serverIp = ipNode.asText();
        } catch (IOException e) {
            logger.error("Impossible de lire le fichier de configuration! Parametres par défaut défini");
        }

        logger.info("IP SERVEUR : " + serverIp + ":" + serverPort);
        Game game = Game.getInstance();
        game.setPlayer(new Player(args[1]));
        game.getPlayer().setSkinPath(skinPath);
        game.init(serverIp, serverPort);
        game.run();
    }

}
