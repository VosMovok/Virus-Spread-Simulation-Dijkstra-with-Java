package core;

import java.util.ArrayList;

public class Graph {
    private final ArrayList<Vertex> vertices;
    public Graph(int numberVertices){
        vertices = new ArrayList<>();
        for(int i=0;i<numberVertices;i++){
            vertices.add(new Vertex("v"+ i));
        }
    }

    public boolean addEdge(int src, int dest, int weight){
        if (src == dest) return false;

        Vertex s = vertices.get(src);
        for (Edge v : s.neighbours)
            if (v.target == vertices.get(dest)) return false;

        Edge new_edge = new Edge(vertices.get(dest),weight);
        s.neighbours.add(new_edge);
        return true;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int vert){
        return vertices.get(vert);
    }
}