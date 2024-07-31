package fr.math.minecraft.server.api;

import fr.math.minecraft.server.api.mapper.RegisterServerApiMapper;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RegisterServerApi extends ApiTemplate<Server> {

    public RegisterServerApi() {
        super(new RegisterServerApiMapper());
    }


    @Override
    public String getUrl() {
        return "/api/server";
    }

    @Override
    public void handleRequest(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod(this.getMethod());
        connection.setRequestProperty("Accept", "application/json");
    }

    @Override
    public String getMethod() {
        return "POST";
    }

}
