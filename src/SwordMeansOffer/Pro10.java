package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/14 17:47
 */
public class Pro10 {

    /**
     * 普通递归
     * @param target
     * @return
     */
    public static int RectCover1(int target) {
        if(target == 0 || target == 1) { //坑
            return target;
        }
        if(target == 2) {
            return 2;
        }
        return RectCover1(target - 1) + RectCover1(target - 2);
    }

    static int[] steps = new int[45];

    /**
     * 记忆化搜索
     * @param target
     * @return
     */
    public static int RectCover2(int target) {
        if(target == 0 || target == 1) { //坑
            return target;
        }
        if(target == 2) {
            return 2;
        }
        if(steps[target] != 0) {
            return steps[target];
        }
        steps[target] = RectCover2(target - 1) + RectCover2(target - 2);
        return steps[target];
    }

    public static int RectCover3(int target) {
        if(target == 0 || target == 1) {
            return target;
        }
        int first = 1;
        int second = 1;
        int result = 0;
        for(int i = 2; i <= target; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    /**
     * 矩阵快速幂做法
     * @param n
     * @return
     */
    public static int RectCover4(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = fastPowMatrix(base, n - 1);
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
            System.out.println(RectCover1(n));
            System.out.println(RectCover2(n));
            System.out.println(RectCover3(n));
            System.out.println(RectCover4(n));

        }
    }
}
