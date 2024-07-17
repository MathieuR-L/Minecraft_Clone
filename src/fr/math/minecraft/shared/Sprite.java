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

import fr.math.minecraft.client.entity.player.Player;

public class Sprite {

    private final static int ANIMATION_SPEED = 20;
    private int index;
    private int tick;

    public Sprite() {
        this.index = 0;
        this.tick = 0;
    }

    public void update(PlayerAction action) {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            if (action != null && index < action.getLength()) {
                index++;
                if (index == action.getLength()) {
                    index = 0;
                }
            }
            tick = 0;
        }
    }

    public void reset() {
        this.index = 0;
        this.tick = 0;
    }

    public int getIndex() {
        return index;
    }

    public int getTick() {
        return tick;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
