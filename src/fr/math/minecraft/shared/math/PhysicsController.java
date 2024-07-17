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

package fr.math.minecraft.shared.math;

import fr.math.minecraft.server.Client;
import fr.math.minecraft.shared.entity.Entity;
import org.joml.Vector2f;
import static fr.math.minecraft.shared.GameConfiguration.*;

public class PhysicsController {

    public static void infligeKnockback(Entity entity, Vector2f direction) {
        entity.getVelocity().y = KNOCK_BACK_Y;
        entity.getVelocity().x = direction.x * KNOCK_BACK_X;
        entity.getVelocity().z = direction.y * KNOCK_BACK_Z;
        entity.setMaxSpeed(.4f);
    }

    public static void infligeKnockback(Client client, Vector2f direction) {
        client.getVelocity().y = KNOCK_BACK_Y;
        client.getVelocity().x = direction.x * KNOCK_BACK_X;
        client.getVelocity().z = direction.y * KNOCK_BACK_Z;
        client.setMaxSpeed(.4f);
    }

}
