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

import fr.math.minecraft.shared.network.PlayerInputData;

import java.util.ArrayList;
import java.util.List;

public class InputPayload {

    private final int tick;
    private final List<PlayerInputData> inputData;

    public InputPayload(int tick, List<PlayerInputData> inputData) {
        this.tick = tick;
        this.inputData = inputData;
    }

    public InputPayload(int tick) {
        this.tick = tick;
        this.inputData = new ArrayList<>();
    }

    public int getTick() {
        return tick;
    }

    public List<PlayerInputData> getInputData() {
        return inputData;
    }
}
