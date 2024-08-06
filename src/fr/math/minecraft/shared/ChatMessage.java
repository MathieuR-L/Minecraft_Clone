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
    private final String profileIconUrl;

    public ChatMessage(String senderUuid, String senderName, String message) {
        this(UUID.randomUUID().toString(), System.currentTimeMillis(), senderUuid, senderName, message, ChatColor.WHITE, null);
    }

    public ChatMessage(String senderUuid, String senderName, String message, ChatColor color) {
        this(UUID.randomUUID().toString(), System.currentTimeMillis(), senderUuid, senderName, message, color, null);
    }

    public ChatMessage(String id, long timestamp, String senderUuid, String senderName, String message, ChatColor color) {
        this(id, timestamp, senderUuid, senderName, message, color, null);
    }

    public ChatMessage(String id, long timestamp, String senderUuid, String senderName, String message, ChatColor color, String profileIconUrl) {
        this.timestamp = timestamp;
        this.id = id;
        this.senderUuid = senderUuid;
        this.senderName = senderName;
        this.message = message;
        this.color = color;
        this.profileIconUrl = profileIconUrl;
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

    public ObjectNode toWebJSONObject() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ObjectNode authorNode = mapper.createObjectNode();

        authorNode.put("name", senderName);
        authorNode.put("id", senderUuid);
        authorNode.put("profileIconUrl", profileIconUrl);

        node.put("timestamp", timestamp);
        node.put("id", id);
        node.set("author", authorNode);
        node.put("message", message);

        return node;
    }

    public String getId() {
        return id;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getProfileIconUrl() {
        return profileIconUrl;
    }
}
