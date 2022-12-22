package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class ConvertDataForPlotting {
    static class Data {
        Map<String, Edge[]> net;
    }

    static class Edge {
        String node;
        int weight;
    }

    static class Converted {
        Node[] nodes;
        Link[] links;
    }

    static class Node {
        String id;
        int group;
    }

    static class Link {
        String source;
        String target;
        int weight;
    }

    public static Converted convertDataForPlotting() throws IOException {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader("../data/social-net.json"));
        Data data = gson.fromJson(reader, new TypeToken<Data>(){}.getType());
        reader.close();

        String nameSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Converted converted = new Converted();
        converted.nodes = new Node[nameSet.length()];
        converted.links = new Link[nameSet.length() * data.net.size()];

        int linkIndex = 0;
        for (int i = 0; i < nameSet.length(); i++) {
            char letter = nameSet.charAt(i);
            // Adding nodes
            if (letter == 'A') {
                converted.nodes[i] = new Node();
                converted.nodes[i].id = String.valueOf(letter);
                converted.nodes[i].group = 3;
            } else if (letter == 'Z') {
                converted.nodes[i] = new Node();
                converted.nodes[i].id = String.valueOf(letter);
                converted.nodes[i].group = 3;
            } else {
                converted.nodes[i] = new Node();
                converted.nodes[i].id = String.valueOf(letter);
                converted.nodes[i].group = 1;
            }

            // Adding edges
            Edge[] edges = data.net.get(String.valueOf(letter));
            for (Edge edge : edges) {
                converted.links[linkIndex] = new Link();
                converted.links[linkIndex].source = String.valueOf(letter);
                converted.links[linkIndex].target = edge.node;
                converted.links[linkIndex].weight = edge.weight;
                linkIndex++;
            }
        }

        String json = gson.toJson(converted);
        FileWriter writer = new FileWriter("../data/[PLOT]social-net.json");
        writer.write(json);
        writer.close();

        return converted;
    }
}
//Note:
//
//        In Java, you don't need to import a library to read and write files. You can use the `File



