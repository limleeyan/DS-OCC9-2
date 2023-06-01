package def;

public class MatrixCluster {
    public static void main(String args[]) {
        
        MatrixCluster2 cluster = new MatrixCluster2();
        char[][] grid = ReadMatrixFromFile.matrixFile2CharArray("src\\main\\java\\def\\BattleshipMatrix.txt");

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

