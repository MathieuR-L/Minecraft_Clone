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

package fr.math.minecraft.client.manager;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.gui.menus.Menu;

public class MenuManager {

    private final Game game;
    private Menu openedMenu;

    public MenuManager(Game game) {
        this.game = game;
        this.openedMenu = null;
    }

    public void registerMenu(Menu menu) {
        game.getMenus().put(menu.getClass(), menu);
    }

    public void unregisterMenu(Menu menu) {
        game.getMenus().remove(menu.getClass());
    }

    public void open(Class<? extends Menu> menuClass) {
        Menu menu = game.getMenus().get(menuClass);
        this.closeAllMenus();
        openedMenu = menu;
        menu.open();
    }

    public void closeAllMenus() {
        for (Menu menu : game.getMenus().values()) {
            menu.close();
        }
    }

    public void close(Class<? extends Menu> menuClass) {
        Menu menu = game.getMenus().get(menuClass);
        menu.close();
    }

    public Menu getOpenedMenu() {
        return openedMenu;
    }
}
