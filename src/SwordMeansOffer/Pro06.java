package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/11 20:00
 */
public class Pro06 {

    public static int minNumberInRotateArray(int [] array) {
        if(array.length == 0) {
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        int mid = low;
        while(array[low] >= array[high]) {
            if(high - low == 1) {
                mid = high;
                break;
            }
            mid = low + (high - low) / 2;
            /**
             * 我知道剑指Offer中是让按顺序查找，那么这样复杂度不还是O(n)吗？
             * 所以按照划分子问题的方法，根据mid划分2个子分组
             * 然后对这两个子数组继续使用我们的二分即可呀！
             * 采用了尾递归，不存在栈溢出，同时避免了顺序查找
             * 优化：当然你可以令开一个函数，记录区间，避免我的数组复制的开销
             * 我tm沙比了，这tm的复杂度飙到了O(nlogn)了
             */
//            if(array[low] == array[mid] && array[mid] == array[high]) {
//                int[] left = new int[mid - low + 1];
//                int[] right = new int[high - mid + 1];
//                System.arraycopy(array, low, left, 0, left.length);
//                System.arraycopy(array, mid, right, 0, right.length);
//                return Math.min(minNumberInRotateArray(left), minNumberInRotateArray(right));
//            }
            if(array[low] == array[mid] && array[mid] == array[high]) {
                return ordinalGetMin(array, low, high);
            }
            if(array[low] <= array[mid]) {
                low = mid;
            }else if(array[mid] <= array[high]) {
                high = mid;
            }
        }
        return array[mid];
    }

    public static int ordinalGetMin(int[] array, int low, int high) {
//        int min = Integer.MAX_VALUE;
//        for(int i = low; i <= high; i++) {
//            min = Math.min(min, array[i]);
//        }
//        return min;
        for(int i = low + 1; i <= high; i++) {
            if(array[i] < array[i - 1]) {
                return array[i];
            }
        }
        return array[low];
    }

    private static void reverseArray(int start, int end, int[] array) {
        for(int i = start; i <= end; i++) {
            int temp = array[i];
            array[i] = array[end - i];
            array[end - i] = temp;
        }
    }

    public static void revolveArray(int index, int[] array) {
        reverseArray(0, index, array);
        reverseArray(index + 1, array.length - 1, array);
        reverseArray(0, array.length - 1, array);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = input.nextInt();
            }
//            revolveArray(new Random().nextInt(n), array);
            System.out.println(minNumberInRotateArray(array));
        }
    }
}
