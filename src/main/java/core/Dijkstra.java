import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Dijkstra {
    public static Map<String, Object> dijkstra(Map<String, Map<String, Map<String, Integer>>> graph, String start,
            String finish) {
        PriorityQueue<Map.Entry<String, Integer>> nodes = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        String smallest = "";
        List<String> shortestPath = new ArrayList<>();

        for (String vertex : graph.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                nodes.offer(Map.entry(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                nodes.offer(Map.entry(vertex, Integer.MAX_VALUE));
            }
            previous.put(vertex, null);
        }

        while (!nodes.isEmpty()) {
            smallest = nodes.poll().getKey();
            if (smallest.equals(finish)) {
                while (previous.get(smallest) != null) {
                    shortestPath.add(smallest);
                    smallest = previous.get(smallest);
                }
                break;
            }

            if (smallest != null || distances.get(smallest) != Integer.MAX_VALUE) {
                for (String neighbor : graph.get(smallest).keySet()) {
                    Map<String, Integer> nextNode = graph.get(smallest).get(neighbor);
                    int candidate = distances.get(smallest) + nextNode.get("weight");
                    if (candidate < distances.get(nextNode.get("node"))) {
                        distances.put(nextNode.get("node").toString(), candidate);
                        previous.put(nextNode.get("node").toString(), smallest);
                        nodes.offer(Map.entry(nextNode.get("node").toString(), candidate));
                    }
                }
            }
        }

        List<String> finalPath = new ArrayList<>();
        finalPath.add(smallest);
        finalPath.addAll(shortestPath);
        int totalDistance = distances.get(finalPath.get(finalPath.size() - 1));
        result.put("finalPath", finalPath);
        result.put("totalDistance", totalDistance);

        return result;
    }
}
