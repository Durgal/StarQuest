package src.main.java.data_structures;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> connections;

    public Node() {
        connections = new ArrayList<>();
    }

    public void connect(Node other) {
        if (!connections.contains(other)) {
            connections.add(other);
        }
    }

    public List<Node> getConnections() {
        return connections;
    }

}