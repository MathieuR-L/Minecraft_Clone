package fr.math.minecraft.server.api;

import fr.math.minecraft.shared.ChatMessage;

import java.util.List;

public class Server {

    private final String ip;
    private final int onlinePlayers;
    private final List<ChatMessage> chatMessages;

    public Server(String ip, int onlinePlayers, List<ChatMessage> chatMessages) {
        this.ip = ip;
        this.onlinePlayers = onlinePlayers;
        this.chatMessages = chatMessages;
    }

    public String getIp() {
        return ip;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
}
