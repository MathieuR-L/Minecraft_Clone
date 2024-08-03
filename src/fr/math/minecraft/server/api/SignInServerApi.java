package fr.math.minecraft.server.api;

import fr.math.minecraft.server.ServerConfiguration;
import fr.math.minecraft.server.api.mapper.SignInServerApiMapper;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SignInServerApi extends ApiTemplate<Server> {

    public SignInServerApi() {
        super(new SignInServerApiMapper());
    }


    @Override
    public String getUrl() {
        return "/api/auth/server";
    }

    @Override
    public void handleRequest(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod(this.getMethod());
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + ServerConfiguration.getInstance().getAuthToken());
    }

    @Override
    public String getMethod() {
        return "POST";
    }

}
