package SwordMeansOffer;

import java.util.*;

/**
 * Created by Special on 2018/7/22 9:08
 */
public class Pro30 {
    //测试用例： [1,2,3,2,2,2,5,4,2]

    /**
     * 排序
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution(int [] array) {
        if(array == null) {
            return 0;
        }
        Arrays.sort(array);
        int result = array[array.length >> 1];
        if(checkValid(array, result)) {
            return result;
        }
        return 0;
    }

    /**
     * 哈希的思想，数组的值作为键，次数作为值
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution2(int [] array) {
        if(array == null) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        int targetCount = array.length >> 1;
        for(int i = 0; i < array.length; i++) {
            count.put(array[i], (count.get(array[i]) == null ? 0 : count.get(array[i])) + 1);
            if(count.get(array[i]) > targetCount) {
                return array[i];
            }
        }
        return 0;
    }

    /**
     * 快速排序的思想，基于partion, 调整数组
     * 使得数组中k索引的值就是kth的值
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution3(int [] array) {
        if(array == null) {
            return 0;
        }
        int targetIndex = array.length >> 1;
        findKthValue(array, 0, array.length - 1, targetIndex);
        if(checkValid(array, array[targetIndex])) {
            return array[targetIndex];
        }
        return 0;
    }

    /**
     * 校验该值是否真的出现了超过一半的次数
     * @param array
     * @param value
     * @return
     */
    public static boolean checkValid(int[] array, int value) {
        int count = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == value) {
                count++;
            }
        }
        return count > (array.length >> 1);
    }
    public static void findKthValue(int[] array, int low, int high, int k) {
        if(low < high) {
            int randomPivot = new Random().nextInt(high - low + 1) + low;
            swap(array, randomPivot, high);
            int index = low;
            for(int i = low; i < high; i++) {
                if(array[i] < array[high]) {
                    swap(array, index, i);
                    index++;
                }
            }
            swap(array, index, high);
            if(index > k) {
                findKthValue(array, low, index - 1, k);
            }else if(index < k){
                findKthValue(array, index + 1, high, k);
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        if(i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static int MoreThanHalfNum_Solution4(int [] array) {
        if(array == null) {
            return 0;
        }
        int result = 0, count = 0;
        for(int i = 0; i < array.length; i++) {
            if(i == 0 || count == 0) {
                result = array[i];
                count++;
            } else if(array[i] == result) {
                count++;
            }else {
                count--;
            }
        }
        if(checkValid(array, result)) {
            return result;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] nums = new int[n];
            for(int i = 0; i < n; i++) {
                nums[i] = input.nextInt();
            }
            int index = MoreThanHalfNum_Solution4(nums);
            System.out.println(String.format("index: %d, value: %d",
                    index, nums[index]));
        }
    }
}
