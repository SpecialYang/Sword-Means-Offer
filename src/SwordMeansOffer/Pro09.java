package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/14 15:23
 */
public class Pro09 {

    public static int JumpFloorII1(int target) {
        if(target == 0 || target == 1) {
            return 1;
        }
        return fastPow(2, target - 1);
    }

    public static int fastPow(int base, int index) {
        int res = 1;
        int temp = base;
        while(index != 0) {
            if((index & 1) == 1) {
                res *= temp;
            }
            temp *= temp;
            index >>= 1;
        }
        return res;
    }

    public static int JumpFloorII2(int target) {
        int result = 1;
        while(--target > 0) {
            result *= 2;
        }
        return result;
    }

    public static int JumpFloorII3(int target) {
        return 1 << (target - 1);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            System.out.println(JumpFloorII1(n));
            System.out.println(JumpFloorII2(n));
            System.out.println(JumpFloorII3(n));
        }
    }
}
