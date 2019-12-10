package trainsAndTowns.computers;

import trainsAndTowns.classes.Edge;
import trainsAndTowns.classes.Graph;

import java.util.*;

public class Solutions {

    public static Integer getDistance(String path, Graph graph){
        int result = 0;

        //Linked list of route
        LinkedList<Character> route = new LinkedList<>();
        for (int i = 0; i < path.length(); i++) {
            route.add(path.charAt(i));
        }

        //loop through route list until the last stop
        while (route.size() > 1){
            //get all possible routes from current stop
            List<Edge> edges = graph.getEdge(route.get(0));

            //find desired next stop
            Edge destination = edges.stream()
                    .filter(edge -> route.get(1).equals(edge.getDestination()))
                    .findAny()
                    .orElse(null);

            //remove current location from routes list because we already visited it
            route.removeFirst();

            if (destination != null){
                //if desired destination have found, add its distance to result value
                result += destination.getWeight();
            } else {
                //if desired destination have not found, means route is impossible
                result = 0;
                route.clear();
            }
        }

        //return route distance
        return result;
    }

    public static List<List<Character>> getNumberOfTrips(Graph graph, Character start, Character destination){
        //initializing stack for pushing routes
        Stack<Character> stack = new Stack<>();
        //initializing set to remember visited tows
        Set<Character> visited = new HashSet<>();
        //result list
        List<List<Character>> result = new ArrayList<>();

        //add starting point to stack and visited set
        stack.push(start);
        visited.add(start);

        //start depth-first search method
        dfs(graph,start,destination,stack,visited, result);

        //return result list
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
        //loop through connected destinations to given town
        for (Edge edge: graph.getEdge(start)){
            //if connected destination is desired,
            if (edge.getDestination() == destination){
                //push to stack
                stack.push(edge.getDestination());
                //convert stack to list of towns
                List<Character> solution = new ArrayList<>(stack);
                //add list of towns to result list
                result.add(solution);
                //remove from stack
                stack.pop();
            }

            //if connected destination have not visited,
            if (!visited.contains(edge.getDestination())){
                //add to stack
                stack.push(edge.getDestination());
                //mark as visited
                visited.add(edge.getDestination());
                //run dfs method recursively with a starting point connected destination
                dfs(graph,edge.getDestination(),destination,stack,visited, result);
            }
        }

        //when loop has finished, remove it from visited list and stack route
        visited.remove(start);
        stack.pop();
    }

    public static int getShortestDistanceRoute(Graph graph, Character start, Character destination){
        //initialize solution map
        Map<Character,Edge> table = new HashMap<>();
        //get all vertices
        Set<Character> vertices = graph.getVerticesKeys();
        //initialize queue of towns to visit next
        Queue<Character> queue = new LinkedList<>();
        //initialize set of visited town
        Set<Character> visited = new HashSet<>();

        //set map keys as vertex
        for (Character vertex: vertices){
            //set edge distance 0, if the destination is the same as starting point
            table.put(vertex, new Edge(start, vertex == start ? 0 : Integer.MAX_VALUE));
        }

        //add starting point to queue and mark it as visited
        queue.add(start);
        visited.add(start);

        //looping through queue
        while(!queue.isEmpty()){
            //get first out as a current location, and remove it from queue
            Character current = queue.poll();

            //loop through all possible routes from current location
            for(Edge edge : graph.getEdge(current)){
                //if we want to make round trip
                if (start == destination && edge.getDestination() == destination){
                    //we need to round trip distance equal to infinity instead of 0
                    table.get(edge.getDestination()).setWeight(Integer.MAX_VALUE);
                    //then remove it from visited
                    visited.remove(edge.getDestination());
                }

                //if possible destination have not visited
                if (!visited.contains(edge.getDestination())){
                    //mark it visited and to queue
                    visited.add(edge.getDestination());
                    queue.add(edge.getDestination());

                    //get distance value from current point and possible point
                    Edge previousRow = table.get(current);
                    Edge currentRow = table.get(edge.getDestination());

                    //if possible point distance is shorter
                    if (previousRow.getWeight() + edge.getWeight() < currentRow.getWeight()){
                        //store it in map
                        currentRow.setWeight(previousRow.getWeight() + edge.getWeight());
                        currentRow.setDestination(edge.getDestination());
                    }
                }
            }

        }

        //return table with shortest route to destination point
        return table.get(destination).getWeight();
    }

    public static int getNumberOfTripsLessThan(Graph graph, Character start, Character destination, Integer limit){
        //initializing stack
        Stack<Character> stack = new Stack<>();
        //initializing stack of distance
        Stack<Integer> distance = new Stack<>();
        //list of result
        List<List<Character>> result = new ArrayList<>();

        //add starting point to stack and a starting point 0
        stack.push(start);
        distance.push(0);

        //starting recursive dfs algorithm
        loop(graph, start, destination, distance, limit, stack, result);

        //returning result list size, add -1 because round trip contains C-> 0
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
        //looping through
        for (Edge edge: graph.getEdge(start)){
            // add last distance stack element and possible route distance and its fits the limit
            if (distance.lastElement() + edge.getWeight() < limit){
                //add destination name to stack
                stack.push(edge.getDestination());
                //add distance
                distance.push(distance.lastElement() + edge.getWeight());

                //recursively call loop function with a new starting point
                loop(graph,edge.getDestination(),destination,distance, limit, stack, result);
            }
        }

        //if last stack element is desired destination
        if (stack.lastElement() == destination){
            //convert stack to list
            List<Character> solution = new ArrayList<>(stack);
            //add to result list
            result.add(solution);
        }

        //remove last position from stacks
        stack.pop();
        distance.pop();
    }
}
