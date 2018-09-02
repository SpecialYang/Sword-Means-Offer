package SwordMeansOffer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Special on 2018/6/17 16:37
 */
public class Pro14 {

    public static void printToMaxOfNDigits1(int n) {
        if(n <= 0) {
            return;
        }
        char[] nums = new char[n + 1];
        Arrays.fill(nums, '0');
        while(!increment(nums)) {
            printNum(nums);
        }
    }

    /**
     * 大数自增操作！
     * @param nums
     * @return
     */
    public static boolean increment(char[] nums) {
        int carry = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[i] - '0' + carry;
            //因为是加1，所以肯定是在最后一位上加1了
            if(i == nums.length - 1) {
                temp++;
            }
            carry = temp / 10;
            temp %= 10;
            nums[i] = (char)(temp + '0');
        }
        return nums[0] == '1';
    }

    public static void printNum(char[] nums) {
        int index = 0;
        for(; index < nums.length; index++) {
            if(nums[index] != '0'){
                break;
            }
        }
        for(; index < nums.length; index++) {
            System.out.print(nums[index]);
        }
        System.out.println();
    }

    public static void printToMaxOfNDigits2(int n) {
        if(n <= 0) {
            return;
        }
        char[] nums = new char[n];
        recursiveProductNum(0, n, nums);
    }

    public static void recursiveProductNum(int index, int length, char[] nums) {
        if(index == length) {
            printNum(nums);
            return;
        }
        for(char i = '0'; i <= '9'; i++){
            nums[index] = i;
            recursiveProductNum(index + 1, length, nums);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
//            printToMaxOfNDigits1(n);
            printToMaxOfNDigits2(n);
        }
    }
}
