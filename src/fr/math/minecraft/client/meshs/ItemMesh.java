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

package fr.math.minecraft.client.meshs;

import fr.math.minecraft.client.buffers.EBO;
import fr.math.minecraft.client.buffers.VAO;
import fr.math.minecraft.client.buffers.VBO;
import fr.math.minecraft.client.builder.ItemMeshBuilder;
import fr.math.minecraft.client.vertex.ItemVertex;
import fr.math.minecraft.shared.world.Material;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL33.*;

public class ItemMesh extends Mesh {

    private ItemVertex[] vertices;

    public ItemMesh(Material material) {
        super();
        ArrayList<Integer> elements = new ArrayList<>();
        this.vertices = ItemMeshBuilder.buildItemMesh(material, elements);
        this.indices = elements.stream().mapToInt(Integer::valueOf).toArray();
        this.init();
    }


    @Override
    public void init() {
        vao = new VAO();
        vao.bind();

        vbo = new VBO(vertices);
        ebo = new EBO(indices);

        vao.linkAttrib(vbo, 0, 3, GL_FLOAT, ItemVertex.VERTEX_SIZE * Float.BYTES, 0);
        vao.linkAttrib(vbo, 1, 2, GL_FLOAT, ItemVertex.VERTEX_SIZE * Float.BYTES, 3 * Float.BYTES);

        vao.unbind();
        vbo.unbind();
        ebo.unbind();
    }

    @Override
    public void draw() {
        vao.bind();
        glDrawArrays(GL_TRIANGLES, 0, vertices.length);
        //glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        vao.unbind();
    }

    public void update(Material material) {
        vbo.delete();
        ebo.delete();
        vao.delete();
        this.vertices = ItemMeshBuilder.buildItemMesh(material, new ArrayList<>());
        this.init();
        System.out.println("update!");
    }
}