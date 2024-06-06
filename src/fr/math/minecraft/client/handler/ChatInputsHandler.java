package fr.math.minecraft.client.handler;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.network.payload.ChatPayload;
import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.system.libffi.FFICIF;

import static org.lwjgl.glfw.GLFW.*;

public class ChatInputsHandler {

    public void handleInputs(long window, StringBuilder builder) {
        glfwSetCharCallback(window, new InputHandler(builder));
    }

    public void handleInputs(long window, ChatPayload chatWindow) {
        glfwSetCharCallback(window, new InputHandler(chatWindow.getMessage()));
    }

    private static class InputHandler extends GLFWCharCallback {

        private final StringBuilder inputBuilder;

        public InputHandler(StringBuilder inputBuilder) {
            this.inputBuilder = inputBuilder;
        }

        @Override
        public void invoke(long window, int codepoint) {
            char letter = (char) codepoint;
            if (letter == GLFW_KEY_TAB) {
                ChatPayload chatPayload = Game.getInstance().getPlayer().getChatPayload();
                chatPayload.setMessage();
            }
            inputBuilder.append(letter);
        }
    }

}
