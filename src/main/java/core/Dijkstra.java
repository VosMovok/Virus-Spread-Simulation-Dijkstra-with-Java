package core;

import util.PriorityQueue;

import java.util.LinkedList;

public class Dijkstra {
    public static void calculate(Vertex source) {
        source.minDistance = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(source);

        while (!queue.isEmpty()) {

            Vertex u = queue.poll();

            for (int i = 0; i < u.neighbours.size(); i++) {
                Edge neighbour = u.neighbours.get(i);
                double newDist = u.minDistance + neighbour.weight;

                if (neighbour.target.minDistance > newDist) {
                    // Remove the node from the queue to update the distance value.
                    queue.remove(neighbour.target);
                    neighbour.target.minDistance = newDist;

                    // Take the path visited till now and add the new node.s
                    neighbour.target.path = new LinkedList<>(u.path);
                    neighbour.target.path.add(u);

                    //Reenter the node with new distance.
                    queue.add(neighbour.target);
                }
            }
        }
    }
}
