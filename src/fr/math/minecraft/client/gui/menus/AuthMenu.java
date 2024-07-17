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

package fr.math.minecraft.client.gui.menus;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.gui.GuiInputField;
import fr.math.minecraft.client.gui.buttons.BackToTitleButton;
import fr.math.minecraft.client.gui.buttons.BlockButton;
import fr.math.minecraft.client.gui.buttons.LoginButton;
import fr.math.minecraft.client.meshs.ButtonMesh;
import fr.math.minecraft.shared.GameConfiguration;

public class AuthMenu extends Menu {

    private GuiInputField emailInput;
    private GuiInputField passwordInput;

    public AuthMenu(Game game) {
        super(game, "Authentification");
    }

    @Override
    public void loadContent() {

        BlockButton backToTitle = new BackToTitleButton(GameConfiguration.WINDOW_CENTER_X - ButtonMesh.BUTTON_WIDTH / 2.0f, 30);
        BlockButton loginButton = new LoginButton();
        GuiInputField emailField = new GuiInputField(GuiInputField.Type.TEXT, GameConfiguration.WINDOW_CENTER_X - GuiInputField.WIDTH / 2.0f, 250, "Email");
        GuiInputField passwordField = new GuiInputField(GuiInputField.Type.PASSWORD, GameConfiguration.WINDOW_CENTER_X - GuiInputField.WIDTH / 2.0f, 150, "Mot de passe");

        this.emailInput = emailField;
        this.passwordInput = passwordField;

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

    public GuiInputField getEmailInput() {
        return emailInput;
    }

    public GuiInputField getPasswordInput() {
        return passwordInput;
    }
}
