package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/16 23:11
 */
public class Pro12 {

    /**
     * 完备的double型的整数幂次方函数
     * 这里用的快速幂思想
     * @param base
     * @param exponent
     * @return
     */
    public static double Power(double base, int exponent) {
        if(equal(base, 0.0) && exponent < 0) {
            throw new IllegalArgumentException("分母不能为0");
        }
        int absExponent = exponent < 0 ? -exponent : exponent;
        double res = 1;
        double t = base;
        while(absExponent != 0) {
            if((absExponent & 1) == 1) {
                res *= t;
            }
            t *= t;
            absExponent >>= 1;
        }
        return exponent < 0 ? 1.0 / res : res;
    }

    /**
     * 判断double型数据的是否相等
     * @param num1
     * @param num2
     * @return
     */
    public static boolean equal(double num1, double num2) {
        return num1 - num2 < 1e-8 && num1 - num2 > -1e-8;
    }

    /**
     * 递归的形式求快速幂
     * @param base
     * @param exponent
     * @return
     */
    public static double recursivePow(double base, int exponent) {
        if(exponent == 0) {
            return 1;
        }
        if(exponent == 1) {
            return base;
        }
        double res = recursivePow(base, exponent >> 1);
        if((exponent & 1) == 1) {
            res *= base;
        }
        return res;
    }
    /**
     * 不准备的思路
     * @param base
     * @param exponent
     * @return
     */
    public static double Power2(double base, int exponent) {
        double res = 1.0;
        for(int i = 1; i <= exponent; i++) {
            res *= base;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            double base = input.nextDouble();
            int exponent = input.nextInt();
            System.out.println(Power(base, exponent));
        }
    }
}
