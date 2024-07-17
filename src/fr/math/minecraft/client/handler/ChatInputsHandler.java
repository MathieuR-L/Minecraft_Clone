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

package fr.math.minecraft.client.handler;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.network.payload.ChatPayload;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;
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

        private final static Logger logger = LoggerUtility.getClientLogger(InputHandler.class, LogType.TXT);

        private final StringBuilder inputBuilder;

        public InputHandler(StringBuilder inputBuilder) {
            this.inputBuilder = inputBuilder;
        }

        @Override
        public void invoke(long window, int codepoint) {
            char letter = (char) codepoint;
            if (letter == GLFW_KEY_TAB) {
                ChatPayload chatPayload = Game.getInstance().getPlayer().getChatPayload();
                logger.debug("Current msg :" + chatPayload.getMessage().toString());
                //chatPayload.setMessage();
            }
            inputBuilder.append(letter);
        }
    }

}
