package fr.math.minecraft.server.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Node {

    private String data;
    private HashMap<String, Node> options;

    public Node(String data) {
        this.data = data;
        this.options = new HashMap<>();
    }

    private Node getOptionByName(String name) {
        return options.get(name);
    }

    public String displayOption(int i) {
        int y = 0;
        if(i >= 0 && i < options.size()) {
            for (Map.Entry mapentry : options.entrySet()) {
                if(y == i) {
                    return "" + mapentry.getKey();
                }
                y++;
            }
        }
        return null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HashMap<String, Node> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Node> options) {
        this.options = options;
    }
}
