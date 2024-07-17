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

package fr.math.minecraft.shared.world;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.shared.math.MathUtils;
import org.joml.Vector3i;

import java.util.Objects;

public class Coordinates {

    private int x, y, z;

    public Coordinates(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinates(Vector3i position) {
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y && z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public int compareTo(Coordinates o) {
        Player player = Game.getInstance().getPlayer();
        double distance1 = MathUtils.distance(player, this);
        double distance2 = MathUtils.distance(player, o);

        if (distance1 < distance2) {
            return 1;
        } else if (distance1 > distance2) {
            return -1;
        }
        return 0;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
