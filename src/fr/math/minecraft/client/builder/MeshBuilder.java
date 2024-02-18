package fr.math.minecraft.client.builder;

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.meshs.model.NatureModel;
import fr.math.minecraft.client.packet.ChunkEmptyPacket;
import fr.math.minecraft.client.vertex.Vertex;
import fr.math.minecraft.client.meshs.model.BlockModel;
import fr.math.minecraft.client.world.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeshBuilder {

    private static int counter = 0;

    public boolean isEmpty (int worldX, int worldY, int worldZ) {
        return this.isEmpty(worldX, worldY, worldZ, true);
    }

    public boolean isEmpty(int worldX, int worldY, int worldZ, boolean occlusionMode) {

        Coordinates coordinates = new Coordinates(worldX, worldY, worldZ);

        int chunkX = (int) Math.floor(worldX / (double) Chunk.SIZE);
        int chunkY = (int) Math.floor(worldY / (double) Chunk.SIZE);
        int chunkZ = (int) Math.floor(worldZ / (double) Chunk.SIZE);

        World world = Game.getInstance().getWorld();
        Chunk chunk = world.getChunk(chunkX, chunkY, chunkZ);

        int blockX = worldX % Chunk.SIZE;
        int blockY = worldY % Chunk.SIZE;
        int blockZ = worldZ % Chunk.SIZE;

        blockX = blockX < 0 ? blockX + Chunk.SIZE : blockX;
        blockY = blockY < 0 ? blockY + Chunk.SIZE : blockY;
        blockZ = blockZ < 0 ? blockZ + Chunk.SIZE : blockZ;

        if (chunk == null) return true;

        if (occlusionMode) {
            return chunk.getBlock(blockX, blockY, blockZ) == Material.AIR.getId();
        }

        return world.getTransparents().contains(chunk.getBlock(blockX, blockY, blockZ));
    }

    public static Vector2f[] calculateTexCoords(int x, int y, float format) {
        Vector2f texCoordsBottomLeft = new Vector2f(x/format, y/format);
        Vector2f texCoordsUpLeft = new Vector2f(x/format, (y + 1)/format);
        Vector2f texCoordsUpRight = new Vector2f((x + 1)/format, (y + 1)/format);
        Vector2f texCoordsBottomRight = new Vector2f((x + 1)/format, y/format);

        Vector2f[] texCoords = {
            texCoordsBottomLeft,
            texCoordsUpLeft,
            texCoordsUpRight,
            texCoordsUpRight,
            texCoordsBottomRight,
            texCoordsBottomLeft,
        };

        return texCoords;
    }


    public Vertex[] buildChunkMesh(Chunk chunk) {
        BlockModel blockModel = new BlockModel();
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int x = 0; x < Chunk.SIZE; x++) {
            for (int y = 0; y < Chunk.SIZE; y++) {
                for (int z = 0; z < Chunk.SIZE; z++) {

                    byte block = chunk.getBlock(x, y, z);
                    if (block == Material.AIR.getId()) continue;

                    Material material = Material.getMaterialById(block);

                    if (material == null) material = Material.DEBUG;

                    int worldX = x + chunk.getPosition().x * Chunk.SIZE;
                    int worldY = y + chunk.getPosition().y * Chunk.SIZE;
                    int worldZ = z + chunk.getPosition().z * Chunk.SIZE;

                    boolean px = isEmpty(worldX + 1, worldY, worldZ, false);
                    boolean nx = isEmpty(worldX - 1, worldY, worldZ, false);
                    boolean py = isEmpty(worldX, worldY + 1, worldZ, false);
                    boolean ny = isEmpty(worldX, worldY - 1, worldZ, false);
                    boolean pz = isEmpty(worldX, worldY, worldZ + 1, false);
                    boolean nz = isEmpty(worldX, worldY, worldZ - 1, false);

                    Vector2f[] textureCoords;

                    if(material == Material.WEED || material == Material.ROSE) {
                        textureCoords = calculateTexCoords(material.getX(), material.getY(), 16.0f);

                        for (int k = 0; k < 6; k++)  {
                            Vector3f blockVector = new Vector3f(x, y, z);
                            vertices.add(new Vertex(blockVector.add(NatureModel.FIRST_FACE[k]), textureCoords[k],material.getId(),0, 3));
                        }

                        for (int k = 0; k < 6; k++)  {
                            Vector3f blockVector = new Vector3f(x, y, z);
                            vertices.add(new Vertex(blockVector.add(NatureModel.SECOND_FACE[k]), textureCoords[k],material.getId(),0, 3));
                        }
                    } else {
                        if (px) {
                            if(material.isFaces()) {
                                textureCoords = calculateTexCoords(material.getPx().x, material.getPx().y, 16.0f);
                            } else {
                                textureCoords = calculateTexCoords(material.getX(), material.getY(), 16.0f);

                            }

                            Map<Integer, Integer> map = calculateAmbiantOcclusion(worldX, worldY, worldZ, BlockFace.PX_FACE);

                            for (int k = 0; k < 6; k++)  {
                                Vector3f blockVector = new Vector3f(x, y, z);
                                vertices.add(new Vertex(blockVector.add(BlockModel.PX_POS[k]), textureCoords[k],material.getId(),0, map.get(k)));
                            }
                        }

                        if (nx) {
                            if(material.isFaces()) {
                                textureCoords = calculateTexCoords(material.getNx().x, material.getNx().y, 16.0f);
                            } else {
                                textureCoords = calculateTexCoords(material.getX(), material.getY(), 16.0f);

                            }

                            Map<Integer, Integer> map = calculateAmbiantOcclusion(worldX, worldY, worldZ, BlockFace.NX_FACE);

                            for (int k = 0; k < 6; k++)  {
                                Vector3f blockVector = new Vector3f(x, y, z);
                                vertices.add(new Vertex(blockVector.add(BlockModel.NX_POS[k]), textureCoords[k],material.getId(),1, map.get(k)));
                            }
                        }

                        if (py) {
                            if(material.isFaces()) {
                                textureCoords = calculateTexCoords(material.getPy().x, material.getPy().y, 16.0f);
                            } else {
                                textureCoords = calculateTexCoords(material.getX(), material.getY(), 16.0f);

                            }

                            Map<Integer, Integer> map = calculateAmbiantOcclusion(worldX, worldY, worldZ, BlockFace.PY_FACE);

                            for (int k = 0; k < 6; k++)  {
                                Vector3f blockVector = new Vector3f(x, y, z);
                                vertices.add(new Vertex(blockVector.add(BlockModel.PY_POS[k]), textureCoords[k],material.getId(),2, map.get(k)));
                            }
                        }

                        if (ny) {
                            if(material.isFaces()) {
                                textureCoords = calculateTexCoords(material.getNy().x, material.getNy().y, 16.0f);
                            } else {
                                textureCoords = calculateTexCoords(material.getX(), material.getY(), 16.0f);

                            }

                            Map<Integer, Integer> map = calculateAmbiantOcclusion(worldX, worldY, worldZ, BlockFace.NY_FACE);

                            for (int k = 0; k < 6; k++)  {
                                Vector3f blockVector = new Vector3f(x, y, z);
                                vertices.add(new Vertex(blockVector.add(BlockModel.NY_POS[k]), textureCoords[k],material.getId(),3, map.get(k)));
                            }
                        }

                        if (pz) {
                            if(material.isFaces()) {
                                textureCoords = calculateTexCoords(material.getPz().x, material.getPz().y, 16.0f);
                            } else {
                                textureCoords = calculateTexCoords(material.getX(), material.getY(), 16.0f);

                            }

                            Map<Integer, Integer> map = calculateAmbiantOcclusion(worldX, worldY, worldZ, BlockFace.PZ_FACE);

                            for (int k = 0; k < 6; k++)  {
                                Vector3f blockVector = new Vector3f(x, y, z);
                                vertices.add(new Vertex(blockVector.add(BlockModel.PZ_POS[k]), textureCoords[k],material.getId(),4, map.get(k)));
                            }
                        }

                        if (nz) {
                            if(material.isFaces()) {
                                textureCoords = calculateTexCoords(material.getNz().x, material.getNz().y, 16.0f);
                            } else {
                                textureCoords = calculateTexCoords(material.getX(), material.getY(), 16.0f);

                            }

                            Map<Integer, Integer> map = calculateAmbiantOcclusion(worldX, worldY, worldZ, BlockFace.NZ_FACE);

                            for (int k = 0; k < 6; k++)  {
                                Vector3f blockVector = new Vector3f(x, y, z);
                                vertices.add(new Vertex(blockVector.add(BlockModel.NZ_POS[k]), textureCoords[k],material.getId(),5, map.get(k)));
                            }
                        }
                    }
                }
            }
        }
        counter = 0;

        return vertices.toArray(new Vertex[0]);
    }

    public Map<Integer, Integer> calculateAmbiantOcclusion(int worldX, int worldY, int worldZ, BlockFace face) {

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        int h = 0;

        switch (face) {
            case PX_FACE:
                a = isEmpty(worldX + 1, worldY + 1, worldZ) ? 1 : 0;
                b = isEmpty(worldX + 1, worldY + 1, worldZ + 1) ? 1 : 0;
                c = isEmpty(worldX + 1, worldY, worldZ + 1) ? 1 : 0;
                d = isEmpty(worldX + 1, worldY - 1, worldZ + 1) ? 1 : 0;
                e = isEmpty(worldX + 1, worldY - 1, worldZ) ? 1 : 0;
                f = isEmpty(worldX + 1, worldY - 1, worldZ - 1) ? 1 : 0;
                g = isEmpty(worldX + 1, worldY, worldZ - 1) ? 1 : 0;
                h = isEmpty(worldX + 1, worldY + 1, worldZ - 1) ? 1 : 0;
                break;
            case PY_FACE:
                a = isEmpty(worldX, worldY + 1, worldZ - 1) ? 1 : 0;
                b = isEmpty(worldX + 1, worldY + 1, worldZ - 1) ? 1 : 0;
                c = isEmpty(worldX + 1, worldY + 1, worldZ) ? 1 : 0;
                d = isEmpty(worldX + 1, worldY + 1, worldZ + 1) ? 1 : 0;
                e = isEmpty(worldX, worldY + 1, worldZ + 1) ? 1 : 0;
                f = isEmpty(worldX - 1, worldY + 1, worldZ + 1) ? 1 : 0;
                g = isEmpty(worldX - 1, worldY + 1, worldZ) ? 1 : 0;
                h = isEmpty(worldX - 1, worldY + 1, worldZ - 1) ? 1 : 0;
                break;
            case PZ_FACE:
                a = isEmpty(worldX, worldY + 1, worldZ + 1) ? 1 : 0;
                b = isEmpty(worldX + 1, worldY + 1, worldZ + 1) ? 1 : 0;
                c = isEmpty(worldX + 1, worldY, worldZ + 1) ? 1 : 0;
                d = isEmpty(worldX + 1, worldY - 1, worldZ + 1) ? 1 : 0;
                e = isEmpty(worldX, worldY - 1, worldZ + 1) ? 1 : 0;
                f = isEmpty(worldX - 1, worldY - 1, worldZ + 1) ? 1 : 0;
                g = isEmpty(worldX - 1, worldY, worldZ + 1) ? 1 : 0;
                h = isEmpty(worldX - 1, worldY + 1, worldZ + 1) ? 1 : 0;
                break;
            case NX_FACE:
                a = isEmpty(worldX - 1, worldY + 1, worldZ) ? 1 : 0;
                b = isEmpty(worldX - 1, worldY + 1, worldZ + 1) ? 1 : 0;
                c = isEmpty(worldX - 1, worldY, worldZ + 1) ? 1 : 0;
                d = isEmpty(worldX - 1, worldY - 1, worldZ + 1) ? 1 : 0;
                e = isEmpty(worldX - 1, worldY - 1, worldZ) ? 1 : 0;
                f = isEmpty(worldX - 1, worldY - 1, worldZ - 1) ? 1 : 0;
                g = isEmpty(worldX - 1, worldY, worldZ - 1) ? 1 : 0;
                h = isEmpty(worldX - 1, worldY + 1, worldZ - 1) ? 1 : 0;
                break;
            case NY_FACE:
                a = isEmpty(worldX, worldY - 1, worldZ - 1) ? 1 : 0;
                b = isEmpty(worldX + 1, worldY - 1, worldZ - 1) ? 1 : 0;
                c = isEmpty(worldX + 1, worldY - 1, worldZ) ? 1 : 0;
                d = isEmpty(worldX + 1, worldY - 1, worldZ + 1) ? 1 : 0;
                e = isEmpty(worldX, worldY - 1, worldZ + 1) ? 1 : 0;
                f = isEmpty(worldX - 1, worldY - 1, worldZ + 1) ? 1 : 0;
                g = isEmpty(worldX - 1, worldY - 1, worldZ) ? 1 : 0;
                h = isEmpty(worldX - 1, worldY - 1, worldZ - 1) ? 1 : 0;
                break;
            case NZ_FACE:
                a = isEmpty(worldX, worldY + 1, worldZ - 1) ? 1 : 0;
                b = isEmpty(worldX + 1, worldY + 1, worldZ - 1) ? 1 : 0;
                c = isEmpty(worldX + 1, worldY, worldZ - 1) ? 1 : 0;
                d = isEmpty(worldX + 1, worldY - 1, worldZ - 1) ? 1 : 0;
                e = isEmpty(worldX, worldY - 1, worldZ - 1) ? 1 : 0;
                f = isEmpty(worldX - 1, worldY - 1, worldZ - 1) ? 1 : 0;
                g = isEmpty(worldX - 1, worldY, worldZ - 1) ? 1 : 0;
                h = isEmpty(worldX - 1, worldY + 1, worldZ - 1) ? 1 : 0;
                break;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, g + f + e);
        map.put(1, g + h + a);
        map.put(2, a + b + c);
        map.put(3, a + b + c);
        map.put(4, c + d + e);
        map.put(5, g + f + e);

        return map;
    }

}
