import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeightedGraph {
    private HashMap<String, List<Edge>> net;

    public WeightedGraph() {
        net = new HashMap<>();
    }

    public void addVertex(String vertex) {
        if (!net.containsKey(vertex)) {
            net.put(vertex, new ArrayList<>());
        }
    }

    public boolean addEdge(String vertex1, String vertex2, int weight) {
        List<Edge> edges1 = net.get(vertex1);
        List<Edge> edges2 = net.get(vertex2);
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
