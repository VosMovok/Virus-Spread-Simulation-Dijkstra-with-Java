import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> population = new ArrayList<>();
        WeightedGraph graph = new WeightedGraph();
        String nameSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();

        // adding individuals to population
        for (int i = 0; i < nameSet.length(); i++) {
            population.add(Character.toString(nameSet.charAt(i)));
        }

        // adding individuals to social net
        for (String individual : population) {
            graph.addVertex(individual);
        }

        // connecting individuals randomly at least 2 times each
        int possibleConnections, candidateIndx, maxConnNumber;
        boolean connected;
        for (int i = 0; i < population.size(); i++) {
            maxConnNumber = 1; // more connections = higher possibility of infection = less ETA
            possibleConnections = population.size() - 1;
            if (i == 0) {
                possibleConnections--;
            } else if (i == population.size() - 1) {
                possibleConnections--;
            }
            while (maxConnNumber != 0) {
                connected = false;
                while (!connected) {
                    candidateIndx = rand.nextInt(possibleConnections);
                    if (i < population.size() - 1) {
                        candidateIndx++;
                    }
                    connected = graph.addEdge(population.get(i), population.get(candidateIndx), rand.nextInt(5) + 1);
                }
                maxConnNumber--;
            }
        }

        System.out.println("Population:");
        System.out.println(graph);

        // write graph to JSON file
        try {
            File file = new File("data/social-net.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(graph.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing JSON Object to File.");
            e.printStackTrace();
        }

        /*
         * +-----------------SOCIAL DISTANCING-----------------+
         * | range from 1 to 5 |
         * | * 1 is a strong edge, the individuals |
         * | with a strong edge are more exposed |
         * | to the virus. 5 is the weakest |
         * | |
         * | * Every number represent even the |
         * | amount of days passed |
         * | |
         */
        Path path = dijkstra(graph, population.get(0), population.get(population.size() - 1));
        System.out.println("Final path " + path.finalPath + ", days passed " + path.totalDistance);

        // ask user if they want to convert data for plotting
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you want to make a cleaner version of the data for plotting? [y/n] ");
        String answer = scanner.nextLine();
        if (answer.equals("y")) {
            convertDataForPlotting();
            System.out.println("Data successfully polished.");
        } else {
            System.out.println("if you want to plot make sure to have the right format!");
        }

        // write result to CSV file
        try {
            File file = new File("data/result.csv");
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            Writer writer = new BufferedWriter(osw);
            BeanToCsv<Result> beanToCsv = new StatefulBeanToCsvBuilder<Result>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            Result result = new Result(path.finalPath, path.totalDistance);
            beanToCsv.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing CSV file.");
            e.printStackTrace();
        }
    }
}

