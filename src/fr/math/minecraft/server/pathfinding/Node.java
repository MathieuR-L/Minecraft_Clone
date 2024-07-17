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

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.Objects;

public class Node {

    private float gCost, fCost, hCost;
    private float heuristic;
    private final Vector2i position;
    private Node parent;

    public Node(int x, int y) {
        this(new Vector2i(x, y));
    }

    public Node(Vector3i position) {
        this(new Vector2i(position.x, 0, position.z));
    }

    public Node(Vector3f position) {
        this(new Vector2i((int) position.x, (int) position.z));
    }

    public Node(Vector2i position) {
        this.position = position;
        this.gCost = -1;
        this.hCost = -1;
        this.fCost = -1;
        this.heuristic = -1;
        this.parent = null;
    }

    public Vector2i getPosition() {
        return position;
    }

    public float getGCost() {
        return gCost;
    }

    public void setGCost(float gCost) {
        this.gCost = gCost;
    }

    public float getFCost() {
        return fCost;
    }

    public void setFCost(float fCost) {
        this.fCost = fCost;
    }

    public float getHCost() {
        return hCost;
    }

    public void setHCost(float hCost) {
        this.hCost = hCost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                ", position=" + position +
                ", heuristic=" + heuristic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(position, node.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
