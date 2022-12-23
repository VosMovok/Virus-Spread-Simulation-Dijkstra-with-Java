package core;

import java.util.ArrayList;

public class Graph {
    private final ArrayList<Vertex> vertices;

    public Graph(int numberVertices) {
        vertices = new ArrayList<>();
        for (int i = 0; i < numberVertices; i++) {
            vertices.add(new Vertex("v" + i));
        }
    }

    public Graph(){
        vertices = new ArrayList<>();
    }

    public void addVertex(String name) {
        vertices.add(new Vertex(name));
    }

    public boolean addEdge(int source, int destination, int weight) {
        if (source == destination) return false;

        Vertex s = vertices.get(source);
        for (Edge v : s.neighbours)
            if (v.target == vertices.get(destination)) return false;

        Edge new_edge = new Edge(vertices.get(destination), weight);
        s.neighbours.add(new_edge);
        return true;
    }

    public boolean addEdge(String source, String destination, int weight) {
        if (source == destination) return false;
        Vertex src = null;
        for (Vertex vertex : vertices) {
            if (vertex.name.equals(source)) {
                src = vertex;
                break;
            }
        }
        if (src == null) return false;
        Vertex dest = null;

        for (Vertex vertex : vertices) {
            if (vertex.name.equals(destination)) {
                dest = vertex;
                break;
            }
        }

        if (dest == null) return false;
        for (Edge v : src.neighbours)
            if (v.target == dest) return false;

        Edge new_edge = new Edge(dest, weight);
        src.neighbours.add(new_edge);
        return true;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int vert) {
        return vertices.get(vert);
    }
}