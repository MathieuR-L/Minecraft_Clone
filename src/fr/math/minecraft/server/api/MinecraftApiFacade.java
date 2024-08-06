package fr.math.minecraft.server.api;

import fr.math.minecraft.server.api.payload.ChatMessagePayload;
import fr.math.minecraft.server.websockets.ServerStatus;
import fr.math.minecraft.shared.ChatMessage;

public class MinecraftApiFacade {

    public Server signInServer() {
        SignInServerApi registerServerApi = new SignInServerApi();
        return registerServerApi.execute();
    }

    public void updateServer(Server server, ServerStatus serverStatus) {
        UpdateServerApi updateServerApi = new UpdateServerApi(server.getId(), serverStatus);
        updateServerApi.execute();
    }

    public ChatMessage postMessage(ChatMessagePayload payload) {
        PostChatMessageApi postChatMessageApi = new PostChatMessageApi(payload);
        return postChatMessageApi.execute();
    }

}
