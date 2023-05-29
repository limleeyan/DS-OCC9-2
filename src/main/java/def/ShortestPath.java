package def;
import java.util.*;


public class ShortestPath {
    private  static List<List<Integer>> paths;
    private static int destination;

    public static void main(String[] args) {
        Graph<Integer,Integer> graph1 = createGraph();
        System.out.println("The number of vertices in graph: " + graph1.getSize());

        System.out.println("Vertices: ");
        for(int i=0; i< graph1.getSize();i++){
            System.out.print(i+ ": "+ graph1.getVertex(i)+ "\t");
        }
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the base camp for the enemy base camp: ");
        destination = sc.nextInt();

        //Find all possible paths using BFS
        paths = new ArrayList<>();
//        visited = new boolean[graph1.getSize()];
        breadthFirstSearch(graph1,1,new ArrayList<>());

        //Print the best path(s)
        printBestPaths();
    }

    public static void breadthFirstSearch(Graph<Integer,Integer> graph, int start, List<Integer> currentPath){
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
                    if(!path.contains(neibour)){ //if not yet visit
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


    private static Graph<Integer, Integer> createGraph(){
        Graph<Integer, Integer> graph = new Graph<>();
        int[] vertices = {1,2,3,4,5,6,7,8,9,10};
        for(int elem : vertices){
            graph.addVertex(elem);
        }

        graph.addUndirectedEdge(1,2,null);
        graph.addUndirectedEdge(1,3,null);
        graph.addUndirectedEdge(1,6,null);
        graph.addUndirectedEdge(1,10,null);

        graph.addUndirectedEdge(2,4,null);

        graph.addUndirectedEdge(3,4,null);
        graph.addDirectedEdge(3,7,null);

        graph.addUndirectedEdge(4,5,null);

        graph.addUndirectedEdge(5,6,null);
        graph.addUndirectedEdge(5,7,null);

        graph.addUndirectedEdge(6,7,null);
        graph.addUndirectedEdge(6,8,null);

        graph.addUndirectedEdge(7,9,null);
        graph.addUndirectedEdge(7,8,null);

        graph.addUndirectedEdge(8,9,null);
        graph.addUndirectedEdge(8,10,null);

        graph.addUndirectedEdge(9,10,null);

        return graph;
    }
}
