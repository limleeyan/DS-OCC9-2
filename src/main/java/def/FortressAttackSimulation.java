package def;

import java.util.Scanner;

public class FortressAttackSimulation {


    public FortressAttackSimulation() {

    }

    public static void main(String[] args) {
        FortressAttack weightedGraph = new FortressAttack();

        weightedGraph.initializeGraph(11);

        weightedGraph.addWeightedEdge(1, 2, 10, "Forest");
        weightedGraph.addWeightedEdge(1, 3, 18, "Flat Road");
        weightedGraph.addWeightedEdge(1, 6, 20, "Flat Road");
        weightedGraph.addWeightedEdge(1, 10, 16, "Flat Road");

        weightedGraph.addWeightedEdge(2, 4, 10, "Swamp");

        weightedGraph.addWeightedEdge(3, 4, 12, "Swamp");
        weightedGraph.addWeightedEdge(3, 7, 28, "Plank Road");

        weightedGraph.addWeightedEdge(4, 5, 12, "Swamp");

        weightedGraph.addWeightedEdge(5, 6, 17, "Flat Road");
        weightedGraph.addWeightedEdge(5, 7, 10, "Forest");

        weightedGraph.addWeightedEdge(6, 7, 23, "Forest");
        weightedGraph.addWeightedEdge(6, 8, 35, "Plank Road");

        weightedGraph.addWeightedEdge(7, 9, 17, "Flat Road");
        weightedGraph.addWeightedEdge(7, 8, 19, "Flat Road");

        weightedGraph.addWeightedEdge(8, 9, 7, "Swamp");
        weightedGraph.addWeightedEdge(8, 10, 12, "Forest");

        weightedGraph.addWeightedEdge(9, 10, 18, "Flat Road");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the destination (enemy fortress) node: ");
        int enemyFortress = scanner.nextInt();
        scanner.close();

        weightedGraph.shortestTime(enemyFortress);
    }

}
