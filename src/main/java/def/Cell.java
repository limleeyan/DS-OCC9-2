package def;

public class Cell {
    int row;
    int col;
    int cost;
    int heuristic;
    Cell parent;

    public Cell(int row, int col, int cost, int heuristic) {
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode() {
        return row * 31 + col;
    }
}
