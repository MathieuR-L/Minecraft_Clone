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
    private static ServerConfiguration instance = null;
    private static Logger logger = LoggerUtility.getServerLogger(ServerConfiguration.class, LogType.TXT);

    private ServerConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode configuration = mapper.readTree(new File("server-config.json"));
            this.environment = configuration.get("PROJECT_ENVIRONMENT").asText();

            if (this.environment.equalsIgnoreCase("PRODUCTION")) {
                this.apiEndpoint = configuration.get("API_ENDPOINT").asText();
                this.authEndpoint = configuration.get("AUTH_ENDPOINT").asText();
            } else {
                this.apiEndpoint = "localhost:3000";
                this.authEndpoint = "localhost:3001";
            }
            logger.info("Configuration chargée avec succès");
        } catch (IOException e) {
            logger.warn("Impossible de charger la configuration, les paramètres par défaut s'appliquent.");
            this.environment = "unknown";
            this.apiEndpoint = "localhost:3000";
            this.authEndpoint = "localhost:3001";
        }
    }

    public static ServerConfiguration getInstance() {
        if (instance == null) {
            instance = new ServerConfiguration();
        }
        return instance;
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
}
