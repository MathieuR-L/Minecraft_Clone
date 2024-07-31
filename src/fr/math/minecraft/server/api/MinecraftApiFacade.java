package fr.math.minecraft.server.api;

import fr.math.minecraft.server.websockets.ServerStatus;

public class MinecraftApiFacade {

    public Server registerServer() {
        RegisterServerApi registerServerApi = new RegisterServerApi();
        return registerServerApi.execute();
    }

    public void updateServer(ServerStatus serverStatus) {
        UpdateServerApi updateServerApi = new UpdateServerApi(11, serverStatus);
        updateServerApi.execute();
    }

}
