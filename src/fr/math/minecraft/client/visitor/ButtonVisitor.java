package fr.math.minecraft.client.visitor;

import fr.math.minecraft.client.gui.buttons.AuthButton;
import fr.math.minecraft.client.gui.buttons.BackToTitleButton;
import fr.math.minecraft.client.gui.buttons.LoginButton;
import fr.math.minecraft.client.gui.buttons.PlayButton;

public interface ButtonVisitor<T> {

    T onClick(PlayButton button);
    T onClick(BackToTitleButton button);
    T onClick(AuthButton button);
    T onClick(LoginButton button);

}
