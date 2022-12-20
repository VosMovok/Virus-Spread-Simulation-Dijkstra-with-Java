package core;

import java.util.LinkedList;

public class Graph {
    String name;
    LinkedList<Edge> edges;
    Graph (String name, LinkedList<Edge> edges) {
        this.name = name;
        this.edges = edges;
    }
}
