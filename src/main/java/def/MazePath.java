package def;
import java.util.*;

class Maze {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] maze;

    public Maze(int[][] maze) {
        this.maze = maze;
    }

    public List<Cell> findShortestPath(int entryRow, int entryCol, int exitRow, int exitCol) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        int[][] distances = new int[numRows][numCols];
        boolean[][] visited = new boolean[numRows][numCols];


        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.distance));
        queue.offer(new Cell(entryRow, entryCol, 0));

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int row = current.row;
            int col = current.col;

            if (row == exitRow && col == exitCol) {
                // Exit reached, backtrack the path
                List<Cell> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current);
                    current = distances[current.row][current.col] == 0 ? null : getNearestNeighs(distances, current);
                }
                return path;
            }

            visited[row][col] = true;

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
                    int newDistance = current.distance + maze[newRow][newCol];
                    if (newDistance < distances[newRow][newCol] || distances[newRow][newCol] == 0) {
                        distances[newRow][newCol] = newDistance;
                        queue.offer(new Cell(newRow, newCol, newDistance));
                    }
                }
            }
            // // Print distances array
            // System.out.println("Distances array:");
            // for (int i = 0; i < numRows; i++) {
            //     for (int j = 0; j < numCols; j++) {
            //         System.out.print(distances[i][j] + " ");
            //     }
            //     System.out.println();
            // }
        }

        return null; // No path found
    }

    private boolean isValid(int row, int col) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        return row >= 0 && row < numRows && col >= 0 && col < numCols && maze[row][col] != 0;
    }

    private Cell getNearestNeighs(int[][] distances, Cell cell) {
        int row = cell.row;
        int col = cell.col;
        int minDist = Integer.MAX_VALUE;
        Cell minNeig = null;

        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isValid(newRow, newCol) && distances[newRow][newCol] < minDist) {
                minDist = distances[newRow][newCol];
                //System.out.println(minDist);
                minNeig = new Cell(newRow, newCol, minDist);
            }
        }

        return minNeig;
    }
}

public class MazePath {
    public MazePath() {
        int[][] maze = ReadMatrixFromFile.matrixFile2IntArray("src\\main\\java\\def\\CaoCaoMaze.txt");
        
        int entryRow = -1;
        int entryCol = -1;
        int exitRow = -1;
        int exitCol = -1;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 2) {
                    entryRow = i;
                    entryCol = j;
                } else if (maze[i][j] == 3) {
                    exitRow = i;
                    exitCol = j;
                }
            }
        }

        Maze mazeSolver = new Maze(maze);
        List<Cell> shortestPath = mazeSolver.findShortestPath(entryRow, entryCol, exitRow, exitCol);

        System.out.println();
        if (shortestPath != null) {
            System.out.println("Shortest path found:");
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    boolean isPathCell = false;
                    for (Cell cell : shortestPath) {
                        if (cell.row == i && cell.col == j) {
                            isPathCell = true;
                            break;
                        }
                    }
                    if (isPathCell) {
                        System.out.print("* ");
                    } else {
                        System.out.print(maze[i][j] + " ");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("No path found.");
        }

    }
    public static void main(String[] args) {
        new MazePath();
    }
}
