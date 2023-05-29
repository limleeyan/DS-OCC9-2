package def;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ReadMatrixFromFile {

    public static char[][] matrixFile2CharArray(String fileName) {
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
    public static int[][] matrixFile2IntArray(String fileName) {
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
    
        int[][] grid = new int[numRows][numCols];
    
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
                grid[i][j] = Integer.parseInt(values[j]);
            }
            i++;
        }
    
        return grid;
    }
    
}