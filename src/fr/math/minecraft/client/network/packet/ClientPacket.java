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

package fr.math.minecraft.client.network.packet;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.MinecraftClient;

import java.io.IOException;

public abstract class ClientPacket {

    public synchronized void send() {
        Game game = Game.getInstance();
        MinecraftClient client = game.getClient();
        String message = this.toJSON();
        try {
            client.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String toJSON();

    public abstract String getResponse();


}
