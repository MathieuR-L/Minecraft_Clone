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

import fr.math.minecraft.client.buffers.VAO;
import fr.math.minecraft.client.buffers.VBO;
import fr.math.minecraft.client.meshs.model.VillagerModel;
import fr.math.minecraft.client.vertex.VillagerVertex;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.opengl.GL33.*;

public class VillagerMesh extends Mesh {

    private VillagerVertex[] vertices;

    public VillagerMesh() {
        this.build();
        this.init();
    }

    private void build() {
        ArrayList<VillagerVertex> vertex = new ArrayList<>();

        this.addFace(vertex, VillagerModel.VILLAGER_HEAD_POS);
        this.addFace(vertex, VillagerModel.VILLAGER_NOSE_POS);
        this.addFace(vertex, VillagerModel.VILLAGER_CHEST_POS);
        this.addFace(vertex, VillagerModel.VILLAGER_LEFT_HAND);
        this.addFace(vertex, VillagerModel.VILLAGER_RIGHT_HAND);
        this.addFace(vertex, VillagerModel.VILLAGER_BOTTOM_HAND_POS);
        this.addFace(vertex, VillagerModel.VILLAGER_LEFT_LEG);
        this.addFace(vertex, VillagerModel.VILLAGER_RIGHT_LEG);

        /* Second layer */

        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_HEAD_POS));
        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_NOSE_POS));
        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_CHEST_POS));
        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_LEFT_HAND));
        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_RIGHT_HAND));
        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_BOTTOM_HAND_POS));
        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_LEFT_LEG));
        this.addFace(vertex, scale(VillagerModel.LAYER_VILLAGER_RIGHT_LEG));

        vertices = vertex.toArray(new VillagerVertex[0]);
    }

    private VillagerVertex[] scale(VillagerVertex[] vertices) {
        VillagerVertex[] scaleVertices = new VillagerVertex[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            VillagerVertex vertex = new VillagerVertex(vertices[i]);
            Vector3f position = vertex.getPosition();
            position.mul(1.15f);
            scaleVertices[i] = vertex;
        }
        return scaleVertices;
    }

    private void addFace(ArrayList<VillagerVertex> vertices, VillagerVertex[] vertex) {
        vertices.addAll(Arrays.asList(vertex));
    }

    @Override
    public void init() {
        this.vao = new VAO();
        vao.bind();

        vbo = new VBO(vertices);

        vao.linkAttrib(vbo, 0, 3, GL_FLOAT, 6 * Float.BYTES, 0);
        vao.linkAttrib(vbo, 1, 2, GL_FLOAT, 6 * Float.BYTES, 3 * Float.BYTES);
        vao.linkAttrib(vbo, 2, 1, GL_FLOAT, 6 * Float.BYTES, 5 * Float.BYTES);

        vao.unbind();
        vbo.unbind();
    }

    @Override
    public void draw() {
        vao.bind();
        glDrawArrays(GL_TRIANGLES, 0, vertices.length);
        vao.unbind();
    }
}
