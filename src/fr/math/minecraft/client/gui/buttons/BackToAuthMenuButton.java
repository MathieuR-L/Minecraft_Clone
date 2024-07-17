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

package fr.math.minecraft.client.gui.buttons;

import fr.math.minecraft.client.meshs.ButtonMesh;
import fr.math.minecraft.client.visitor.ButtonVisitor;
import fr.math.minecraft.shared.GameConfiguration;

public class BackToAuthMenuButton extends BlockButton {

    public BackToAuthMenuButton() {
        super(
                "Réessayer",
                GameConfiguration.WINDOW_CENTER_X - ButtonMesh.BUTTON_WIDTH / 2.0f,
                GameConfiguration.WINDOW_CENTER_Y - ButtonMesh.BUTTON_HEIGHT / 2.0f - 50,
                -9
        );
    }

    @Override
    public <T> T accept(ButtonVisitor<T> visitor) {
        return visitor.onClick(this);
    }

}
