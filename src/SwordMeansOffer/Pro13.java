package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/17 15:00
 */
public class Pro13 {

    /**
     * 笨方法，借助辅助空间
     * @param array
     */
    public static void reOrderArray1(int [] array) {
        int index = 0;
        int[] newArray = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            if((array[i] & 1) == 1) {
                newArray[index++] = array[i];
            }
        }
        for(int i = 0; i < array.length; i++) {
            if((array[i] & 1) == 0) {
                newArray[index++] = array[i];
            }
        }
        System.arraycopy(newArray, 0, array, 0, array.length);
    }

    /**
     * 原址上操作，但是复杂度会较高
     * @param array
     */
    public static void reOrderArray2(int [] array) {
        for(int i = 0; i < array.length; i++) {
            if((array[i] & 1) == 1 && i != 0) {
                int index = i;
                while(index > 0 && (array[index - 1] & 1) != 1) {
                    int temp = array[index];
                    array[index] = array[index - 1];
                    array[index - 1] = temp;
                    index--;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] array = new int[n];
            for(int i = 0; i < n; i++) {
                array[i] = input.nextInt();
            }
//            reOrderArray1(array);
            reOrderArray2(array);
            for(int i = 0; i < n; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }
}
