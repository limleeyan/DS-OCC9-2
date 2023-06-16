package def;

import java.util.*;

public class FortressAttack {
    private int V;
    private List<List<WeightedEdge>> adj;

    public FortressAttack() {

    }

    public void initializeGraph(int vertices) {
        V = vertices;
        adj = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; ++i)
            adj.add(new ArrayList<>());
    }

    public void addUndirectedWeightedEdge(int source, int destination, int distance, String roadType) {
        adj.get(source).add(new WeightedEdge(destination, distance, roadType));
        adj.get(destination).add(new WeightedEdge(source, distance, roadType));
    }

    public void adDirectedWeightedEdge(int source, int destination, int distance, String roadType) {
        adj.get(source).add(new WeightedEdge(destination, distance, roadType));
    }

    public void shortestTime(int fortress) {
        String[] generalNames = {"Cavalry", "Archer", "Infantry"};
        double[][] time = new double[V][3];
        int[][] prev = new int[V][3];
        for (int i = 0; i < V; i++) {
            Arrays.fill(time[i], Double.MAX_VALUE);
            Arrays.fill(prev[i], -1);
        }

        for (int general = 0; general < 3; general++) {
            PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingDouble(node -> node.time));
            time[1][general] = 0;
            pq.offer(new Node(1, 0, general));

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int u = node.vertex;
                int currentGeneral = node.general;

                for (WeightedEdge edge : adj.get(u)) {
                    int v = edge.destination;
                    int distance = edge.distance;
                    String roadType = edge.roadType;
                    double speed = getGeneralSpeed(roadType, currentGeneral);

                    double travelTime = distance / speed;
                    if (time[u][currentGeneral] + travelTime < time[v][currentGeneral]) {
                        time[v][currentGeneral] = time[u][currentGeneral] + travelTime;
                        prev[v][currentGeneral] = u;
                        pq.offer(new Node(v, time[v][currentGeneral], currentGeneral));
                    }
                }
            }
        }

        for (int general = 0; general < 3; general++) {
            System.out.printf("Shortest time for %s to reach the enemy fortress from Node 1: %.2f hours\n", generalNames[general], time[fortress][general]);
            System.out.print("Shortest path: ");
            printPath(prev, fortress, general);
            System.out.println();
        }
    }


    private double getGeneralSpeed(String roadType, int general) {
        switch (roadType) {
            case "Flat Road" -> {
                if (general == 0) { // Cavalry
                    return 2.0 * 3;
                } else if (general == 1) { // Archer
                    return 1.0 * 2;
                } else { // Infantry
                    return 1.0 * 2;
                }
            }
            case "Forest" -> {
                if (general == 0) { // Cavalry
                    return 2.0 * 0.8;
                } else if (general == 1) { // Archer
                    return 1.0 * 1;
                } else { // Infantry
                    return 1.0 * 2.5;
                }
            }
            case "Swamp" -> {
                if (general == 0) { // Cavalry
                    return 2.0 * 0.3;
                } else if (general == 1) { // Archer
                    return 1.0 * 2.5;
                } else { // Infantry
                    return 1.0 * 1;
                }
            }
            case "Plank Road" -> {
                if (general == 0) { // Cavalry
                    return 2.0 * 0.5;
                } else if (general == 1) { // Archer
                    return 1.0 * 0.5;
                } else { // Infantry
                    return 1.0 * 0.5;
                }
            }
            default -> {return 1.0;} // Default speed if terrain is not recognized
        }
    }

    private void printPath(int[][] prev, int fortress, int general) {
        List<Integer> path = new ArrayList<>();
        int current = fortress;
        while (current != -1) {
            path.add(current);
            current = prev[current][general];
        }
        Collections.reverse(path);

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i != path.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }

}



