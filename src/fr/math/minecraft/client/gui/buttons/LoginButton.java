package fr.math.minecraft.client.gui.buttons;

import fr.math.minecraft.client.meshs.ButtonMesh;
import fr.math.minecraft.client.visitor.ButtonVisitor;
import fr.math.minecraft.shared.GameConfiguration;

public class LoginButton extends BlockButton {

    private boolean pending;

    public LoginButton() {
        super("Se connecter", GameConfiguration.WINDOW_CENTER_X - ButtonMesh.BUTTON_WIDTH / 2.0f, 80, -9);
        this.pending = false;
    }

    @Override
    public <T> T accept(ButtonVisitor<T> visitor) {
        return visitor.onClick(this);
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

}
