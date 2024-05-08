package fr.math.minecraft.client.manager;

import fr.math.minecraft.shared.ChatMessage;
import fr.math.minecraft.shared.GameConfiguration;
import org.joml.Math;

import java.util.HashMap;
import java.util.Map;

public class ChatManager {

    private final Map<String, ChatMessage> chatMessages;
    private float chatOpacity;
    private int delay;

    public ChatManager() {
        this.chatMessages = new HashMap<>();
        this.chatOpacity = 1.0f;
        this.delay = 0;
    }

    public Map<String, ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public float getChatOpacity() {
        return chatOpacity;
    }

    public void setChatOpacity(float chatOpacity) {
        if (chatOpacity == 1.0f) {
            this.delay = (int) GameConfiguration.UPS * 2;
        }
        this.chatOpacity = Math.max(chatOpacity, 0.0f);
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = Math.max(delay, 0);;
    }
}
