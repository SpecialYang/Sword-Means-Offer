package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/7/23 18:46
 */
public class Pro32 {

    public static int FindGreatestSumOfSubArray(int[] array) {
        if(array == null) {
            return 0;
        }
        int sum = Integer.MIN_VALUE;
        int pre = 0;
        for(int i = 0; i < array.length; i++) {
            pre = Math.max(pre + array[i], array[i]);
            sum = Math.max(sum, pre);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] array = new int[n];
            for(int i = 0; i < n; i++) {
                array[i] = input.nextInt();
            }
            System.out.println(FindGreatestSumOfSubArray(array));
        }
    }
}
