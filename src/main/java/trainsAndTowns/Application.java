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
        int taskNum = 1;
        List<String> routes = new ArrayList<>(List.of("ABC", "AD", "ADC", "AEBCD", "AED"));

        for (String route : routes) {
            int result = Solutions.getDistance(route, graph);
            System.out.println("Output #" + taskNum + ": " + (result == 0 ? "NO SUCH ROUTE" : result));
            taskNum++;
        }

        //Solutions for 6-7
        int output6 = 0;
        List<List<Character>> allRoutesFromCToC=Solutions.getNumberOfTrips(graph, 'C', 'C');
        for (List<Character> list : allRoutesFromCToC){
            if (list.size() <= 4){
                output6++;
            }
        }
        System.out.println("Output #" + taskNum + ": " + output6);
        taskNum++;

        int output7 = 0;
        List<List<Character>> allRoutesFromAToC = Solutions.getNumberOfTrips(graph,'A', 'C');
        for (List<Character> list : allRoutesFromAToC){
            if (list.size() == 5){
                output7++;
            }
        }
        System.out.println("Output #" + taskNum + ": " +output7);
        taskNum++;

        //Solutions for 8-9
        System.out.println("Output #" + taskNum + ": " + Solutions.getShortestDistanceRoute(graph, 'A', 'C'));
        taskNum++;
        System.out.println("Output #" + taskNum + ": " + Solutions.getShortestDistanceRoute(graph, 'B', 'B'));
        taskNum++;

        //Solution for 10
        System.out.println("Output " + taskNum + ": " + Solutions.getNumberOfTripsLessThan(graph,'C','C', 30));

        //ask to restart
        System.out.println("If you want to restart program please type: restart");
        String restart = scanner.next();
        if (restart.equals("restart")){
            restart(args);
        }
    }

    private static void restart(String[] args) throws IOException {
        System.out.println("restarting");
        main(args);
    }
}
