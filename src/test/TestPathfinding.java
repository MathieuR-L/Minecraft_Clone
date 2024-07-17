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

package test;

import fr.math.minecraft.server.pathfinding.AStar;
import fr.math.minecraft.server.pathfinding.Graph;
import fr.math.minecraft.server.pathfinding.Node;
import fr.math.minecraft.shared.entity.Entity;
import fr.math.minecraft.shared.entity.EntityFactory;
import fr.math.minecraft.shared.entity.EntityType;
import fr.math.minecraft.shared.entity.mob.Zombie;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.Material;
import fr.math.minecraft.shared.world.World;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestPathfinding {

    private World world;

    @Before
    public void prepareWorld() {
        world = new World();
        Chunk chunk = new Chunk(0, 0, 0);

        for (int x = 0; x < Chunk.SIZE; x++) {
            for (int z = 0; z < Chunk.SIZE; z++) {
                for (int y = 0; y < Chunk.SIZE; y++) {

                    int worldY = y + chunk.getPosition().y * Chunk.SIZE;

                    if (worldY <= 10) {
                        chunk.setBlock(x, y, z, Material.STONE.getId());
                    }
                }
            }
        }

        world.addChunk(chunk);
    }

    @Test
    public void testPathFinding() {
        Entity zombie = EntityFactory.createEntity(EntityType.ZOMBIE);
        zombie.setPosition(new Vector3f(0, 0, 0));
        world.addEntity(zombie);
        AStar.initGraph(world, zombie.getPosition());
        Node start = new Node(zombie.getPosition());
        Node end = new Node(new Vector3f(zombie.getPosition()).add(10, 0, 4));
        List<Node> path = AStar.shortestPath(world, start, end);

        System.out.println(Arrays.toString(path.toArray()));
    }

    @Test
    public void testGraph() {
        Graph graph = new Graph();
        graph.addNode(new Node(0, 0));
        graph.addNode(new Node(1, 0));
        graph.addNode(new Node(2, 0));
        graph.addNode(new Node(3, 0));

        graph.addLink(new Node(0, 0), new Node(1, 0));
        graph.addLink(new Node(0, 0), new Node(2, 0));

        System.out.println(graph);

    }
}
