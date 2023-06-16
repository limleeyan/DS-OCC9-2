package def;

import javax.swing.*;
import java.util.Scanner;

public class FortressAttackSimulation {
    static Scanner sc = new Scanner(System.in);
    public FortressAttackSimulation() {
        FortressAttack weightedGraph = new FortressAttack();

        ImageIcon graphImage = new ImageIcon("src/main/resources/GraphImage.PNG");
        JOptionPane.showMessageDialog(null,null,"Graph",JOptionPane.INFORMATION_MESSAGE,graphImage);

        weightedGraph.initializeGraph(11);

        weightedGraph.addUndirectedWeightedEdge(1, 2, 10, "Forest");
        weightedGraph.addUndirectedWeightedEdge(1, 3, 18, "Flat Road");
        weightedGraph.addUndirectedWeightedEdge(1, 6, 20, "Flat Road");
        weightedGraph.addUndirectedWeightedEdge(1, 10, 16, "Flat Road");

        weightedGraph.addUndirectedWeightedEdge(2, 4, 10, "Swamp");

        weightedGraph.addUndirectedWeightedEdge(3, 4, 12, "Swamp");
        weightedGraph.adDirectedWeightedEdge(3, 7, 28, "Plank Road");

        weightedGraph.addUndirectedWeightedEdge(4, 5, 12, "Swamp");

        weightedGraph.addUndirectedWeightedEdge(5, 6, 17, "Flat Road");
        weightedGraph.addUndirectedWeightedEdge(5, 7, 10, "Forest");

        weightedGraph.addUndirectedWeightedEdge(6, 7, 23, "Forest");
        weightedGraph.addUndirectedWeightedEdge(6, 8, 35, "Plank Road");

        weightedGraph.addUndirectedWeightedEdge(7, 9, 17, "Flat Road");
        weightedGraph.addUndirectedWeightedEdge(7, 8, 19, "Flat Road");

        weightedGraph.addUndirectedWeightedEdge(8, 9, 7, "Swamp");
        weightedGraph.addUndirectedWeightedEdge(8, 10, 12, "Forest");

        weightedGraph.addUndirectedWeightedEdge(9, 10, 18, "Flat Road");

        while (true) {
            System.out.println("\nFortress Attack Simulation");
            System.out.print("Enter the destination (enemy fortress) node, (Enter -1 to exit feature): ");
            int enemyFortress = sc.nextInt();

            if(enemyFortress==-1){
                break;
            }
            else if(enemyFortress<=0 || enemyFortress>10){
                System.out.println("Invalid Input!");
                continue;
            }
            weightedGraph.shortestTime(enemyFortress);
        }
    }
}
