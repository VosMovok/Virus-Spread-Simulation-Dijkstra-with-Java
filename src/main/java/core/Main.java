package core;

import java.util.Random;

public class Main {
    public static void main(String[] arg) {
        // Create a new graph.
        int maxVertices = 100;
        int maxConnections = 2;
        int maxWeights = 20;
        Graph graph = new Graph(maxVertices);

        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < maxVertices; i++) {
            int randConnections = rand.nextInt(0, maxConnections + 1);
            for (int j = 0; j < randConnections; j++) {
                int randWeight = rand.nextInt(1, maxWeights + 1);
                int randDest = rand.nextInt(0, maxVertices);
                boolean success = graph.addEdge(i, randDest, randWeight);
                if (success) j--;
            }
        }

        // Calculate Dijkstra.
        Dijkstra.calculate(graph.getVertex(0));

        // Print the minimum Distance.
        for (int i = 0; i < graph.getVertices().size(); i++) {
            Vertex v = graph.getVertices().get(i);
            System.out.print("Vertex - " + v + ", Dist - " + v.minDistance + ", Path - ");
            for (int j = 0; j < v.path.size(); j++) {
                Vertex pathvert = v.path.get(j);
                System.out.print(pathvert + " ");
            }
            System.out.println("" + v);
        }

    }
}
