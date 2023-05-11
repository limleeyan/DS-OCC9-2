package def;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MatrixCluster {
    public static void main(String[] args) {

        int[][] matrix = null;

        try {
            matrix = readBooleanMatrix("BattleshipMatrix.txt");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    
        boolean[][] bolMat = convertBinaryToBoolean(matrix);

        int numClusters = countClusters(bolMat);
        System.out.println("Number of clusters: " + numClusters);
    }

    public static int[][] readBooleanMatrix(String filename) throws IOException {
        // Open the file for reading
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // Determine the dimensions of the matrix
        int numRows = 0;
        int numCols = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            numRows++;
            String[] tokens = line.split("\\s+");
            numCols = tokens.length;
        }

        // Initialize the matrix
        int[][] matrix = new int[numRows][numCols];

        // Reset the scanner to the beginning of the file
        scanner.close();
        scanner = new Scanner(file);

        // Read the values into the matrix
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            for (int col = 0; col < numCols; col++) {
                matrix[row][col] = Integer.parseInt(tokens[col]);
            }
            row++;
        }

        // Close the scanner and return the matrix
        scanner.close();
        return matrix;
    }

    public static boolean[][] convertBinaryToBoolean(int[][] binaryArray) {
        int numRows = binaryArray.length;
        int numCols = binaryArray[0].length;
        boolean[][] boolArray = new boolean[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                boolArray[i][j] = (binaryArray[i][j] == 1);
            }
        }
        return boolArray;
    }  

    public static int countClusters(boolean[][] matrix) {
        int n = matrix.length;
        int count = 0;
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && matrix[i][j]) {
                    count++;
                    dfs(matrix, visited, i, j);
                }
            }
        }

        return count;
    }

    private static void dfs(boolean[][] matrix, boolean[][] visited, int i, int j) {
        int n = matrix.length;

        if (i < 0 || i >= n || j < 0 || j >= n) {
            return;
        }

        if (!visited[i][j] && matrix[i][j]) {
            visited[i][j] = true;
            dfs(matrix, visited, i-1, j-1);
            dfs(matrix, visited, i-1, j);
            dfs(matrix, visited, i-1, j+1);
            dfs(matrix, visited, i, j-1);
            dfs(matrix, visited, i, j+1);
            dfs(matrix, visited, i+1, j-1);
            dfs(matrix, visited, i+1, j);
            dfs(matrix, visited, i+1, j+1);
        }
    }
}