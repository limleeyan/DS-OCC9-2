package def;

public class TestGraph {
    public static void main(String[] args) {
//        Graph<String,Integer> graph1 = new Graph<>();
//        String[] vertices = {"1","2","3","4","5","6","7","8","9","10"};
//        for(String elem: vertices)
//            graph1.addVertex(elem);
        Graph<Integer,Integer> graph1 = new Graph<>();
        int[] vertices = {1,2,3,4,5,6,7,8,9,10};
        for(int elem : vertices)
            graph1.addVertex(elem);

        System.out.println("The number of vertices in graph1: "+graph1.getSize());

        System.out.println("Vertices: ");
        for(int i=0; i<graph1.getSize(); i++)
            System.out.print(i+": "+graph1.getVertex(i)+"\t");
        System.out.println();

        graph1.addUndirectedEdge(1,2,null);
        graph1.addUndirectedEdge(1,3,null);
        graph1.addUndirectedEdge(1,6,null);
        graph1.addUndirectedEdge(1,10,null);

        graph1.addUndirectedEdge(2,4,null);

        graph1.addUndirectedEdge(3,4,null);
        graph1.addDirectedEdge(3,7,null);

        graph1.addUndirectedEdge(4,5,null);

        graph1.addUndirectedEdge(5,6,null);
        graph1.addUndirectedEdge(5,7,null);

        graph1.addUndirectedEdge(6,7,null);
        graph1.addUndirectedEdge(6,8,null);

        graph1.addUndirectedEdge(7,8,null);
        graph1.addUndirectedEdge(7,9,null);

        graph1.addUndirectedEdge(8,9,null);
        graph1.addUndirectedEdge(8,10,null);

        graph1.addUndirectedEdge(9,10,null);

        System.out.println("Print edges: ");
        graph1.printEdges();
    }
}
