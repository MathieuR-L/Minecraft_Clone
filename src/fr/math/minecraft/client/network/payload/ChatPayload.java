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

package fr.math.minecraft.client.network.payload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.network.packet.ChatMessagePacket;

public class ChatPayload {

    private boolean open;
    private StringBuilder message;
    private final Player sender;

    public ChatPayload(Player player) {
        this.sender = player;
        this.open = false;
        this.message = new StringBuilder();
    }

    public void send() {
        if (message.length() == 0) {
            return;
        }

        ChatMessagePacket packet = new ChatMessagePacket(sender, message.toString());
        packet.send();
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public void setMessage(StringBuilder stringBuilder) {
        this.message = stringBuilder;
    }
}
