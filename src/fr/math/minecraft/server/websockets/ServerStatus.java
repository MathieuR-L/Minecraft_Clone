package fr.math.minecraft.server.websockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Date;

public class ServerStatus {

    public enum Status {
        ONLINE, OFFLINE;
    }

    private final int onlinePlayers;
    private final int messagesCount;
    private final Date lastUpdate;
    private final Status status;

    public ServerStatus(int onlinePlayers, int messagesCount) {
        this(onlinePlayers, messagesCount, new Date());
    }

    public ServerStatus(int onlinePlayers, int messagesCount, Date lastUpdate) {
        this(Status.ONLINE, onlinePlayers, messagesCount, lastUpdate);
    }

    public ServerStatus(Status status, int onlinePlayers, int messagesCount, Date lastUpdate) {
        this.status = status;
        this.onlinePlayers = onlinePlayers;
        this.messagesCount = messagesCount;
        this.lastUpdate = lastUpdate;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Status getStatus() {
        return status;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("onlinePlayers", onlinePlayers);
        node.put("messagesCount", messagesCount);
        node.put("lastUpdate", lastUpdate.toString());
        node.put("status", status.toString());

        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
