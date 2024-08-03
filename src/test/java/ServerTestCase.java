package test.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.gui.menus.RetryAuthMenu;
import fr.math.minecraft.client.manager.MenuManager;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.ServerConfiguration;
import fr.math.minecraft.server.api.Server;
import fr.math.minecraft.server.manager.PluginManager;
import fr.math.minecraft.server.websockets.MinecraftWebSocketServer;
import fr.math.minecraft.shared.ChatMessage;
import fr.math.minecraft.shared.world.World;
import org.java_websocket.server.WebSocketServer;
import org.joml.Vector3f;
import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ServerTestCase {

    protected MinecraftServer server;
    protected MockedStatic<MinecraftServer> minecraftServerMock;
    private MockedStatic<ServerConfiguration> serverConfigurationMock;
    private MockedStatic<ImageIO> mockImageIO;
    protected ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        server = mock(MinecraftServer.class);
        minecraftServerMock = Mockito.mockStatic(MinecraftServer.class);
        minecraftServerMock.when(MinecraftServer::getInstance).thenReturn(server);
        this.mockImageIO = Mockito.mockStatic(ImageIO.class);

        World world = mock(World.class);
        PluginManager pluginManager = mock(PluginManager.class);
        Server serverData = mock(Server.class);
        MinecraftWebSocketServer webSocketServer = mock(MinecraftWebSocketServer.class);
        Map<String, Client> clients = new HashMap<>();
        List<ChatMessage> chatMessages = new ArrayList<>();

        this.serverConfigurationMock = Mockito.mockStatic(ServerConfiguration.class);
        ServerConfiguration mockConfig = Mockito.mock(ServerConfiguration.class);
        serverConfigurationMock.when(ServerConfiguration::getInstance).thenReturn(mockConfig);

        when(mockConfig.getApiEndpoint()).thenReturn("https://test.api.minecraftclone.com");
        when(mockConfig.getAuthEndpoint()).thenReturn("https://test.auth.minecraftclone.com");
        when(mockConfig.getAuthToken()).thenReturn("auth_123456789");

        when(serverData.getIp()).thenReturn("127.0.0.1");
        when(serverData.getId()).thenReturn(1);

        when(world.getSpawnPosition()).thenReturn(new Vector3f());

        when(server.getWorld()).thenReturn(world);
        when(server.getPluginManager()).thenReturn(pluginManager);
        when(server.getServerData()).thenReturn(serverData);
        when(server.getWebSocketServer()).thenReturn(webSocketServer);
        when(server.getClients()).thenReturn(clients);
        when(server.getChatMessages()).thenReturn(chatMessages);

        mockImageIO.when(() -> ImageIO.write(any(BufferedImage.class), eq("png"), any(File.class))).thenReturn(true);

        doNothing().when(server).sendPacket(ArgumentMatchers.any());
    }

    @After
    public void tearDown() {
        minecraftServerMock.close();
        serverConfigurationMock.close();
        mockImageIO.close();
    }
}
