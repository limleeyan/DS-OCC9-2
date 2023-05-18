package def;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class MatrixCluster {
    public static void main(String args[]) {
        
        MatrixCluster2 cluster = new MatrixCluster2();
        char[][] grid = ReadMatrixFromFile.readMatrixFromFile("BattleShipMatrix.txt");

        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[i].length; j++) {
        //         System.out.print(grid[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        //print the matrix to check 


        int result = cluster.numIslands(grid);
        System.out.println("Number of cluster: " + result);

    }
}

class MatrixCluster2{
    public int numIslands (char[][] grid){
        int totalIslands = 0;
        int rowLen = grid.length;
        int colLen = grid[0].length;

        boolean[][] visited = new boolean[rowLen][colLen];
        
        int[] rowList = {1, 0, 0, -1};
        int[] colList = {0, 1, -1, 0};

        for (int i = 0; i < rowLen; i++){
            for (int j = 0; j < colLen; j++){
                if (grid[i][j] == '1' && !visited[i][j]){
                    totalIslands++;
                    dfs(i, j, grid, rowLen, colLen, rowList, colList, visited);
                }
            }
        }
        return totalIslands;
    }
    private void dfs(int row, int col, char[][] grid, int rowLen, int colLen, int[]rowList, int[] colList, boolean[][] visited){
        visited [row][col] = true;

        for (int i = 0; i < 4; i++){
            int deltaRow = rowList[i] + row;
            int deltaCol = colList[i] + col;

            if (deltaRow < 0 || deltaCol < 0 || deltaRow >= rowLen || deltaCol >= colLen){
                continue;
            }
            if (deltaRow == row && deltaCol == col){
                continue;
            }
            if (grid[deltaRow][deltaCol] == '1' && !visited[deltaRow][deltaCol]){
                dfs(deltaRow, deltaCol, grid, rowLen, colLen, rowList, colList, visited);
            }
        }
    }
}


class ReadMatrixFromFile {

    public static char[][] readMatrixFromFile(String fileName) {
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int numRows = 0;
        int numCols = 0;

        // get the number of rows and columns in the matrix
        while (scanner.hasNextLine()) {
            numRows++;
            String line = scanner.nextLine();
            numCols = line.split(" ").length;
        }

        char[][] grid = new char[numRows][numCols];

        // reset scanner to the beginning of the file
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // read the values from the file into the grid
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(" ");
            for (int j = 0; j < values.length; j++) {
                grid[i][j] = values[j].charAt(0);
            }
            i++;
        }

        return grid;
    }
}
