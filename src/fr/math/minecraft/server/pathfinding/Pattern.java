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

package fr.math.minecraft.server.pathfinding;

import java.util.List;

public class Pattern {

    private List<Node> path;
    private int currentIndex;
    private final Node start, end;

    public Pattern(List<Node> path, Node start, Node end) {
        this.path = path;
        this.currentIndex = 0;
        this.start = start;
        this.end = end;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    public List<Node> getPath() {
        return path;
    }

    public Pattern next() {
        if (!path.isEmpty()) {
            path.remove(0);
            return this;
        }
        return null;
    }

    public Node getCurrentNode() {
        if (currentIndex >= path.size()) {
            return null;
        }
        return path.get(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
