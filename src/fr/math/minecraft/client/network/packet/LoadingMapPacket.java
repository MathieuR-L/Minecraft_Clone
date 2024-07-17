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

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.network.AuthUser;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;

public class LoadingMapPacket extends ClientPacket implements Runnable{

    private final ObjectMapper mapper;
    private final Player player;
    private final static Logger logger = LoggerUtility.getClientLogger(ConnectionInitPacket.class, LogType.TXT);;
    private final AuthUser user;


    public LoadingMapPacket(Player player, AuthUser user) {
        this.mapper = new ObjectMapper();
        this.player = player;
        this.user = user;
    }

    @Override
    public void run() {

    }

    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public String getResponse() {
        return null;
    }


}
