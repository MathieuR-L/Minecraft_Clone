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

package fr.math.minecraft.client.manager;

import fr.math.minecraft.shared.ChatMessage;
import fr.math.minecraft.shared.GameConfiguration;
import org.joml.Math;

import java.util.HashMap;
import java.util.Map;

public class ChatManager {

    private final Map<String, ChatMessage> chatMessages;
    private float chatOpacity;
    private int delay;

    public ChatManager() {
        this.chatMessages = new HashMap<>();
        this.chatOpacity = 1.0f;
        this.delay = 0;
    }

    public Map<String, ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public float getChatOpacity() {
        return chatOpacity;
    }

    public void setChatOpacity(float chatOpacity) {
        if (chatOpacity == 1.0f) {
            this.delay = (int) GameConfiguration.UPS * 2;
        }
        this.chatOpacity = Math.max(chatOpacity, 0.0f);
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = Math.max(delay, 0);;
    }
}
