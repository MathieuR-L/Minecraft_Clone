package fr.math.minecraft.client.visitor;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.GameState;
import fr.math.minecraft.client.audio.Sounds;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.gui.buttons.AuthButton;
import fr.math.minecraft.client.gui.buttons.BackToTitleButton;
import fr.math.minecraft.client.gui.buttons.LoginButton;
import fr.math.minecraft.client.gui.buttons.PlayButton;
import fr.math.minecraft.client.gui.menus.AuthMenu;
import fr.math.minecraft.client.gui.menus.ConnectionMenu;
import fr.math.minecraft.client.gui.menus.MainMenu;
import fr.math.minecraft.client.manager.MenuManager;
import fr.math.minecraft.client.manager.SoundManager;
import fr.math.minecraft.client.network.packet.ConnectionInitPacket;

public class ButtonActionVisitor implements ButtonVisitor<Void> {

    @Override
    public Void onClick(PlayButton button) {

        Game game = Game.getInstance();
        Player player = game.getPlayer();
        SoundManager soundManager = game.getSoundManager();
        MenuManager menuManager = game.getMenuManager();


        soundManager.play(Sounds.CLICK);
        menuManager.open(ConnectionMenu.class);

        ConnectionMenu menu = (ConnectionMenu) menuManager.getOpenedMenu();
        menu.getTitle().setText("Connexion en cours...");

        ConnectionInitPacket packet = new ConnectionInitPacket(player);
        Thread thread = new Thread(packet);
        thread.start();

        return null;
    }

    @Override
    public Void onClick(BackToTitleButton button) {

        Game game = Game.getInstance();
        MenuManager menuManager = game.getMenuManager();
        SoundManager soundManager = game.getSoundManager();

        game.setState(GameState.MAIN_MENU);
        soundManager.play(Sounds.CLICK);
        menuManager.open(MainMenu.class);

        return null;
    }

    @Override
    public Void onClick(AuthButton button) {

        Game game = Game.getInstance();
        MenuManager menuManager = game.getMenuManager();
        SoundManager soundManager = game.getSoundManager();
        soundManager.play(Sounds.CLICK);

        menuManager.open(AuthMenu.class);

        return null;
    }

    @Override
    public Void onClick(LoginButton button) {

        Game game = Game.getInstance();
        MenuManager menuManager = game.getMenuManager();
        SoundManager soundManager = game.getSoundManager();
        soundManager.play(Sounds.CLICK);

        return null;
    }
}
