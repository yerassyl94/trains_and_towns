package trainsAndTowns.classes;

import java.util.*;

public class Graph {
    int vertices;
    HashMap<Character, List<Edge>> adjacencyList;

    public Graph(int vertices){
        this.vertices = vertices;
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(char source, char destination, int weight){
        if (!this.adjacencyList.containsKey(source)){
            this.adjacencyList.put(source, new ArrayList<>());
        }

        Edge edge = new Edge( destination, weight);
        this.adjacencyList.get(source).add(edge);
    }

    public List<Edge> getEdge(char key){
        return this.adjacencyList.get(key);
    }

    public Set<Character> getVerticesKeys(){
        return this.adjacencyList.keySet();
    }
}
