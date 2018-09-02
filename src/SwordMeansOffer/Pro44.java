package SwordMeansOffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Special on 2018/8/18 17:43
 */
public class Pro44 {

    public int binarySearch(int[] array, int num) {
        int left = 0, right = array.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(array[mid] > num) {
                right = mid - 1;
            } else if(array[mid] < num) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if(array == null || array.length < 2) {
            return result;
        }
        for(int i = 0; i < array.length - 1; i++) {
            int num = sum - array[i];
            if(binarySearch(array, num) != -1) {
                result.add(array[i]);
                result.add(num);
                return result;
            }
        }
        return result;
    }

    /**
     * 左右夹逼
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum2(int [] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if(array == null || array.length < 2) {
            return result;
        }
        int left = 0, right = array.length - 1;
        while(left < right) {
            int temp = array[left] + array[right];
            if(temp < sum) {
                left++;
            } else if(temp > sum) {
                right--;
            } else {
                result.add(array[left]);
                result.add(array[right]);
                return result;
            }
        }
        return result;
    }

    /**
     * 空间换时间
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum3(int [] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if(array == null || array.length < 2) {
            return result;
        }
        Set<Integer> sets = new HashSet<>();
        for(int i = 0; i < array.length; i++) {
           sets.add(array[i]);
        }
        for(int i = 0; i < array.length - 1; i++) {
           int num = sum - array[i];
           if(sets.contains(num)) {
               result.add(array[i]);
               result.add(num);
           }
        }
        return result;
    }
}
