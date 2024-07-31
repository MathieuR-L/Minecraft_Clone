package fr.math.minecraft.server.api;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.ServerConfiguration;
import fr.math.minecraft.server.api.mapper.ApiMapper;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ApiTemplate<T> {

    private final static Logger logger = LoggerUtility.getServerLogger(RegisterServerApi.class, LogType.TXT);
    private ApiMapper<T> apiMapper;

    public ApiTemplate(ApiMapper<T> apiMapper) {
        this.apiMapper = apiMapper;
    }

    private HttpURLConnection createConnection(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        this.handleRequest(connection);
        return connection;
    }

    public T execute() {
        HttpURLConnection connection = null;
        try {
            String endpointUrl = ServerConfiguration.getInstance().getApiEndpoint() + this.getUrl();
            connection = this.createConnection(endpointUrl);
            if (connection.getResponseCode() != 200) {
                logger.warn(this.getMethod() + " " + this.getUrl() + " " + connection.getResponseCode() + " " + connection.getResponseMessage());
            } else {
                logger.info(this.getMethod() + " " + this.getUrl() + " " + connection.getResponseCode() + " " + connection.getResponseMessage());
            }
            String responseData = this.handleResponse(connection);
            return apiMapper.parseData(responseData);
        } catch (IOException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public String handleResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    public abstract void handleRequest(HttpURLConnection connection) throws IOException;
    public abstract String getUrl();
    public abstract String getMethod();
}
