package fr.math.minecraft.client.gui.buttons;

import fr.math.minecraft.client.meshs.ButtonMesh;
import fr.math.minecraft.client.visitor.ButtonVisitor;
import fr.math.minecraft.shared.GameConfiguration;

public class AuthButton extends BlockButton {

    public AuthButton() {
        super("S'authentifier", GameConfiguration.WINDOW_CENTER_X - ButtonMesh.BUTTON_WIDTH / 2.0f, GameConfiguration.WINDOW_CENTER_Y - ButtonMesh.BUTTON_HEIGHT / 2.0f - ButtonMesh.BUTTON_HEIGHT - 10);
    }

    @Override
    public <T> T accept(ButtonVisitor<T> visitor) {
        return visitor.onClick(this);
    }
}
