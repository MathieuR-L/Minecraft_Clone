package fr.math.minecraft.server.api;

import fr.math.minecraft.server.websockets.ServerStatus;

public class MinecraftApiFacade {

    public Server signInServer() {
        SignInServerApi registerServerApi = new SignInServerApi();
        return registerServerApi.execute();
    }

    public void updateServer(Server server, ServerStatus serverStatus) {
        UpdateServerApi updateServerApi = new UpdateServerApi(server.getId(), serverStatus);
        updateServerApi.execute();
    }

}
