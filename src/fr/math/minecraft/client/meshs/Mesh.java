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
import fr.math.minecraft.client.buffers.VBO;
import fr.math.minecraft.client.vertex.Vertex;
import fr.math.minecraft.client.buffers.VAO;

public abstract class Mesh {

    protected Vertex[] vertices;
    protected int[] indices;
    protected VAO vao;
    protected VBO vbo;
    protected EBO ebo;

    public Mesh() {
        this.vertices = null;
        this.indices = null;
        this.vbo = null;
        this.vao = null;
    }

    public abstract void init();
    public abstract void draw();
    public void delete() {
        if (vao != null) vao.delete();
        if (vbo != null) vbo.delete();
        if (ebo != null) ebo.delete();
    }

    public VAO getVAO() {
        return vao;
    }

    public VBO getVBO() {
        return vbo;
    }

    public EBO getEBO() {
        return ebo;
    }
}
