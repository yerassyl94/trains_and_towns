package trainsAndTowns.classes;

import java.util.*;

public class Graph {
    //number of vertices of graph
    int vertices;
    //graph initialization
    HashMap<Character, List<Edge>> adjacencyList;

    //constructor
    public Graph(int vertices){
        this.vertices = vertices;
        this.adjacencyList = new HashMap<>();
    }

    //add Edge to graph
    public void addEdge(char source, char destination, int weight){
        if (!this.adjacencyList.containsKey(source)){
            this.adjacencyList.put(source, new ArrayList<>());
        }

        Edge edge = new Edge( destination, weight);
        this.adjacencyList.get(source).add(edge);
    }

    //get Edge by key
    public List<Edge> getEdge(char key){
        return this.adjacencyList.get(key);
    }

    //set vertex
    public Set<Character> getVerticesKeys(){
        return this.adjacencyList.keySet();
    }
}
