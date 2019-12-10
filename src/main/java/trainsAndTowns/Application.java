package trainsAndTowns;

import trainsAndTowns.classes.Graph;
import trainsAndTowns.computers.Solutions;
import trainsAndTowns.utils.GraphBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {

        //Getting data from command line
        System.out.println("Please provide your graph (ex: AB5,DC4) :");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        //Building graph
        Graph graph = GraphBuilder.graphBuilder(userInput);

        //Solutions for 1-5;
        //taskNum value for ease stdout
        int taskNum = 1;

        //initializing list of given routes to calculate
        List<String> routes = new ArrayList<>(List.of("ABC", "AD", "ADC", "AEBCD", "AED"));

        //looping through list of routes
        for (String route : routes) {
            //getting result
            int result = Solutions.getDistance(route, graph);
            //Print result distance on command line, if distance equals to zero, prints "NO SUCH ROUTE"
            System.out.println("Output #" + taskNum + ": " + (result == 0 ? "NO SUCH ROUTE" : result));
            //increase taskNum value by 1
            taskNum++;
        }

        //Solutions for 6-7

        //initializing result integer
        int output6 = 0;

        //getting all possible routes from given points
        List<List<Character>> allRoutesFromCToC=Solutions.getNumberOfTrips(graph, 'C', 'C');

        //loop through all possible routes to get routes less than 3
        for (List<Character> list : allRoutesFromCToC){
            if (list.size() <= 4){
                output6++;
            }
        }

        //printing result
        System.out.println("Output #" + taskNum + ": " + output6);
        //add up taskNum
        taskNum++;

        //initializing result integer
        int output7 = 0;

        //getting all possible routes from A to C
        List<List<Character>> allRoutesFromAToC = Solutions.getNumberOfTrips(graph,'A', 'C');

        //loop through routes and get those with has exactly 4 stops
        for (List<Character> list : allRoutesFromAToC){
            if (list.size() == 5){
                output7++;
            }
        }

        //printing result
        System.out.println("Output #" + taskNum + ": " +output7);
        //add up taskNum
        taskNum++;

        //Solutions for 8-9
        //get shortest distance route from A to C, and print it
        System.out.println("Output #" + taskNum + ": " + Solutions.getShortestDistanceRoute(graph, 'A', 'C'));
        //add up taskNum
        taskNum++;
        //get shortest distance of round trip from B to B, and print it
        System.out.println("Output #" + taskNum + ": " + Solutions.getShortestDistanceRoute(graph, 'B', 'B'));
        //add up taskNum
        taskNum++;

        //Solution for 10
        //get number of trips from C to C where their distance is less than C
        System.out.println("Output " + taskNum + ": " + Solutions.getNumberOfTripsLessThan(graph,'C','C', 30));

        //ask to restart
        System.out.println("If you want to restart program please type: restart");
        String restart = scanner.next();
        //if user typed restart, restart application
        if (restart.equals("restart")){
            restart(args);
        }
    }

    //restart method
    private static void restart(String[] args) throws IOException {
        System.out.println("restarting");
        main(args);
    }
}
