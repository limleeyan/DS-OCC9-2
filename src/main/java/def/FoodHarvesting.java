package def;

public class FoodHarvesting {
    Graph<Integer,Integer> foodGraph = new Graph<>();

    public void insertGraph(){
        int[] vertices = {1,2,3,4,5,6,7,8,9,10};
        for(int elem : vertices)
            foodGraph.addVertex(elem);

        System.out.println("The number of vertices in graph1: "+foodGraph.getSize());

        System.out.println("Vertices: ");
        for(int i=0; i<foodGraph.getSize(); i++)
            System.out.print(i+": "+foodGraph.getVertex(i)+"\t");
        System.out.println();

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

        System.out.println("Print edges: ");
        foodGraph.printEdges();
    }


}
