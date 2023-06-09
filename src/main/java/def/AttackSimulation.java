package def;

public class AttackSimulation {

    public AttackSimulation(){
        WeightedGraph<Integer, Integer, String> weightedGraph1 = createGraph();

    }

    private static WeightedGraph<Integer, Integer, String> createGraph() {
        WeightedGraph<Integer, Integer, String> weightedGraph = new WeightedGraph<>();

        int[] vertices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int elem : vertices) {
            weightedGraph.addVertex(elem);
        }

        weightedGraph.addUndirectedEdge(1, 2, 10, "Forest");
        weightedGraph.addUndirectedEdge(1, 3, 18, "Flat Road");
        weightedGraph.addUndirectedEdge(1, 6, 20, "Flat Road");
        weightedGraph.addUndirectedEdge(1, 10, 16, "Flat Road");

        weightedGraph.addUndirectedEdge(2, 4, 10, "Swamp");

        weightedGraph.addUndirectedEdge(3, 4, 12, "Swamp");
        weightedGraph.addDirectedEdge(3, 7, 28, "Plank Road");

        weightedGraph.addUndirectedEdge(4, 5, 12, "Swamp");

        weightedGraph.addUndirectedEdge(5, 6, 17, "Flat Road");
        weightedGraph.addUndirectedEdge(5, 7, 10, "Forest");

        weightedGraph.addUndirectedEdge(6, 7, 23, "Forest");
        weightedGraph.addUndirectedEdge(6, 8, 35, "Plank Road");

        weightedGraph.addUndirectedEdge(7, 9, 17, "Flat Road");
        weightedGraph.addUndirectedEdge(7, 8, 19, "Flat Road");

        weightedGraph.addUndirectedEdge(8, 9, 7, "Swamp");
        weightedGraph.addUndirectedEdge(8, 10, 12, "Forest");

        weightedGraph.addUndirectedEdge(9, 10, 18, "Flat Road");

        return weightedGraph;

    }
}
