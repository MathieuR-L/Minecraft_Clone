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

import fr.math.minecraft.client.vertex.*;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL33.*;
public class VBO {

    private final int id;

    public VBO(Vertex[] vertices) {
        id = glGenBuffers();
        float[] data = new float[vertices.length * 8];
        int bufferPosition = 0;
        for (Vertex vertex : vertices) {
            data[bufferPosition++] = vertex.getPosition().x;
            data[bufferPosition++] = vertex.getPosition().y;
            data[bufferPosition++] = vertex.getPosition().z;
            data[bufferPosition++] = vertex.getTextureCoords().x;
            data[bufferPosition++] = vertex.getTextureCoords().y;
            data[bufferPosition++] = vertex.getBlockID();
            data[bufferPosition++] = vertex.getBlockFace();
            data[bufferPosition++] = vertex.getOcclusion();
        }
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * 8);
        buffer.put(data).flip();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public VBO(BlockVertex[] vertices) {
        id = glGenBuffers();
        float[] data = new float[vertices.length * 7];
        int bufferPosition = 0;
        for (BlockVertex vertex : vertices) {
            data[bufferPosition++] = vertex.getPosition().x;
            data[bufferPosition++] = vertex.getPosition().y;
            data[bufferPosition++] = vertex.getPosition().z;
            data[bufferPosition++] = vertex.getTextureCoords().x;
            data[bufferPosition++] = vertex.getTextureCoords().y;
            data[bufferPosition++] = vertex.getIndex();
            data[bufferPosition++] = vertex.getFace();
        }
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * 7);
        buffer.put(data).flip();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public VBO(ItemVertex[] vertices) {
        id = glGenBuffers();
        float[] data = new float[vertices.length * ItemVertex.VERTEX_SIZE];
        int bufferPosition = 0;
        for (ItemVertex vertex : vertices) {
            data[bufferPosition++] = vertex.getPosition().x;
            data[bufferPosition++] = vertex.getPosition().y;
            data[bufferPosition++] = vertex.getPosition().z;
            data[bufferPosition++] = vertex.getTextureCoords().x;
            data[bufferPosition++] = vertex.getTextureCoords().y;
        }
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * ItemVertex.VERTEX_SIZE);
        buffer.put(data).flip();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public VBO(HandVertex[] vertices) {
        id = glGenBuffers();
        float[] data = new float[vertices.length * 5];
        int bufferPosition = 0;
        for (Vertex vertex : vertices) {
            data[bufferPosition++] = vertex.getPosition().x;
            data[bufferPosition++] = vertex.getPosition().y;
            data[bufferPosition++] = vertex.getPosition().z;
            data[bufferPosition++] = vertex.getTextureCoords().x;
            data[bufferPosition++] = vertex.getTextureCoords().y;
        }
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * 5);
        buffer.put(data).flip();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public VBO(PlayerVertex[] vertices) {
        id = glGenBuffers();
        float[] data = new float[vertices.length * 6];
        int bufferPosition = 0;
        for (PlayerVertex vertex : vertices) {
            data[bufferPosition++] = vertex.getPosition().x;
            data[bufferPosition++] = vertex.getPosition().y;
            data[bufferPosition++] = vertex.getPosition().z;
            data[bufferPosition++] = vertex.getTextureCoords().x;
            data[bufferPosition++] = vertex.getTextureCoords().y;
            data[bufferPosition++] = vertex.getPartId();
        }
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * 6);
        buffer.put(data).flip();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public VBO(VillagerVertex[] vertices) {
        id = glGenBuffers();
        float[] data = new float[vertices.length * 6];
        int bufferPosition = 0;
        for (VillagerVertex vertex : vertices) {
            data[bufferPosition++] = vertex.getPosition().x;
            data[bufferPosition++] = vertex.getPosition().y;
            data[bufferPosition++] = vertex.getPosition().z;
            data[bufferPosition++] = vertex.getTextureCoords().x;
            data[bufferPosition++] = vertex.getTextureCoords().y;
            data[bufferPosition++] = vertex.getPartId();
        }
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * 6);
        buffer.put(data).flip();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public VBO(float[] vertices) {
        id = glGenBuffers();
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
        buffer.put(vertices).flip();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public VBO(int bufferSize, int drawMethod) {
        id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(bufferSize);
        glBufferData(GL_ARRAY_BUFFER, buffer, drawMethod);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void bufferFloat(FloatBuffer buffer, int drawMethod) {
        this.bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, drawMethod);
    }

    public int getId() {
        return id;
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void delete() {
        glDeleteBuffers(id);
    }

}
