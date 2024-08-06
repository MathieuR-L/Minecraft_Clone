package fr.math.minecraft.server.api.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ChatMessagePayload {

    private final String message;
    private final String authorId;

    public ChatMessagePayload(String message, String authorId) {
        this.message = message;
        this.authorId = authorId;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("message", this.message);
        node.put("authorId", this.authorId);

        return mapper.writeValueAsString(node);
    }

    public String getMessage() {
        return message;
    }

    public String getAuthorId() {
        return authorId;
    }
}
