package fr.math.minecraft.client.gui;

import fr.math.minecraft.client.meshs.ButtonMesh;
import fr.math.minecraft.client.visitor.ButtonActionVisitor;
import fr.math.minecraft.shared.GameConfiguration;

import static org.lwjgl.glfw.GLFW.*;

public class GuiInputField {

    public static final float WIDTH = ButtonMesh.BUTTON_WIDTH;
    public static final float HEIGHT = ButtonMesh.BUTTON_HEIGHT;
    private final Type type;
    private final String label;
    private final StringBuilder value;
    private final float x, y;
    private boolean focused;

    public GuiInputField(Type type, float x, float y, String label) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.focused = false;
        this.label = label;
        this.value = new StringBuilder();
    }

    public StringBuilder getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Type getType() {
        return type;
    }

    public boolean contains(double mouseX, double mouseY) {
        if (mouseX < 0 || mouseY < 0) {
            return false;
        }
        mouseY = GameConfiguration.WINDOW_HEIGHT - mouseY;
        return (
                x <= mouseX && mouseX <= x + ButtonMesh.BUTTON_WIDTH &&
                        y <= mouseY && mouseY <= y + ButtonMesh.BUTTON_HEIGHT
        );
    }

    public void handleInputs(long window, double mouseX, double mouseY) {
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS) {
            focused = this.contains(mouseX, mouseY);
        }

        if (!focused) {
            return;
        }

        if (glfwGetKey(window, GLFW_KEY_BACKSPACE) == GLFW_PRESS) {
            if (value.length() != 0) {
                value.deleteCharAt(value.length() - 1);
            }
        }
    }

    public enum Type {
        TEXT, PASSWORD;
    }
}
