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

package fr.math.minecraft.client.buffers;

import static org.lwjgl.opengl.GL33.*;
public class VAO {

    private final int id;

    public VAO() {
        id = glGenVertexArrays();
    }

    public void linkAttrib(VBO vbo, int layout, int size, int type, int stride, long offset) {
        vbo.bind();
        glVertexAttribPointer(layout, size, type, false, stride, offset);
        glEnableVertexAttribArray(layout);
        vbo.unbind();
    }

    public void bind() {
        glBindVertexArray(id);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public void delete() {
        glDeleteVertexArrays(id);
    }
}
