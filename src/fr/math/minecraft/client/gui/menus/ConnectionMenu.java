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

package fr.math.minecraft.client.gui.menus;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.gui.buttons.BackToTitleButton;
import fr.math.minecraft.client.gui.buttons.BlockButton;

public class ConnectionMenu extends Menu {

    public ConnectionMenu(Game game) {
        super(game, "Connexion en cours...");
    }

    @Override
    public void loadContent() {

        BlockButton backToTitle = new BackToTitleButton();

        this.buttons.add(backToTitle);
    }

    @Override
    public MenuBackgroundType getBackgroundType() {
        return MenuBackgroundType.DIRT_BACKGROUND;
    }

    @Override
    public void update() {

    }
}
