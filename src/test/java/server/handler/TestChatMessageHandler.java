package test.java.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.handler.ChatMessageHandler;
import fr.math.minecraft.shared.ChatMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import test.java.ServerTestCase;
import test.java.factories.ClientFactory;

public class TestChatMessageHandler extends ServerTestCase {

    private ChatMessageHandler handler;
    private ObjectMapper mapper;
    private Client client;

    @Before
    public void init() {
        this.mapper = new ObjectMapper();
        this.client = ClientFactory.createClient();
        client.setActive(true);
        server.getClients().put(client.getUuid(), client);

        ObjectNode mockClientNode = mapper.createObjectNode();
        mockClientNode.put("sender", client.getUuid());
        mockClientNode.put("content", "Hello, World!");
        this.handler = new ChatMessageHandler(mockClientNode, client.getAddress(), client.getPort());
    }

    @Test
    public void testIncomingMessageShouldBeSaved() {
        handler.run();

        Assert.assertEquals(server.getChatMessages().size(), 1);
        ChatMessage chatMessage = server.getChatMessages().get(0);
        Assert.assertEquals(chatMessage.getMessage(), "Hello, World!");
        Assert.assertEquals(chatMessage.getSenderName(), client.getName());
        Assert.assertEquals(chatMessage.getSenderUuid(), client.getUuid());
    }

    @Test
    public void testRejectUnknownClientMessage() {
        server.getClients().remove(client.getUuid());
        handler.run();

        //Mockito.verify(logger).warn("Le client (" + client.getUuid() + ") est inconnu, son message contient : Hello, World!");

        Assert.assertEquals(server.getChatMessages().size(), 0);
    }

}
