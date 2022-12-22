package core;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] arg) throws PythonExecutionException, IOException {
        // Create a new graph.
        int maxVertices = 100;
        int maxConnections = 6;
        int maxWeights = 5;
        Graph graph = new Graph(maxVertices);

        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < maxVertices; i++) {
            int randConnections = rand.nextInt(0, maxConnections + 1);
            for (int j = 0; j < randConnections; j++) {
                int randWeight = rand.nextInt(1, maxWeights + 1);
                int randDest = rand.nextInt(0, maxVertices);
                boolean success = graph.addEdge(i, randDest, randWeight);
                if (!success) j--;
            }
        }

        // Calculate Dijkstra.
        Dijkstra.calculate(graph.getVertex(0));

        TreeMap<Double, Integer> maxDays = new TreeMap<>();

        boolean infinityDays = false;

        for (int i = 0; i < graph.getVertices().size(); i++) {
            Vertex vertexTemp = graph.getVertices().get(i);
            System.out.print("Vertex - " + vertexTemp + ", Dist - " + vertexTemp.minDistance + ", Path - ");
            maxDays.merge(vertexTemp.minDistance, 1, Integer::sum);
            for (int j = 0; j < vertexTemp.path.size(); j++) {
                Vertex pathVertex = vertexTemp.path.get(j);
                System.out.print(pathVertex + " ");
            }
            System.out.println("" + vertexTemp);
        }
        maxDays.remove(Double.POSITIVE_INFINITY);

        Matplotlib.dataToPlot(maxDays);
//        if (infinityDays)
//            System.out.println("Maximum Days to Infect All Nodes : " + Double.POSITIVE_INFINITY + " Days");
//        else
//            System.out.println("Maximum Days to Infect All Nodes : " + maxDays + " Days");
//        if (maxDays == 0.0)
//            System.out.println("Maximum Days to Infect All Reachable Nodes : " + Double.POSITIVE_INFINITY + " Days");
//        else
//            System.out.println("Maximum Days to Infect All Reachable Nodes : " + maxDays + " Days");

    }
}
