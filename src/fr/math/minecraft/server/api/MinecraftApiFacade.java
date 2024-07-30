package fr.math.minecraft.server.api;

public class MinecraftApiFacade {

    public Void registerServer() {
        RegisterServerApi registerServerApi = new RegisterServerApi();
        return registerServerApi.execute();
    }

}
