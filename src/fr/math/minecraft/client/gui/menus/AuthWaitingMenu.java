package fr.math.minecraft.client.gui.menus;

import fr.math.minecraft.client.Game;

public class AuthWaitingMenu extends Menu {

    public AuthWaitingMenu(Game game) {
        super(game, "Authentification en cours...");
    }

    @Override
    public void loadContent() {
    }

    @Override
    public MenuBackgroundType getBackgroundType() {
        return MenuBackgroundType.DIRT_BACKGROUND;
    }

    @Override
    public void update() {

    }

}
