package util;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import helper.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.swing.*;
import java.util.LinkedList;

public class GraphVis {
    public static void show(LinkedList<FullName> vertex, LinkedList<Connection> edges) {
        // Create a JGraphT graph
        SimpleWeightedGraph<String, WeightedEdge> graph =
                new SimpleWeightedGraph<>(WeightedEdge.class);

        // Add vertices to the graph
        for (FullName v : vertex) {
            graph.addVertex(v.getFullname());
        }

        // Add edges to the graph and set their weights
        for (Connection e : edges) {
            WeightedEdge edge = graph.addEdge(e.getSource(), e.getDestination());
            graph.setEdgeWeight(edge, e.getWeight());
        }

        JGraphXAdapter<String, WeightedEdge> jgxAdapter = new JGraphXAdapter<>(graph);
        // Use the JGraphX layout algorithms to arrange the vertices in a circle
        mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

        // Create a component to display the graph
        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);

        // Modify the rendering of the edges to include the weight information
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put("html", "<html><div style='text-align:center;'>{label}</div></html>");
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put("edgeStyle", "orthogonalEdgeStyle");
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put("endArrow", "classic");
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put("endSize", "16");

        // Add the graph component to a frame
        JFrame frame = new JFrame();
        frame.getContentPane().add(graphComponent);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class WeightedEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
}
