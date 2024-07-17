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

package fr.math.minecraft.client.visitor;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.GameState;
import fr.math.minecraft.client.audio.Sounds;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.gui.buttons.*;
import fr.math.minecraft.client.gui.menus.AuthMenu;
import fr.math.minecraft.client.gui.menus.AuthWaitingMenu;
import fr.math.minecraft.client.gui.menus.ConnectionMenu;
import fr.math.minecraft.client.gui.menus.MainMenu;
import fr.math.minecraft.client.manager.MenuManager;
import fr.math.minecraft.client.manager.SoundManager;
import fr.math.minecraft.client.network.AuthUser;
import fr.math.minecraft.client.network.packet.AuthentificationPacket;
import fr.math.minecraft.client.network.packet.ConnectionInitPacket;
import fr.math.minecraft.shared.GameConfiguration;

public class ButtonActionVisitor implements ButtonVisitor<Void> {

    private int i = 0;

    @Override
    public Void onClick(PlayButton button) {

        Game game = Game.getInstance();
        Player player = game.getPlayer();
        SoundManager soundManager = game.getSoundManager();
        MenuManager menuManager = game.getMenuManager();

        soundManager.play(Sounds.CLICK);

        AuthUser user = game.getUser();

        if (user == null) {
            return null;
        }
        GameConfiguration.setWorldType("CLASSIC_WORLD");

        menuManager.open(ConnectionMenu.class);
        ConnectionMenu menu = (ConnectionMenu) menuManager.getOpenedMenu();
        menu.getTitle().setText("Connexion en cours...");

        ConnectionInitPacket packet = new ConnectionInitPacket(player, user);
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

        if (button.isPending()) {
            return null;
        }

        Game game = Game.getInstance();
        MenuManager menuManager = game.getMenuManager();
        AuthMenu authMenu = (AuthMenu) menuManager.getOpenedMenu();
        SoundManager soundManager = game.getSoundManager();
        soundManager.play(Sounds.CLICK);

        String email = authMenu.getEmailInput().getValue().toString();
        String password = authMenu.getPasswordInput().getValue().toString();

        button.setPending(true);
        AuthentificationPacket packet = new AuthentificationPacket(button, email, password);
        Thread thread = new Thread(packet);
        thread.start();

        menuManager.open(AuthWaitingMenu.class);

        return null;
    }

    @Override
    public Void onClick(BackToAuthMenuButton button) {

        Game game = Game.getInstance();
        MenuManager menuManager = game.getMenuManager();
        SoundManager soundManager = game.getSoundManager();

        game.setState(GameState.MAIN_MENU);
        soundManager.play(Sounds.CLICK);
        menuManager.open(AuthMenu.class);

        return null;
    }

    @Override
    public Void onClick(CyberPlayButton button) {

        Game game = Game.getInstance();
        Player player = game.getPlayer();
        SoundManager soundManager = game.getSoundManager();
        MenuManager menuManager = game.getMenuManager();

        soundManager.play(Sounds.CLICK);

        AuthUser user = game.getUser();

        if (user == null) {
            return null;
        }

        GameConfiguration.setWorldType("SUPERFLAT_WORLD");
        menuManager.open(ConnectionMenu.class);
        ConnectionMenu menu = (ConnectionMenu) menuManager.getOpenedMenu();
        menu.getTitle().setText("Connexion en cours...");

        ConnectionInitPacket packet = new ConnectionInitPacket(player, user);
        Thread thread = new Thread(packet);
        thread.start();
        i++;

        return null;
    }
}
