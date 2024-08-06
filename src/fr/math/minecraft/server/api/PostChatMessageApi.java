package fr.math.minecraft.server.api;

import fr.math.minecraft.server.ServerConfiguration;
import fr.math.minecraft.server.api.mapper.ChatMessageMapper;
import fr.math.minecraft.server.api.payload.ChatMessagePayload;
import fr.math.minecraft.shared.ChatMessage;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PostChatMessageApi extends ApiTemplate<ChatMessage> {

    private final ChatMessagePayload payload;

    public PostChatMessageApi(ChatMessagePayload payload) {
        super(new ChatMessageMapper());
        this.payload = payload;
    }

    @Override
    public void handleRequest(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod(this.getMethod());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + ServerConfiguration.getInstance().getAuthToken());
        connection.setDoOutput(true);

        String payloadData = payload.toJSON();
        connection.getOutputStream().write(payloadData.getBytes());
    }

    @Override
    public String getUrl() {
        return "/api/chatmessages";
    }

    @Override
    public String getMethod() {
        return "POST";
    }

}
