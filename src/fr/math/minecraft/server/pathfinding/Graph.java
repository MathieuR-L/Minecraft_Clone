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

import org.joml.Vector3i;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    private final Map<Node, Set<Node>> nodes;
    private final Map<Node, Node> world;
    private final Set<Vector3i> loadedChunks;

    public Graph() {
        this.nodes = new HashMap<>();
        this.world = new HashMap<>();
        this.loadedChunks = new HashSet<>();
    }

    public Map<Node, Set<Node>> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        nodes.put(node, new HashSet<>());
        world.put(node, node);
    }

    public void removeNode(Node node) {
        if (nodes.containsKey(node)) {
            nodes.remove(node);
        }
    }

    public boolean isLinked(Node node1, Node node2) {
        return nodes.get(node1).contains(node2);
    }

    public void addLink(Node node1, Node node2) {
        nodes.get(node1).add(node2);
        nodes.get(node2).add(node1);
    }

    public void removeLink(Node node1, Node node2) {
        if (isLinked(node1, node2)) {
            nodes.get(node1).remove(node2);
            nodes.get(node2).remove(node1);
        }
    }

    public Set<Vector3i> getLoadedChunks() {
        return loadedChunks;
    }

    public Set<Node> getNeighbors(Node node) {
        return nodes.get(node);
    }

    @Override
    public String toString() {
        String res = "";
        for (Node node : nodes.keySet()) {
            res += node;
            Set<Node> neighbors = this.getNeighbors(node);
            if (neighbors == null || neighbors.isEmpty()) {
                res += " [ ]";
            } else {
                res += " " + neighbors;
            }
            res += "\n";
        }
        return res;
    }
}
