package def;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Cell {
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

class FindPath {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int calculateHeuristic(int row, int col, int exitRow, int exitCol) {
        return Math.abs(row - exitRow) + Math.abs(col - exitCol);
    }

    private static boolean isValid(int[][] matrix, int row, int col) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        return row >= 0 && row < numRows && col >= 0 && col < numCols && matrix[row][col] != 0;
    }

    public static List<Cell> findPath(int[][] matrix, int entryRow, int entryCol, int exitRow, int exitCol) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        boolean[][] visited = new boolean[numRows][numCols];

        PriorityQueue<Cell> openSet = new PriorityQueue<>((a, b) -> (a.cost + a.heuristic) - (b.cost + b.heuristic));
        openSet.offer(new Cell(entryRow, entryCol, 0, calculateHeuristic(entryRow, entryCol, exitRow, exitCol)));

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();
            int row = current.row;
            int col = current.col;

            if (matrix[row][col] == 3) {
                // Exit reached, backtrack the path
                List<Cell> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current);
                    current = current.parent;
                }
                return path;
            }

            visited[row][col] = true;

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValid(matrix, newRow, newCol) && !visited[newRow][newCol]) {
                    int newCost = current.cost + 1;
                    int newHeuristic = calculateHeuristic(newRow, newCol, exitRow, exitCol);
                    Cell neighbor = new Cell(newRow, newCol, newCost, newHeuristic);
                    neighbor.parent = current;

                    if (!openSet.contains(neighbor)) {
                        openSet.offer(neighbor);
                    } else {
                        // Update cost and heuristic if new values are lower
                        for (Cell cell : openSet) {
                            if (cell.equals(neighbor) && newCost < cell.cost) {
                                cell.cost = newCost;
                                cell.heuristic = newHeuristic;
                                cell.parent = current;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return null; // No path found
    }
}

public class MazePath2 {
    public static void main(String[] args) { 

        int[][] matrix = ReadMatrixFromFile.matrixFile2IntArray("src\\main\\java\\def\\CaoCaoMaze.txt");

        int entryRow = 1;
        int entryCol = 0;
        int exitRow = 9;
        int exitCol = 12;

        List<Cell> path = FindPath.findPath(matrix, entryRow, entryCol, exitRow, exitCol);

        if (path != null) {
            System.out.println("Path found:");
            for (Cell cell : path) {
                System.out.println("(" + cell.row + ", " + cell.col + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
