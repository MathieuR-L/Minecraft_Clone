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

package fr.math.minecraft.server.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Node {

    private String data;
    private HashMap<String, Node> options;

    public Node(String data) {
        this.data = data;
        this.options = new HashMap<>();
    }

    private Node getOptionByName(String name) {
        return options.get(name);
    }

    public String displayOption(int i) {
        int y = 0;
        if(i >= 0 && i < options.size()) {
            for (Map.Entry mapentry : options.entrySet()) {
                if(y == i) {
                    return "" + mapentry.getKey();
                }
                y++;
            }
        }
        return null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HashMap<String, Node> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Node> options) {
        this.options = options;
    }
}
