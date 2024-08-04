package fr.math.minecraft.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ServerConfiguration {

    private String environment;
    private String apiEndpoint, authEndpoint;
    private String authToken;
    private String storeType, keyStore, storePassword, keyPassword;
    private static ServerConfiguration instance = null;
    private static Logger logger = LoggerUtility.getServerLogger(ServerConfiguration.class, LogType.TXT);

    private ServerConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        this.environment = "development";
        this.apiEndpoint = "localhost:3000";
        this.authEndpoint = "localhost:3001";
        this.authToken = "";
        this.keyPassword = "";
        this.storePassword = "";
        this.storeType = "";
        this.keyStore = "";
        try {
            JsonNode configuration = mapper.readTree(new File("server-config.json"));
            JsonNode environmentNode = configuration.get("PROJECT_ENVIRONMENT");
            JsonNode apiNode = configuration.get("API_ENDPOINT");
            JsonNode authNode = configuration.get("AUTH_ENDPOINT");
            JsonNode authTokenNode = configuration.get("AUTH_TOKEN");
            JsonNode storeTypeNode = configuration.get("STORE_TYPE");
            JsonNode keyStoreNode = configuration.get("KEYSTORE");
            JsonNode storePasswordNode = configuration.get("STORE_PASSWORD");
            JsonNode keyPasswordNode = configuration.get("KEY_PASSWORD");

            if (environmentNode != null) {
                this.environment = environmentNode.asText();
            }
            if (apiNode != null) {
                this.apiEndpoint = apiNode.asText();
            }
            if (authNode != null) {
                this.authEndpoint = authNode.asText();
            }
            if (authTokenNode != null) {
                this.authToken = authTokenNode.asText();
            }
            if (storeTypeNode != null) {
                this.storeType = storeTypeNode.asText();
            }
            if (keyStoreNode != null) {
                this.keyStore = keyStoreNode.asText();
            }
            if (storePasswordNode != null) {
                this.storePassword = storePasswordNode.asText();
            }
            if (keyPasswordNode != null) {
                this.keyPassword = keyPasswordNode.asText();
            }

            logger.info("Configuration chargée avec succès");
        } catch (IOException e) {
            logger.warn("Impossible de charger la configuration, les paramètres par défaut s'appliquent.");
        }
    }

    public static ServerConfiguration getInstance() {
        if (instance == null) {
            instance = new ServerConfiguration();
        }
        return instance;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public String getAuthEndpoint() {
        return authEndpoint;
    }

    public String getStoreType() {
        return storeType;
    }

    public String getKeyStore() {
        return keyStore;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public String getKeyPassword() {
        return keyPassword;
    }
}
