package test.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.gui.menus.RetryAuthMenu;
import fr.math.minecraft.client.manager.MenuManager;
import org.junit.After;
import org.junit.Before;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameTestCase {

    protected Game game;
    protected MockedStatic<Game> gameMock;
    protected ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        game = mock(Game.class);
        gameMock = Mockito.mockStatic(Game.class);
        gameMock.when(Game::getInstance).thenReturn(game);

        when(game.getPlayer()).thenReturn(new Player(""));
        MenuManager menuManagerMock = mock(MenuManager.class);
        when(game.getMenuManager()).thenReturn(menuManagerMock);
        doNothing().when(menuManagerMock).open(RetryAuthMenu.class);
    }

    @After
    public void tearDown() {
        gameMock.close();
    }
}
