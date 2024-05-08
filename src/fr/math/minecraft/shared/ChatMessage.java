package fr.math.minecraft.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

public class ChatMessage {

    private final String id;
    private final String senderUuid;
    private final String senderName;
    private final String message;
    private final long timestamp;
    private final ChatColor color;

    public ChatMessage(String senderUuid, String senderName, String message) {
        this(UUID.randomUUID().toString(), System.currentTimeMillis(), senderUuid, senderName, message, ChatColor.WHITE);
    }

    public ChatMessage(String senderUuid, String senderName, String message, ChatColor color) {
        this(UUID.randomUUID().toString(), System.currentTimeMillis(), senderUuid, senderName, message, color);
    }

    public ChatMessage(String id, long timestamp, String senderUuid, String senderName, String message, ChatColor color) {
        this.timestamp = timestamp;
        this.id = id;
        this.senderUuid = senderUuid;
        this.senderName = senderName;
        this.message = message;
        this.color = color;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSenderUuid() {
        return senderUuid;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return message;
    }

    public ObjectNode toJSONObject() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        node.put("timestamp", timestamp);
        node.put("id", id);
        node.put("uuid", senderUuid);
        node.put("name", senderName);
        node.put("message", message);
        node.put("colorName", color.toString());

        return node;
    }

    public String getId() {
        return id;
    }

    public ChatColor getColor() {
        return color;
    }
}
