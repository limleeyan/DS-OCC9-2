package def;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodHarvesting {
    Graph<Integer,Integer> foodGraph = new Graph<>();
    List<Integer> nodesWithoutFood = new ArrayList<>();

    public void insertGraph(){
        int[] vertices = {1,2,3,4,5,6,7,8,9,10};
        for(int elem : vertices)
            foodGraph.addVertex(elem);

        foodGraph.addUndirectedEdge(1,2,null);
        foodGraph.addUndirectedEdge(1,3,null);
        foodGraph.addUndirectedEdge(1,6,null);
        foodGraph.addUndirectedEdge(1,10,null);

        foodGraph.addUndirectedEdge(2,4,null);

        foodGraph.addUndirectedEdge(3,4,null);
        foodGraph.addDirectedEdge(3,7,null);

        foodGraph.addUndirectedEdge(4,5,null);

        foodGraph.addUndirectedEdge(5,6,null);
        foodGraph.addUndirectedEdge(5,7,null);

        foodGraph.addUndirectedEdge(6,7,null);
        foodGraph.addUndirectedEdge(6,8,null);

        foodGraph.addUndirectedEdge(7,8,null);
        foodGraph.addUndirectedEdge(7,9,null);

        foodGraph.addUndirectedEdge(8,9,null);
        foodGraph.addUndirectedEdge(8,10,null);

        foodGraph.addUndirectedEdge(9,10,null);
    }

    public List<Integer> findHamiltonianCycle() {
        List<Integer> path = new ArrayList<>();
        int numVertices = foodGraph.getSize();
        boolean[] visited = new boolean[numVertices];

        // Start the path from node 1 (index 0)
        path.add(1);
        visited[0] = true;

        if (findHamiltonianCycleUtil(0, visited, path)) {
            // Add the starting node at the end to complete the cycle
            path.add(1);
            return path;
        }

        // No Hamiltonian cycle found, try excluding nodes without food
        List<Integer> nodesWithoutFood = getNodesWithoutFood();
        for (int node : nodesWithoutFood) {
            // Reset visited array
            visited = new boolean[numVertices];

            // Start the path from node 1 (index 0)
            path.clear();
            path.add(1);
            visited[0] = true;

            // Exclude the node without food from the graph
            foodGraph.removeVertex(node);

            if (findHamiltonianCycleUtil(0, visited, path)) {
                // Add the starting node at the end to complete the cycle
                path.add(1);
                return path;
            }

            // Restore the excluded node in the graph
            foodGraph.addVertex(node);
        }

        // No Hamiltonian cycle found
        return null;
    }

    private List<Integer> getNodesWithoutFood() {
        // Implement the logic to retrieve the nodes without food
        // and return them as a list
        // Add the nodes without food to the list
        // Example: nodesWithoutFood.add(9);
        return nodesWithoutFood;
    }

    public void addNodesWithoutFood(Integer node){
        nodesWithoutFood.add(node);
    }

    private boolean findHamiltonianCycleUtil(int currentVertex, boolean[] visited, List<Integer> path) {
        // Base case: All vertices are visited
        if (path.size() == foodGraph.getSize()) {
            // Check if the last vertex is connected to the starting vertex
            int lastVertex = path.get(path.size() - 1);
            int startingVertex = path.get(0);
            if (foodGraph.hasEdge(lastVertex, startingVertex)) {
                return true;
            }
            return false;
        }

        // Recursively visit the neighboring vertices
        List<Integer> neighbors = foodGraph.getNeighbours(path.get(path.size() - 1));
        for (int neighbor : neighbors) {
            if (!visited[neighbor - 1]) {  // Subtract 1 to convert to zero-based indexing
                visited[neighbor - 1] = true;
                path.add(neighbor);

                // Recursive call
                if (findHamiltonianCycleUtil(neighbor - 1, visited, path)) {
                    return true;
                }

                // Backtrack
                visited[neighbor - 1] = false;
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FoodHarvesting foodHarvesting = new FoodHarvesting();
        foodHarvesting.insertGraph();
        List<Integer> cycle = foodHarvesting.findHamiltonianCycle();

//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter node without food: ");
//        int temp;
//        while (true) {
//            temp = sc.nextInt();
//            if (temp == -1) {
//                break; // User finished entering nodes
//            }
//            nodesWithoutFood.add(temp);
//        }
//        sc.close();

        if (cycle != null) {
            System.out.println("Hamiltonian Cycle: "+ cycle);
        } else {
            System.out.println("No Hamiltonian Cycle found.");
        }
    }
}
