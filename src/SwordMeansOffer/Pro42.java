package SwordMeansOffer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Special on 2018/8/15 23:47
 */
public class Pro42 {

    public static void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if(array == null || array.length < 2) {
            return;
        }
        int xorResult = 0;
        for(int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }
        int firstOneIndex = findFirstOne(xorResult);
        for(int i = 0; i < array.length; i++) {
            if(isOneByIndex(array[i], firstOneIndex)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }


    private static boolean isOneByIndex(int number, int index) {
        return ((number >> index) & 1) == 1;
    }

    private static int findFirstOne(int number) {
        int index = 0;
        while((number & 1) == 0) {
            index++;
            number >>= 1;
        }
        return index;
    }

    public static void FindNumsAppearOnce2(int [] array, int num1[], int num2[]) {
        if(array == null || array.length < 2) {
            return;
        }
        Set<Integer> nums = new HashSet<>();
        for(int i = 0; i < array.length; i++) {
            if(nums.contains(array[i])) {
                nums.remove(array[i]);
            } else {
                nums.add(array[i]);
            }
        }
        Iterator<Integer> iterable = nums.iterator();
        num1[0] = iterable.next();
        num2[0] = iterable.next();
    }

    public static int FindNumsAppearOnce(int [] array) {
        if(array == null || array.length < 1) {
            return 0;
        }
        int result = 0;
        for(int i = 0; i < array.length; i++) {
            result ^= array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] num = new int[n];
            for(int i = 0; i < n; i++) {
                num[i] = input.nextInt();
            }
            int[] num1 = new int[1];
            int[] num2 = new int[1];
            FindNumsAppearOnce(num, num1, num2);
            System.out.println(num1[0] + " " + num2[0]);
        }
    }
}
