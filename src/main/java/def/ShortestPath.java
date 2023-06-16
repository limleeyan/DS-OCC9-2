package def;
import java.util.*;
public class ShortestPath {
    static Scanner sc = new Scanner(System.in);
    private  static List<List<Integer>> paths;
    private static int destination;

    public ShortestPath() {
        Graph<Integer> graph1 = createGraph();
        System.out.println("\nThe number of vertices in graph: " + graph1.getSize());
        System.out.println();
//
//        System.out.println("Vertices: ");
//        for (int i = 0; i < graph1.getSize(); i++) {
//            System.out.print(i + "- " + graph1.getVertex(i) + "\t");
//        }
//        System.out.println();

        while (true) {
            System.out.print("Enter the base camp for the enemy base camp (Press -1 exit to exit feature): ");
            destination = sc.nextInt();
            if (destination == -1) {
                break;
            } else if (destination <= 0 || destination >= graph1.getSize()) {
                System.out.println("Invalid input!");
                continue;
            }
            //Find all possible paths using BFS
            paths = new ArrayList<>();
            breadthFirstSearch(graph1, 1, new ArrayList<>());

            //Print the best path(s)
            printBestPaths();
        }
    }

    public static void breadthFirstSearch(Graph<Integer> graph, int start, List<Integer> currentPath){
        Queue<List<Integer>> queue = new LinkedList<>();
        currentPath.add(start);
        queue.offer(currentPath);

        while(!queue.isEmpty()){
            List<Integer> path = queue.poll();
            int lastNode = path.get(path.size()-1);
            if(lastNode == destination){
                paths.add(path);
            }
            else{
                List<Integer> neibours = graph.getNeighbours(lastNode);
                for(int neibour : neibours){
                    if(!path.contains(neibour)){  //if not yet visit
                        List<Integer> newPath = new ArrayList<>(path);
                        newPath.add(neibour);
                        queue.offer(newPath);
                    }
                }
            }
        }
    }

    private static void printBestPaths(){
        System.out.println("Best path(s): ");
        int min = paths.get(0).size();
        for(List<Integer> path: paths) {
            if(path.size()<min){
                min = path.size(); //get min size
            }
        }
        for(List<Integer> path: paths){
            if(path.size()==min){
                for (int i = 0; i < path.size() - 1; i++) {

                    System.out.print(path.get(i) + "->");
                }
                System.out.println(path.get(path.size() - 1));
            }
        }
    }

    private static Graph<Integer> createGraph(){
        Graph<Integer> graph = new Graph<>();
        int[] vertices = {1,2,3,4,5,6,7,8,9,10};
        for(int elem : vertices){
            graph.addVertex(elem);
        }

        graph.addUndirectedEdge(1,2);
        graph.addUndirectedEdge(1,3);
        graph.addUndirectedEdge(1,6);
        graph.addUndirectedEdge(1,10);
        graph.addUndirectedEdge(2,4);
        graph.addUndirectedEdge(3,4);

        graph.addDirectedEdge(3,7);

        graph.addUndirectedEdge(4,5);
        graph.addUndirectedEdge(5,6);
        graph.addUndirectedEdge(5,7);
        graph.addUndirectedEdge(6,7);
        graph.addUndirectedEdge(6,8);
        graph.addUndirectedEdge(7,9);
        graph.addUndirectedEdge(7,8);
        graph.addUndirectedEdge(8,9);
        graph.addUndirectedEdge(8,10);
        graph.addUndirectedEdge(9,10);

        return graph;
    }
}