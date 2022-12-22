package helper;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

public class Import {
    public void json(LinkedList<FullName> fullNames, LinkedList<Connection> connections) throws IOException {

        String name = new String(Files.readAllBytes(Paths.get("data/name.json")));
        String conn = new String(Files.readAllBytes(Paths.get("data/connection.json")));
        Gson gson = new Gson();

// Parse the JSON data using the Entry class
        FullName[] fullName = gson.fromJson(name, FullName[].class);
        Connection[] connection = gson.fromJson(conn, Connection[].class);

// Create a linked list to store the fullNames
        // Create a linked list to store the fullNames

// Add the fullNames to the linked list
        fullNames.addAll(Arrays.asList(fullName));
        connections.addAll(Arrays.asList(connection));
    }

    public class FullName {
        private String FullName;

        public String getFullName() {
            return FullName;
        }
    }

    public class Connection {
        private String Source;
        private String Destination;
        private int Weight;


        public String getSource() {
            return Source;
        }

        public String getDestination() {
            return Destination;
        }

        public int getWeight() {
            return Weight;
        }
    }
}
