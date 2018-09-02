package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/8/11 21:51
 */
public class Pro39 {

    public static int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0) {
            return 0;
        }
        int count = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == k) {
                count++;
            }
        }
        return count;
    }

    public static int GetNumberOfK2(int [] array , int k) {
        if(array == null || array.length == 0) {
            return 0;
        }
        int firstOcur = findFirst(array, k);
        int lastOcur = findLast(array, k);
        return lastOcur - firstOcur + 1;
    }

    /**
     * 变形的二分查找
     * @param array
     * @param k
     * @return start指向k第一次出现的位置，或者指向刚刚大于k的位置（不存在k)
     */
    public static int findFirst(int[] array, int k) {
        int start = 0, end = array.length - 1;
        while(start <= end) {
            int mid = start + ((end - start) >> 1);
            if(array[mid] < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    /**
     * 变形的二分查找
     * @param array
     * @param k
     * @return end指向k最后一次出现的位置，或者指向刚刚小于k的位置（不存在k)
     */
    public static int findLast(int[] array, int k) {
        int start = 0, end = array.length - 1;
        while(start <= end) {
            int mid = start + ((end - start) >> 1);
            if(array[mid] > k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static int GetNumberOfK3(int [] array , int k) {
        if(array == null || array.length == 0) {
            return 0;
        }
        int index = binarySearch(array, k);
        int count = 0;
        if(index != - 1) {
            count++;
            //向前搜查
            for(int i = index - 1; i >= 0; i--) {
                if(array[i] == k) {
                    count++;
                }
            }
            //向后搜查
            for(int i = index + 1; i < array.length; i++) {
                if(array[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int binarySearch(int[] array, int k) {
        int start = 0, end = array.length - 1;
        while(start <= end) {
            int mid = start + ((end - start) >> 1);
            if(array[mid] < k) {
                start = mid + 1;
            } else if(array[mid] > k) {
                end = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] num = new int[n];
            for(int i = 0; i < n; i++) {
                num[i] = input.nextInt();
            }
            System.out.println(GetNumberOfK(num, 2));
            System.out.println(GetNumberOfK2(num, 2));
        }
    }
}
