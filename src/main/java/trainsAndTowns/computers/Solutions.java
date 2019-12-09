package trainsAndTowns.computers;

import trainsAndTowns.classes.Edge;
import trainsAndTowns.classes.Graph;

import java.util.*;

public class Solutions {
    public static Integer getDistance(String path, Graph graph){
        int result = 0;

        LinkedList<Character> route = new LinkedList<>();
        for (int i = 0; i < path.length(); i++) {
            route.add(path.charAt(i));
        }

        while (route.size() > 1){
            List<Edge> edges = graph.getEdge(route.get(0));
            Edge destination = edges.stream()
                    .filter(edge -> route.get(1).equals(edge.getDestination()))
                    .findAny()
                    .orElse(null);

            route.removeFirst();

            if (destination != null){
                result += destination.getWeight();
            } else {
                result = 0;
                route.clear();
            }
        }
        return result;
    }

    public static List<List<Character>> getNumberOfTrips(Graph graph, Character start, Character destination){
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        List<List<Character>> result = new ArrayList<>();

        stack.push(start);
        visited.add(start);

        dfs(graph,start,destination,stack,visited, result);

        return result;
    }

    private static void dfs(
            Graph graph,
            Character start,
            Character destination,
            Stack<Character> stack,
            Set<Character> visited,
            List<List<Character>> result
    ){
        for (Edge edge: graph.getEdge(start)){
            if (edge.getDestination() == destination){
                stack.push(edge.getDestination());
                List<Character> solution = new ArrayList<>(stack);
                result.add(solution);
                stack.pop();
            }

            if (!visited.contains(edge.getDestination())){
                stack.push(edge.getDestination());
                visited.add(edge.getDestination());
                dfs(graph,edge.getDestination(),destination,stack,visited, result);
            }
        }

        visited.remove(start);
        stack.pop();
    }

    public static int getShortestDistanceRoute(Graph graph, Character start, Character destination){
        Map<Character,Edge> table = new HashMap<>();
        Set<Character> vertices = graph.getVerticesKeys();
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        for (Character vertex: vertices){
            table.put(vertex, new Edge(start, vertex == start ? 0 : Integer.MAX_VALUE));
        }

        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()){
            Character current = queue.poll();

            for(Edge edge : graph.getEdge(current)){
                if (start == destination && edge.getDestination() == destination){
                    table.get(edge.getDestination()).setWeight(Integer.MAX_VALUE);
                    visited.remove(edge.getDestination());
                }

                if (!visited.contains(edge.getDestination())){
                    visited.add(edge.getDestination());
                    queue.add(edge.getDestination());

                    Edge previousRow = table.get(current);
                    Edge currentRow = table.get(edge.getDestination());

                    if (previousRow.getWeight() + edge.getWeight() < currentRow.getWeight()){
                        currentRow.setWeight(previousRow.getWeight() + edge.getWeight());
                        currentRow.setDestination(edge.getDestination());
                    }
                }
            }

        }

        return table.get(destination).getWeight();
    }

    public static int getNumberOfTripsLessThan(Graph graph, Character start, Character destination, Integer limit){
        Stack<Character> stack = new Stack<>();
        Stack<Integer> distance = new Stack<>();
        List<List<Character>> result = new ArrayList<>();

        stack.push(start);
        distance.push(0);

        loop(graph, start, destination, distance, limit, stack, result);

        return result.size() -1;
    }

    private static void loop(
            Graph graph,
            Character start,
            Character destination,
            Stack<Integer> distance,
            Integer limit,
            Stack<Character> stack,
            List<List<Character>> result
    ){
        for (Edge edge: graph.getEdge(start)){
            if (distance.lastElement() + edge.getWeight() < limit){
                stack.push(edge.getDestination());
                distance.push(distance.lastElement() + edge.getWeight());

                loop(graph,edge.getDestination(),destination,distance, limit, stack, result);
            }
        }

        if (stack.lastElement() == destination){
            List<Character> solution = new ArrayList<>(stack);
            result.add(solution);
        }
        stack.pop();
        distance.pop();
    }
}
