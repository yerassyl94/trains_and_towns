package trainsAndTowns.utils;

import trainsAndTowns.classes.Graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GraphBuilder {
    public static Graph graphBuilder(String edgesString){
        //removing space between input and make it upper case
        edgesString = edgesString.replaceAll(" ","").toUpperCase();

        //converting string to array of substrings
        String[] edges = edgesString.split(",");

        //initializing set of vertices
        Set<Character> vertices = new HashSet<>(Collections.emptySet());

        //pushing edge to set, to get vertices
        for (String edge : edges) {
            vertices.add(edge.charAt(0));
            vertices.add(edge.charAt(0));
        }

        //initializing graph
        Graph graph = new Graph(vertices.size());

        //adding graph values
        for (String edge: edges){
            graph.addEdge(edge.charAt(0), edge.charAt(1), Character.getNumericValue(edge.charAt(2)));
        }

        return graph;
    }
}
