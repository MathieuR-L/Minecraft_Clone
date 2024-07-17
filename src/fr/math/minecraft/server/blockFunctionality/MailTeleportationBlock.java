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

package fr.math.minecraft.server.blockFunctionality;

import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.shared.world.Material;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class MailTeleportationBlock extends UsableBlock{

    private Vector3f mailHubPosition;

    public MailTeleportationBlock(Material material, Vector3i position) {
        super(material, position);
        this.mailHubPosition = new Vector3f(0.0f, 100.0f, 0.0f);
    }

    @Override
    public void run(Client client, MinecraftServer server) {
        client.setPosition(mailHubPosition);
    }
}
