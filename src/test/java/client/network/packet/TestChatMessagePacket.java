package test.java.client.network.packet;

import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.network.packet.ChatMessagePacket;
import fr.math.minecraft.client.network.payload.ChatPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import test.java.factories.PlayerFactory;

public class TestChatMessagePacket {

    private ChatPayload payload;
    private Player player;

    @Before
    public void init() {
        this.player = PlayerFactory.createPlayer();
        this.payload = new ChatPayload(player);
    }

    @Test
    public void testShouldNotSendEmptyMessage() {
        try (MockedConstruction<ChatMessagePacket> packetMock = Mockito.mockConstruction(ChatMessagePacket.class)) {
            payload.send();
            Assert.assertTrue(packetMock.constructed().isEmpty());
        }
    }

    @Test
    public void testShouldSendMessagePacket() {
        payload.getMessage().append("Hello, World!");
        try (MockedConstruction<ChatMessagePacket> packetMock = Mockito.mockConstruction(ChatMessagePacket.class)) {
            payload.send();
            ChatMessagePacket packet = packetMock.constructed().get(0);
            Mockito.verify(packet).send();
        }
    }
}
