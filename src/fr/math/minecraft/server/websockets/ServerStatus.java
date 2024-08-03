package fr.math.minecraft.server.websockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Date;

public class ServerStatus {

    public enum Status {
        ONLINE, OFFLINE;
    }

    private final String ip;
    private final int onlinePlayers;
    private final int messagesCount;
    private final Date lastUpdate;
    private final Status status;

    public ServerStatus(String ip, int onlinePlayers, int messagesCount) {
        this(ip, onlinePlayers, messagesCount, new Date());
    }

    public ServerStatus(String ip, int onlinePlayers, int messagesCount, Date lastUpdate) {
        this(ip, Status.ONLINE, onlinePlayers, messagesCount, lastUpdate);
    }

    public ServerStatus(String ip, Status status, int onlinePlayers, int messagesCount, Date lastUpdate) {
        this.status = status;
        this.onlinePlayers = onlinePlayers;
        this.messagesCount = messagesCount;
        this.lastUpdate = lastUpdate;
        this.ip = ip;
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

    public String getIp() {
        return ip;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("ip", ip);
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
