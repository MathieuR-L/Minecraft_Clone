package fr.math.minecraft.server.api.mapper;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.api.Server;
import fr.math.minecraft.shared.ChatMessage;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignInServerApiMapper extends ApiMapper<Server> {

    private final static Logger logger = LoggerUtility.getServerLogger(SignInServerApiMapper.class, LogType.TXT);

    @Override
    public Server parseData(String responseData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(responseData);
            String ip = node.get("ip").asText();
            int id = node.get("id").asInt();
            int onlinePlayers = node.get("onlinePlayers").asInt();
            ArrayNode chatMessages = (ArrayNode) node.get("chatMessages");
            List<ChatMessage> messages = new ArrayList<>();

            for (int i = 0; i < chatMessages.size(); i++) {
                JsonNode chatNode = chatMessages.get(i);
                String message = chatNode.get("message").asText();
                String authorId = chatNode.get("authorId").asText();
                String date = chatNode.get("createdAt").asText();
                messages.add(new ChatMessage(message, authorId, date));
            }

            return new Server(id, ip, onlinePlayers, messages);

        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }
}
