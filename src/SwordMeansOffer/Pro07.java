package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/13 20:12
 */
public class Pro07 {

    /**
     * 循环形式
     * @param n
     * @return
     */
    public static int Fibonacci1(int n) {
        int first = 0;
        int second = 1;
        if(n == 0) {
            return first;
        }
        if(n == 1) {
            return second;
        }
        int result = 0;
        for(int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    /**
     * 普通递归形式
     * @param n
     * @return
     */
    public static int Fibonacci2(int n) {
        if(n <= 1) {
            return n;
        }
        return Fibonacci2(n - 1) + Fibonacci2(n - 2);
    }

    static int[] fibo = new int[45];

    /**
     * 记忆化搜索
     * @param n
     * @return
     */
    public static int Fibonacci3(int n) {
        if(n <= 1) {
            return n;
        }
        if(fibo[n] != 0) {
            return fibo[n];
        }
        fibo[n] = Fibonacci3(n - 1) + Fibonacci3(n - 2);
        return fibo[n];
    }

    /**
     * 基于矩阵快速幂的解法
     * 新颖，快速，牛逼
     * @param n
     * @return
     */
    public static int Fibonacci4(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = fastPowMatrix(base, n - 2);
        return res[0][0] + res[1][0];
    }

    /**
     * 矩阵快速幂
     * @param base
     * @param index
     * @return
     */
    public static int[][] fastPowMatrix(int[][] base, int index) {
        int[][] res = new int[base.length][base[0].length];
        for(int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = base;
        while(index != 0) {
            if((index & 1) == 1) {
                res = multipleMatix(res, t);
            }
            t = multipleMatix(t, t);
            index >>= 1;
        }
        return res;
    }

    /**
     * 矩阵相乘
     * @param left
     * @param right
     * @return
     */
    public static int[][] multipleMatix(int[][] left, int[][] right) {
        int[][] res = new int[left.length][left[0].length];
        for(int i = 0; i < left.length; i++) {
            for(int j = 0; j < right.length; j++) {
                for(int k = 0; k < left[0].length; k++) {
                    res[i][j] += left[i][k] * right[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            System.out.println(Fibonacci1(n));
            System.out.println(Fibonacci2(n));
            System.out.println(Fibonacci3(n));
            System.out.println(Fibonacci4(n));
        }
    }
}
