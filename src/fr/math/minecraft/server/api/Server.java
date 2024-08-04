package fr.math.minecraft.server.api;

import fr.math.minecraft.shared.ChatMessage;

import java.util.List;

public class Server {

    private final int id;
    private final String ip;
    private final int onlinePlayers;
    private final List<ChatMessage> chatMessages;

    public Server(int id, String ip, int onlinePlayers, List<ChatMessage> chatMessages) {
        this.id = id;
        this.ip = ip;
        this.onlinePlayers = onlinePlayers;
        this.chatMessages = chatMessages;
    }

    public int getId() {
        return id;
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
