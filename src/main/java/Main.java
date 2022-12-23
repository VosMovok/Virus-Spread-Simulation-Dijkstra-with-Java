import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import core.Dijkstra;
import core.Graph;
import core.Vertex;
import helper.*;
import util.Matplotlib;
import util.GraphVis;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] arg) throws PythonExecutionException, IOException {
        // Create a new graph.
        Graph graph;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Create Random Data? (y/n) : ");
        LinkedList<FullName> fullNames = new LinkedList<>();
        LinkedList<Connection> connections = new LinkedList<>();
        if (scanner.next().charAt(0) == 'y') {
            int maxVertices = 100;
            int maxConnections = 6;
            int maxWeights = 5;
            graph = new Graph(maxVertices);
            Random rand = new Random(System.currentTimeMillis());
            for (int i = 0; i < maxVertices; i++) {
                fullNames.add(new FullName(graph.getVertices().get(i).name));
                int randConnections = rand.nextInt(0, maxConnections + 1);
                for (int j = 0; j < randConnections; j++) {
                    int randWeight = rand.nextInt(1, maxWeights + 1);
                    int randDest = rand.nextInt(0, maxVertices);
                    boolean success = graph.addEdge(i, randDest, randWeight);
                    connections.add(new Connection("v" + i, "v" + randDest, randWeight));
                    if (!success) j--;
                }
            }
        } else {
            System.out.println("Okay! Importing JSON Data");
            Import imp = new Import();
            imp.json(fullNames, connections);
            graph = new Graph();
            for (FullName f : fullNames)
                graph.addVertex(f.getFullname());
            for (Connection c : connections)
                graph.addEdge(c.getSource(), c.getDestination(), c.getWeight());
        }

        // Calculate Dijkstra.
        Dijkstra.calculate(graph.getVertex(0));

        TreeMap<Double, Double> maxDays = new TreeMap<>();

        for (int i = 0; i < graph.getVertices().size(); i++) {
            Vertex vertexTemp = graph.getVertices().get(i);
            System.out.printf("%s %-30s %s %-10s %s", "Vertex -", vertexTemp, "Dist -", vertexTemp.minDistance, "Path - ");
            maxDays.merge(vertexTemp.minDistance, 1.0, Double::sum);
            for (int j = 0; j < vertexTemp.path.size(); j++) {
                Vertex pathVertex = vertexTemp.path.get(j);
                System.out.print(pathVertex + "->");
            }
            System.out.println(vertexTemp);
        }

        if (Collections.max(maxDays.keySet()) == Double.POSITIVE_INFINITY)
            System.out.println("Maximum Days to Infect All Nodes : " + Double.POSITIVE_INFINITY + " Days");
        else
            System.out.println("Maximum Days to Infect All Nodes : " + Collections.max(maxDays.keySet()) + " Days");

        maxDays.remove(Double.POSITIVE_INFINITY);
        if (Collections.max(maxDays.keySet()) == 0.0)
            System.out.println("Maximum Days to Infect All Reachable Nodes : " + Double.POSITIVE_INFINITY + " Days");
        else
            System.out.println("Maximum Days to Infect All Reachable Nodes : " + Collections.max(maxDays.keySet()) + " Days");

        System.out.print("Show Graph Representation and Line Graph? (y/n) : ");
        if (scanner.next().charAt(0) == 'y') {
//            GraphVis.show(fullNames, connections);
            Matplotlib.dataToPlot(maxDays);
        }
        else
            System.out.println("BYE!");

    }
}
