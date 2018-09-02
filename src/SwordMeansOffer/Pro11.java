package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/15 14:34
 */
public class Pro11 {

    /**
     * 普通做法，通过移位操作进行
     * @param n
     * @return
     */
    public static int NumberOf1One(int n) {
        int count = 0;
        while(n != 0) {
            if((n & 1) == 1) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }

    /**
     *  n &= n - 1
     * @param n
     * @return
     */
    public static int NumberOf1Two(int n) {
        int count = 0;
        while(n != 0) {
            count++;
            n &= n - 1;
        }
        return count;
    }

    /**
     * 左移辅助变量
     * @param n
     * @return
     */
    public static int NumberOf1Three(int n) {
        int count = 0;
        int t = 1;
        for(int i = 1; i <= 32; i++) {
            if((n & t) != 0) {
                count++;
            }
            t <<= 1;
        }
        return count;
    }

    /**
     * 判断一个整数是否是2的整数次方
     * @param num
     * @return
     */
    public static boolean isTwoTimes(int num) {
        return (num & (num - 1)) == 0;
    }

    public static int countOfChange(int num1, int num2) {
        int res = num1 ^ num2;
        int count = 0;
        while(res != 0) {
            count++;
            res &= res - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        System.out.println(Integer.MIN_VALUE - 1);
//        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE - 1));
        while(input.hasNext()) {
            int num = input.nextInt();
            System.out.println(NumberOf1One(num));
            System.out.println(NumberOf1Two(num));
            System.out.println(NumberOf1Three(Integer.MAX_VALUE));
            System.out.println(isTwoTimes(num));
            System.out.println(countOfChange(10, 13));
        }
    }
}
