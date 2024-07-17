package fr.math.minecraft.client.events;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class ReceiveMapEvent {
    private final int serie, nextSerie;
    private final ArrayNode blocksNode;

    public ReceiveMapEvent(int serie, int nextSerie, ArrayNode blocksNode) {
        this.serie = serie;
        this.nextSerie = nextSerie;
        this.blocksNode = blocksNode;
    }

    public ArrayNode getBlocksNode() {
        return blocksNode;
    }

    public int getSerie() {
        return serie;
    }

    public int getNextSerie() {
        return nextSerie;
    }
}
