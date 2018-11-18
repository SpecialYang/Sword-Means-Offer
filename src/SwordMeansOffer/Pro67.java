package SwordMeansOffer;

/**
 * Created by SpecialYang on 2018/11/15 21:53.
 */
public class Pro67 {

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || str == null
                || rows * cols < str.length) {
            return false;
        }
        boolean[][] flag = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, i, j, rows, cols, 0, str, flag)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[] matrix, int row, int col, int rows, int cols,
                       int index, char[] str, boolean[][] flag) {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || matrix[row * cols + col] != str[index] || flag[row][col]) {
            return false;
        }
        if (index == str.length - 1) {
            return true;
        }
        flag[row][col] = true;
        //上下左右试探，若有一个方向是正确的，那么之后都不递归了
        if (dfs(matrix, row + 1, col, rows, cols, index + 1, str, flag)
                || dfs(matrix, row - 1, col, rows, cols, index + 1, str, flag)
                || dfs(matrix, row, col + 1, rows, cols, index + 1, str, flag)
                || dfs(matrix, row, col - 1, rows, cols, index + 1, str, flag)) {
            return true;
        }
        flag[row][col] = false;
        return false;
    }
}
