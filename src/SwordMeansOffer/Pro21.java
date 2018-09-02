package SwordMeansOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Special on 2018/6/29 22:26
 */
public class Pro21 {

    /**
     * 1.确定圈数
     * 2.打印每一圈
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        if(matrix == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        int countOfCircle = (Math.min(row, col) + 1) / 2;
        for(int i = 0; i < countOfCircle; i++) {
            int endX = row - i - 1;
            int endY = col - i - 1;
            //打印上边
            for(int j = i; j <= endY; j++) {
                result.add(matrix[i][j]);
            }
            //打印右边
            for(int j = i + 1; j <= endX; j++) {
                result.add(matrix[j][endY]);
            }
            //打印下边
            if(endX > i) {
                for (int j = endY - 1; j >= i; j--) {
                    result.add(matrix[endX][j]);
                }
            }
            //打印左边
            if(endY > i) {
                for (int j = endX - 1; j > i; j--) {
                    result.add(matrix[j][i]);
                }
            }
        }
        return result;
    }

    /**
     * 魔方旋转打印
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix2(int [][] matrix) {
        if(matrix == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        int row = matrix.length;
        while(row != 0) {
            for(int i = 0; i < matrix[0].length; i++) {
                result.add(matrix[0][i]);
            }
            //当row == 1时，说明已是最后一个元素
            if(row == 1) {
                break;
            }
            matrix = anticlockwiseReverse(matrix);
            row = matrix.length;
        }
        return result;
    }

    /**
     * 逆时针旋转矩阵
     * @param matrix
     * @return
     */
    public static int[][] anticlockwiseReverse(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        //删除已打印的第一行
        int[][] newMatrix = new int[col][row - 1];
        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row - 1; j++) {
               newMatrix[i][j] = matrix[j + 1][col - 1 - i];
            }
        }
        return newMatrix;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int m = input.nextInt();
            int[][] matrix = new int[n][m];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    matrix[i][j] = input.nextInt();
                }
            }
            List<Integer> result = printMatrix2(matrix);
            for(int item : result) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
