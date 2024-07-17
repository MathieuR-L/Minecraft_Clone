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

package fr.math.minecraft.shared;

public enum PlayerAction {
    MINING(0, 0, 0, 10);

    private final int id, spriteX, spriteY, length;

    PlayerAction(int id, int spriteX, int spriteY, int length) {
        this.id = id;
        this.spriteX = spriteX;
        this.spriteY = spriteY;
        this.length = length;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public int getLength() {
        return length;
    }

    public int getId() {
        return id;
    }
}
