package def;

import javax.swing.*;
import java.util.*;

public class FoodHarvesting {
    static Scanner sc = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);
    private static Graph<Integer> foodGraph = new Graph<>();
    static List<Integer> nodesWithoutFood = new ArrayList<>();
    private static List<List<Integer>> paths = new ArrayList<>();

    public FoodHarvesting() {
        insertGraph();

        ImageIcon graphImage = new ImageIcon("src/main/resources/GraphImage.PNG");
        JOptionPane.showMessageDialog(null,null,"Graph",JOptionPane.INFORMATION_MESSAGE,graphImage);

        while (true) {
            nodesWithoutFood.clear();
            System.out.print("\nEnter node(s) without food (0 to proceed, -1 to exit feature): ");
            String inputLine = sc.nextLine();
            String[] inputs = inputLine.split("\\s+");

            boolean validInput = true;

            for (String input : inputs) {
                try {
                    int value = Integer.parseInt(input);
                    if (value == -1 || value == 0) {
                        validInput = true;
                        break;
                    } else if (value < 0 || value > 10) {
                        System.out.println("Invalid input! Please enter a value between 0 and 10.");
                        validInput = false;
                    } else {
                        nodesWithoutFood.add(value);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid integer value.");
                    validInput = false;
                }
            }

            if (!validInput) {
                continue;
            }

            if (inputLine.contains("-1"))
                break;

            // if all nodes contain no food
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                list.add(i);
            }
            for (int i = 0; i < nodesWithoutFood.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (nodesWithoutFood.get(i) == list.get(j))
                        list.remove(j);
                }
            }
            if (list.isEmpty())
                System.out.println("All nodes contain no food");

                // if only node1 contains food
            else if (containAllNodesExcept1())
                System.out.println("1 -> 1");

                // find all possible paths/cycles using DFS
            else {
                paths.clear();
                depthFirstSearch(1, new ArrayList<>());

                // find best paths
                List<List<Integer>> bestPaths = findBestPaths();

                // print best path(s)
                printBestPaths(bestPaths);

            }
        }
    }

    public FoodHarvesting(boolean isFood){
        insertGraph();
        nodesWithoutFood.clear();

        ImageIcon graphImage = new ImageIcon("src/main/resources/GraphImage.PNG");
        JOptionPane.showMessageDialog(null,null,"Graph",JOptionPane.INFORMATION_MESSAGE,graphImage);

        boolean validInput = false;
        while (!validInput) {
            System.out.print("\nEnter node(s) without food (0 to proceed): ");
            String inputLine = sc.nextLine();
            String[] inputs = inputLine.split("\\s+");

            validInput = true;

            for (String input : inputs) {
                try {
                    int value = Integer.parseInt(input);
                    if (value < 0 || value > 10) {
                        System.out.println("Invalid input! Please enter a value between 0 and 10.");
                        validInput = false;
                        break;
                    } else if (value!=0) {
                        nodesWithoutFood.add(value);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid integer value.");
                    validInput = false;
                    break;
                }
            }
        }

        // if all nodes contain no food
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        for (int i = 0; i < nodesWithoutFood.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (nodesWithoutFood.get(i) == list.get(j))
                    list.remove(j);
            }
        }
        if (list.isEmpty())
            System.out.println("All nodes contain no food");

            // if only node1 contains food
        else if (containAllNodesExcept1())
            System.out.println("1 -> 1");

            // find all possible paths/cycles using DFS
        else {
            paths.clear();
            depthFirstSearch(1, new ArrayList<>());

            // find best paths
            List<List<Integer>> bestPaths = findBestPaths();

            // print best path(s)
            printBestPaths(bestPaths);
        }
    }

    // method to find all possible cycles using depth first search
    public static void depthFirstSearch (int start, List<Integer> currentPath){
        // if current path is empty, add starting node
        if (currentPath.isEmpty())
            currentPath.add(start);

        int lastNode = currentPath.get(currentPath.size()-1);

        // loop all neighbours of last node
        List<Integer> neighbours = foodGraph.getNeighbours(lastNode);
        for (int neighbour : neighbours){
            // if the neighbour is starting point(1), add in and consider it as a cycle
            if (neighbour==start){
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbour);
                paths.add(newPath);
            }
            // else, if the node haven't been visited, add into the path and start a new dfs using new path
            else if (!currentPath.contains(neighbour)) {
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbour);
                depthFirstSearch(start,newPath);
            }
        }
    }

    // method to find the best paths based on nodes without food inputted
    // i == 0: finding the best paths which contain all food nodes but WITHOUT ALL no food nodes
    // i++ : finding the best paths which contain all food nodes but contain some NO food nodes
    // i++ until we obtain the best paths or until i == number of NO food nodes, which mean we will return a cycle which contain ALL Nodes
    private static List<List<Integer>> findBestPaths(){
        List<List<Integer>> bestPaths = new ArrayList<>();
        for (int i=0; i<=nodesWithoutFood.size(); i++){
            for (List<Integer> path: paths){
                if (containsAllFoodNodes(path) && path.size()==foodGraph.getSize()+1-(nodesWithoutFood.size()-i))
                    bestPaths.add(path);
            }
            if (!bestPaths.isEmpty())
                return bestPaths;
        }
        return bestPaths;
    }

    // method to print out the best path(s)
    private static void printBestPaths(List<List<Integer>> bestPaths){
        System.out.println("Best path(s): ");

        for (List<Integer> path : bestPaths){
            for (int i=0; i<path.size()-1; i++)
                System.out.print(path.get(i)+" -> ");
            System.out.println(path.get(path.size()-1));
        }
    }

    // check whether the modeWithoutFood contain all nodes except 1
    private static boolean containAllNodesExcept1(){
        for (int i=2; i<=10; i++){
            if (!nodesWithoutFood.contains(i))
                return false;
        }
        return true;
    }

    // method to check whether the path contain all food nodes
    private static boolean containsAllFoodNodes(List<Integer> path){
        for (int i=0; i<foodGraph.getSize(); i++){
            if (!nodesWithoutFood.contains(foodGraph.getVertex(i))){
                if (!path.contains(foodGraph.getVertex(i)))
                    return false;
            }
        }
        return true;
    }

    public static void insertGraph(){
        foodGraph.clear();
        int[] vertices = {1,2,3,4,5,6,7,8,9,10};
        for(int elem : vertices)
            foodGraph.addVertex(elem);

        foodGraph.addUndirectedEdge(1,2);
        foodGraph.addUndirectedEdge(1,3);
        foodGraph.addUndirectedEdge(1,6);
        foodGraph.addUndirectedEdge(1,10);

        foodGraph.addUndirectedEdge(2,4);

        foodGraph.addUndirectedEdge(3,4);
        foodGraph.addDirectedEdge(3,7);

        foodGraph.addUndirectedEdge(4,5);

        foodGraph.addUndirectedEdge(5,6);
        foodGraph.addUndirectedEdge(5,7);

        foodGraph.addUndirectedEdge(6,7);
        foodGraph.addUndirectedEdge(6,8);

        foodGraph.addUndirectedEdge(7,8);
        foodGraph.addUndirectedEdge(7,9);

        foodGraph.addUndirectedEdge(8,9);
        foodGraph.addUndirectedEdge(8,10);

        foodGraph.addUndirectedEdge(9,10);
    }


}
