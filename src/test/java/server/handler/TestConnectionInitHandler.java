package test.java.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.TimeoutHandler;
import fr.math.minecraft.server.handler.ConnectionInitHandler;
import fr.math.minecraft.server.websockets.ServerStatus;
import fr.math.minecraft.shared.ChatColor;
import fr.math.minecraft.shared.Utils;
import fr.math.minecraft.shared.network.HttpResponse;
import fr.math.minecraft.shared.network.HttpUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import test.java.ServerTestCase;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;

public class TestConnectionInitHandler extends ServerTestCase {

    private ConnectionInitHandler handler;
    private ObjectMapper mapper;
    private HttpResponse mockResponse;
    private String mockId;
    @Before
    public void init() throws JsonProcessingException {
        this.mapper = new ObjectMapper();
        ObjectNode mockClientData = mapper.createObjectNode();
        mockClientData.put("token", "123456789");
        this.handler = new ConnectionInitHandler(mockClientData, null, 0);
        this.mockId = "1234-5678-9123-4567";

        ObjectNode mockAuthNode = mapper.createObjectNode();
        ObjectNode mockUser = mapper.createObjectNode();
        ObjectNode mockSkinNode = mapper.createObjectNode();
        mockSkinNode.put("link", "https://test.skin.com/johndoe");

        mockUser.put("id", mockId);
        mockUser.put("name", "John Doe");
        mockUser.set("skin", mockSkinNode);

        mockAuthNode.set("user", mockUser);

        this.mockResponse = new HttpResponse(new StringBuilder(mapper.writeValueAsString(mockAuthNode)), 200);
    }

    @Test
    public void testSuccessfulAuthentification() {
        try (MockedStatic<HttpUtils> httpMock = Mockito.mockStatic(HttpUtils.class)) {
            httpMock.when(
                () -> HttpUtils.POST(ArgumentMatchers.any(), ArgumentMatchers.any())
            ).thenReturn(mockResponse);

            try (MockedStatic<Utils> utilsMock = Mockito.mockStatic(Utils.class)) {
                utilsMock.when(() -> Utils.loadBase64Skin(ArgumentMatchers.any())).thenReturn(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));

                try (MockedConstruction<TimeoutHandler> mockTimeout = Mockito.mockConstruction(TimeoutHandler.class)) {
                    handler.run();
                    Client client = server.getClients().get(mockId);

                    Mockito.verify(mockTimeout.constructed().get(0)).start();
                    Assert.assertEquals(server.getClients().size(), 1);
                    Assert.assertNotNull(client);
                    Assert.assertEquals(client.getUuid(), mockId);
                    Assert.assertEquals(client.getName(), "John Doe");
                    Mockito.verify(server).announceMessage("John Doe a rejoint le serveur.", ChatColor.YELLOW);

                    ArgumentCaptor<ServerStatus> argumentCaptor = ArgumentCaptor.forClass(ServerStatus.class);

                    Mockito.verify(server.getWebSocketServer()).broadcastStatus(argumentCaptor.capture());

                    ServerStatus serverStatus = argumentCaptor.getValue();

                    Assert.assertEquals(serverStatus.getOnlinePlayers(), 1);
                }
            }
        }
    }

    @Test
    public void testInvalidTokenAuthentification() {
        try (MockedStatic<HttpUtils> httpMock = Mockito.mockStatic(HttpUtils.class)) {
            httpMock.when(() -> HttpUtils.POST(ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(new IOException());

            ArgumentCaptor<DatagramPacket> argumentCaptor = ArgumentCaptor.forClass(DatagramPacket.class);

            handler.run();

            Mockito.verify(server).sendPacket(argumentCaptor.capture());
            DatagramPacket sentPacket = argumentCaptor.getValue();

            byte[] expectedBuffer = "INVALID_TOKEN".getBytes(StandardCharsets.UTF_8);
            byte[] sentBuffer = sentPacket.getData();

            Assert.assertArrayEquals(expectedBuffer, sentBuffer);
        }
    }
}
