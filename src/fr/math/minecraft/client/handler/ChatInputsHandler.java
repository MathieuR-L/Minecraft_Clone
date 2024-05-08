package fr.math.minecraft.client.handler;

import fr.math.minecraft.client.network.payload.ChatPayload;
import org.lwjgl.glfw.GLFWCharCallback;

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
            inputBuilder.append(letter);
        }
    }

}
