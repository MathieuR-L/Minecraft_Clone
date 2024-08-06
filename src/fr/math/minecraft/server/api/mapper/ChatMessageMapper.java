package fr.math.minecraft.server.api.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.shared.ChatMessage;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessageMapper extends ApiMapper<ChatMessage> {

    private static final Logger logger = LoggerUtility.getServerLogger(ChatMessageMapper.class, LogType.TXT);

    @Override
    public ChatMessage parseData(String responseData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(responseData);
            int id = node.get("id").asInt();
            String message = node.get("message").asText();
            String authorId = node.get("authorId").asText();
            String createdAt = node.get("createdAt").asText();
            Date createdAtDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse(createdAt);
            JsonNode authorNode = node.get("author");
            String authorName = authorNode.get("name").asText();
            String profileIconUrl = authorNode.get("profileIconUrl").asText();

            System.out.println(node);

            return new ChatMessage(
                String.valueOf(id),
                createdAtDate.getTime(),
                authorId,
                authorName,
                message,
                null,
                profileIconUrl
            );
        } catch (IOException | ParseException e) {
            logger.error("Impossible de convertir le message du serveur en un objet. Cela peut être du à des attributs manquants côté réponse du serveur.");
            e.printStackTrace();
        }
        return null;
    }

}
