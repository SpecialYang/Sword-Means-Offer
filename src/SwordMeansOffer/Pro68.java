package SwordMeansOffer;

/**
 * Created by SpecialYang on 2018/11/18 09:26.
 */
public class Pro68 {

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        boolean[][] visited = new boolean[rows][cols];
        return dfs(threshold, rows, cols, 0, 0, visited);
    }

    public int dfs(int threshold, int rows, int cols, int row, int col,
                   boolean[][] visited) {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || visited[row][col] || !isValid(row, col, threshold)) {
            return 0;
        }
        int count = 1;
        //标记已访问过的节点
        visited[row][col] = true;
        //因为是从左上到右下，所以只需探索右，下方向即可
        count += dfs(threshold, rows, cols, row + 1, col, visited);
        count += dfs(threshold, rows, cols, row , col + 1, visited);
        //note: 此处不用恢复现场，因为这里的节点访问不要求顺序性，所以要避免重复访问。
        return count;
    }

    /**
     * 判断当前节点是否满足约束
     * @param row
     * @param col
     * @param threshold
     * @return
     */
    private boolean isValid(int row, int col, int threshold) {
        int result = countOfEachDigit(row);
        result += countOfEachDigit(col);
        return result <= threshold;
    }

    /**
     * 求一个数各位的之和
     * @param num
     * @return
     */
    private int countOfEachDigit(int num) {
        int result = 0;
        while (num != 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }

}
