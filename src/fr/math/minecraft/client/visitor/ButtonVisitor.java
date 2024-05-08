package fr.math.minecraft.client.visitor;

import fr.math.minecraft.client.gui.buttons.*;

public interface ButtonVisitor<T> {

    T onClick(PlayButton button);
    T onClick(BackToTitleButton button);
    T onClick(AuthButton button);
    T onClick(LoginButton button);
    T onClick(BackToAuthMenuButton button);

}
