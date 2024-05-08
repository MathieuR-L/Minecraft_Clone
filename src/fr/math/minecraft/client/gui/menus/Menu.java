package fr.math.minecraft.client.gui.menus;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.gui.GuiInputField;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.client.gui.buttons.BlockButton;
import fr.math.minecraft.client.gui.GuiText;
import fr.math.minecraft.client.manager.FontManager;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    protected GuiText title;
    protected GuiText subTitle;
    protected final List<BlockButton> buttons;
    protected final List<GuiText> texts;
    protected final List<GuiInputField> inputFields;
    protected boolean open;
    protected final Game game;

    public Menu(Game game, String title) {
        this(game, title, null);
    }

    public Menu(Game game, String title, String subTitle) {
        this(game);
        FontManager fontManager = new FontManager();
        float titleWidth = fontManager.getTextWidth(game.getRenderer().getFontMesh(), GameConfiguration.MENU_TITLE_SCALE, title);
        float titleHeight = fontManager.getTextHeight(game.getRenderer().getFontMesh(), GameConfiguration.MENU_TITLE_SCALE, title);
        this.title = new GuiText(
            title,
            GameConfiguration.WINDOW_CENTER_X - titleWidth / 2.0f,
            GameConfiguration.WINDOW_CENTER_Y - titleHeight / 2.0f + 120,
            0xFFFFFF
        );
        if (subTitle == null) {
            this.subTitle = null;
        } else {
            this.subTitle = new GuiText(
                subTitle,
                GameConfiguration.WINDOW_CENTER_X - titleWidth / 2.0f,
                GameConfiguration.WINDOW_CENTER_Y - titleHeight / 2.0f + 90,
                0xA7A7A7
            );
            this.subTitle.setScale(GameConfiguration.MENU_SUBTITLE_SCALE);
        }
        this.title.setScale(GameConfiguration.MENU_TITLE_SCALE);
    }

    public Menu(Game game) {
        this.game = game;
        this.buttons = new ArrayList<>();
        this.texts = new ArrayList<>();
        this.inputFields = new ArrayList<>();
        this.open = false;
        this.title = null;
        this.loadContent();
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

    public boolean isOpen() {
        return open;
    }

    public List<BlockButton> getButtons() {
        return buttons;
    }

    public abstract void loadContent();
    public abstract MenuBackgroundType getBackgroundType();

    public abstract void update();

    public List<GuiText> getTexts() {
        return texts;
    }

    public GuiText getTitle() {
        return title;
    }

    public GuiText getSubTitle() {
        return subTitle;
    }

    public List<GuiInputField> getInputFields() {
        return inputFields;
    }
}
