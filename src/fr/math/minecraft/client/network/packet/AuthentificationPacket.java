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

package fr.math.minecraft.client.network.packet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.texture.Texture;
import fr.math.minecraft.shared.Utils;
import fr.math.minecraft.client.gui.buttons.LoginButton;
import fr.math.minecraft.client.gui.menus.MainMenu;
import fr.math.minecraft.client.gui.menus.RetryAuthMenu;
import fr.math.minecraft.client.manager.MenuManager;
import fr.math.minecraft.client.network.AuthUser;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.shared.network.HttpResponse;
import fr.math.minecraft.shared.network.HttpUtils;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class AuthentificationPacket extends ClientPacket implements Runnable {

    private final String email;
    private final String password;
    private final LoginButton button;
    private final static Logger logger = LoggerUtility.getClientLogger(AuthentificationPacket.class, LogType.TXT);

    public AuthentificationPacket(LoginButton button, String email, String password) {
        this.email = email;
        this.password = password;
        this.button = button;
    }

    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public String getResponse() {
        return null;
    }

    @Override
    public synchronized void send() {
        MenuManager menuManager = Game.getInstance().getMenuManager();
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode credentialsNode = mapper.createObjectNode();

            credentialsNode.put("email", email);
            credentialsNode.put("password", password);

            logger.info("Interrogation du serveur d'authentification...");

            HttpResponse httpResponse = HttpUtils.POST("http://localhost:3001/auth/login", credentialsNode);

            logger.info("Code de réponse du serveur d'authentification : " + httpResponse.getCode());

            JsonNode response = mapper.readTree(httpResponse.getResponse().toString());
            logger.info("Réponse : " + httpResponse.getResponse());

            JsonNode userData = mapper.readTree(response.toString()).get("user");

            String username = userData.get("name").asText();
            String email = userData.get("email").asText();
            String token = userData.get("token").asText();
            String skinUrl = userData.get("skin").get("link").asText();

            logger.info("Skin fourni par le serveur d'authentification : " + skinUrl);

            BufferedImage skinImage = Utils.loadBase64Skin(skinUrl);
            Texture skinTexture = new Texture(skinImage, 6);
            Game.getInstance().getPlayer().setSkinTexture(skinTexture);

            AuthUser user = new AuthUser(username, email, skinUrl, token);
            Game.getInstance().setUser(user);

            menuManager.open(MainMenu.class);

        } catch (Exception e) {
            menuManager.open(RetryAuthMenu.class);
            RetryAuthMenu menu = (RetryAuthMenu) menuManager.getOpenedMenu();
            menu.getSubTitle().setText("Email ou mot de passe incorrect.");
            logger.error(e.getMessage());
        }
    }

    @Override
    public void run() {
        this.send();
        button.setPending(false);
    }
}
