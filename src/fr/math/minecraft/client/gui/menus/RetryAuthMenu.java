package fr.math.minecraft.client.gui.menus;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.gui.buttons.BackToAuthMenuButton;
import fr.math.minecraft.client.gui.buttons.BlockButton;

public class RetryAuthMenu extends Menu {

    public RetryAuthMenu(Game game) {
        super(game, "Authentification échoué.");
    }

    @Override
    public void loadContent() {

        BlockButton backToTitle = new BackToAuthMenuButton();

        this.buttons.add(backToTitle);
    }

    @Override
    public MenuBackgroundType getBackgroundType() {
        return MenuBackgroundType.DIRT_BACKGROUND;
    }

    @Override
    public void update() {

    }

}
