package fr.math.minecraft.client.network.packet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.Game;
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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

            AuthUser user = new AuthUser(username, email, token);
            Game.getInstance().setUser(user);

            menuManager.open(MainMenu.class);

        } catch (IOException e) {
            menuManager.open(RetryAuthMenu.class);
            RetryAuthMenu menu = (RetryAuthMenu) menuManager.getOpenedMenu();
            menu.getSubTitle().setText("Email ou mot de passe incorrect.");
            logger.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.send();
        button.setPending(false);
    }
}
