package fr.math.minecraft.server.api;

import fr.math.minecraft.server.ServerConfiguration;
import fr.math.minecraft.server.api.mapper.VoidApiMapper;
import fr.math.minecraft.server.websockets.ServerStatus;

import java.io.IOException;
import java.net.HttpURLConnection;

public class UpdateServerApi extends ApiTemplate<Void> {

    private final int serverId;
    private final ServerStatus serverStatus;

    public UpdateServerApi(int serverId, ServerStatus serverStatus) {
        super(new VoidApiMapper());
        this.serverId = serverId;
        this.serverStatus = serverStatus;
    }

    @Override
    public void handleRequest(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod(this.getMethod());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + ServerConfiguration.getInstance().getAuthToken());
        connection.setDoOutput(true);

        String statusData = serverStatus.toJson();
        connection.getOutputStream().write(statusData.getBytes());
    }

    @Override
    public String getUrl() {
        return "/api/servers/" + this.serverId;
    }

    @Override
    public String getMethod() {
        return "PUT";
    }
}
