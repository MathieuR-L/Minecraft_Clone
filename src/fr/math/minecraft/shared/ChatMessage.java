/**
*  Minecraft Clone Math edition : Cybersecurity - A serious game to learn network and cybersecurity
*  Copyright (C) 2024 MeAndTheHomies (Math)
*
*  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
