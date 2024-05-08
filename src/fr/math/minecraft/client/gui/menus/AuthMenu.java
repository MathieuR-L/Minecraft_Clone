package fr.math.minecraft.client.gui.menus;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.gui.GuiInputField;
import fr.math.minecraft.client.gui.buttons.BackToTitleButton;
import fr.math.minecraft.client.gui.buttons.BlockButton;
import fr.math.minecraft.client.gui.buttons.LoginButton;
import fr.math.minecraft.client.meshs.ButtonMesh;
import fr.math.minecraft.shared.GameConfiguration;

public class AuthMenu extends Menu {

    public AuthMenu(Game game) {
        super(game, "Authentification");
    }

    @Override
    public void loadContent() {

        BlockButton backToTitle = new BackToTitleButton(GameConfiguration.WINDOW_CENTER_X - ButtonMesh.BUTTON_WIDTH / 2.0f, 30);
        BlockButton loginButton = new LoginButton();
        GuiInputField emailField = new GuiInputField(GuiInputField.Type.TEXT, GameConfiguration.WINDOW_CENTER_X - GuiInputField.WIDTH / 2.0f, 250, "Email");
        GuiInputField passwordField = new GuiInputField(GuiInputField.Type.PASSWORD, GameConfiguration.WINDOW_CENTER_X - GuiInputField.WIDTH / 2.0f, 150, "Mot de passe");

        this.buttons.add(backToTitle);
        this.buttons.add(loginButton);

        this.inputFields.add(emailField);
        this.inputFields.add(passwordField);
    }

    @Override
    public MenuBackgroundType getBackgroundType() {
        return MenuBackgroundType.DIRT_BACKGROUND;
    }

    @Override
    public void update() {

    }

    @Override
    public void close() {
        super.close();
        for (GuiInputField field : inputFields) {
            if (field.getValue().length() == 0) {
                continue;
            }
            field.getValue().delete(0, field.getValue().length() - 1);
        }
    }
}
