package helper;

import java.util.LinkedList;

public class Cleaner {
    public static void duplicate(LinkedList<Connection> connections) {
        connections.removeIf(c -> c.getSource().equals(c.getDestination()));
    }
}
