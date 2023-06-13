package def;

public class WeightedEdge {
    int destination;
    int distance;
    String roadType;

    public WeightedEdge(int destination, int distance, String roadType) {
        this.destination = destination;
        this.distance = distance;
        this.roadType = roadType;
    }
}
