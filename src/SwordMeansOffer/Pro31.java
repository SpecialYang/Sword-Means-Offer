package SwordMeansOffer;

import java.util.*;

/**
 * Created by Special on 2018/7/22 19:08
 */
public class Pro31 {

    /**
     * 排序的做法
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(input == null || k <= 0 || k > input.length) {
            return result;
        }
        Arrays.sort(input);
        for(int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }
    //-------------方法二-------------------

    /**
     * 基于快排的划分函数的思想来做的。
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(input == null || k <= 0 || k > input.length) {
            return result;
        }
        findKthValue(input, 0, input.length - 1, k - 1);
        for(int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    public static void findKthValue(int[] input, int low, int high, int k) {
        if(low < high) {
            int pivot = new Random().nextInt(high - low + 1) + low;
            swap(input, pivot, high);
            int index = low;
            for(int i = low; i < high; i++) {
                if(input[i] < input[high]) {
                    swap(input, i, index);
                    index++;
                }
            }
            swap(input, index, high);
            if(index > k) {
                findKthValue(input, low, index - 1, k);
            }else if(index < k) {
                findKthValue(input, index + 1, high, k);
            }
        }
    }


    public static void swap(int[] input, int i, int j) {
        if(i != j) {
            int temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }
    }

    //-----------方法3----------

    /**
     * Topk问题
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution3(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(input == null || k <= 0 || k > input.length) {
            return result;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {

            //因为要满足大根堆需求，所以使用自定义比较器，比较策略为o1大于o2时，o1放o2的前面
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < input.length; i++) {
            if(i < k) {
                priorityQueue.add(input[i]);
            } else if(input[i] < priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(input[i]);
            }
        }
        result.addAll(priorityQueue);
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] nums = new int[n];
            for(int i = 0; i < n; i++) {
                nums[i] = input.nextInt();
            }
            int k = input.nextInt();
            ArrayList<Integer> result = GetLeastNumbers_Solution3(nums, k);
            for(int item : result) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
