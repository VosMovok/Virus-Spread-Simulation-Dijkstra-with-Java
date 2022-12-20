package core;
import java.util.ArrayList;
import java.util.LinkedList;

public class WeightedGraph {
    private LinkedList<Graph> net;

    public WeightedGraph() {
        net = new LinkedList<>();
    }

    public void addVertex(String vertex) {
        for(Graph Checkvertex : net) {
            if (!(Checkvertex.name == vertex)) {
                net.add(new Graph(vertex, new LinkedList<>()));
            }
        }

    }

    public boolean addEdge(String vertex1, String vertex2, int weight) {
        LinkedList<Edge> edges1 = null;
        LinkedList<Edge> edges2 = null;
        for(Graph Checkvertex : net) {
            if (Checkvertex.name == vertex1) {
                edges1 = Checkvertex.edges;
            }
        }
        for(Graph Checkvertex : net) {
            if (Checkvertex.name == vertex2) {
                edges2 = Checkvertex.edges;
            }
        }
        if (edges1 == null || edges2 == null) {
            return false;
        }

        boolean duplicate = false;
        for (Edge edge : edges1) {
            if (edge.node.equals(vertex2)) {
                duplicate = true;
                break;
            }
        }

        if (!duplicate) {
            edges1.add(new Edge(vertex2, weight));
            edges2.add(new Edge(vertex1, weight));
            return true;
        } else {
            return false;
        }
    }

}
