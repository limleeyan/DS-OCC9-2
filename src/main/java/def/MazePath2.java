package def;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class FindPath {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static boolean isValid(int[][] maze, int row, int col) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        return row >= 0 && row < numRows && col >= 0 && col < numCols && maze[row][col] != 0;
    }

    public static List<Cell> findPath(int[][] maze, int entryRow, int entryCol, int exitRow, int exitCol) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        boolean[][] visited = new boolean[numRows][numCols];

        PriorityQueue<Cell> openSet = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        openSet.offer(new Cell(entryRow, entryCol, 0));

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();
            int row = current.row;
            int col = current.col;

            if (maze[row][col] == 3) {
                // Exit reached, backtrack the path
                List<Cell> path = new ArrayList<>();
                while (current != null) {
                    current.isPath = true;
                    path.add(0, current);
                    current = current.parent;
                }
                return path;
            }

            visited[row][col] = true;

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValid(maze, newRow, newCol) && !visited[newRow][newCol]) {
                    int newCost = current.cost + 1;
                    Cell neighbor = new Cell(newRow, newCol, newCost);
                    neighbor.parent = current;

                    if (!openSet.contains(neighbor)) {
                        openSet.offer(neighbor);
                    } else {
                        // Update cost if new value is lower
                        for (Cell cell : openSet) {
                            if (cell.equals(neighbor) && newCost < cell.cost) {
                                cell.cost = newCost;
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
    public MazePath2() {
        int[][] maze = ReadMatrixFromFile.matrixFile2IntArray("C:\\Users\\eefei\\Desktop\\SEM2 DS Assignment\\DS-OCC9-2-1\\src\\main\\java\\def\\CaoCaoMaze.txt");
        
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

        List<Cell> path = FindPath.findPath(maze, entryRow, entryCol, exitRow, exitCol);

        if (path != null) {
            System.out.println("Path found:");
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (path.contains(new Cell(i, j, 0))) {
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
        new MazePath2();
    }
}
