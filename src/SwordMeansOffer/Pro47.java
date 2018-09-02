package SwordMeansOffer;

import java.util.Arrays;

/**
 * Created by Special on 2018/8/21 21:18
 */
public class Pro47 {

    /**
     * dfs做法
     * @param numbers
     * @return
     */
    public static boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length < 5) {
            return false;
        }
        boolean[] visited = new boolean[16];
        for(int i = 0; i < numbers.length; i++) {
            visited[numbers[i]] = true;
        }
        return dfs(numbers, 0, visited, 0);
    }

    public static boolean dfs(int[] numbers, int index, boolean[] visited, int previous) {
        if(index == 5) {
            if (isContinuousHelper(numbers)) {
                return true;
            }
            return false;
        }
        //如果为0，这里就遍历取值考察
        if(numbers[index] == 0) {
            for (int i = previous + 1; i < 16; i++) {
                if(!visited[i]) {
                    numbers[index] = i;
                    if (dfs(numbers, index + 1, visited, i)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            //如果是固定的值，直接跳过
            return dfs(numbers, index + 1, visited, previous);
        }
    }

    public static boolean isContinuousHelper(int[] numbers) {
        int min = findMin(numbers);
        int max = findMax(numbers);
        int actualSum = 0;
        for(int i = 0; i < numbers.length; i++) {
            actualSum += numbers[i];
        }
        int targetSum = (min + max) * 2 + (min + max) / 2;
        return findMax(numbers) - findMin(numbers) == 4 && actualSum == targetSum;
    }

    public static int findMin(int[] numbers) {
        int result = 16;
        for(int item : numbers) {
            result = Math.min(result, item);
        }
        return result;
    }

    public static int findMax(int[] numbers) {
        int result = 0;
        for(int item : numbers) {
            result = Math.max(result, item);
        }
        return result;
    }


    public static boolean isContinuous2(int [] numbers) {
        if(numbers == null || numbers.length < 5) {
            return false;
        }
        int zeroCount = 0;
        Arrays.sort(numbers);
        for(int i = 0; i < numbers.length - 1; i++) {
            //统计0的个数
            if (numbers[i] == 0) {
                zeroCount++;
            } else if(numbers[i] == numbers[i + 1]) {
                //不为0的两个数间隔为0，说明是一个对子，不满足顺子的要求
                return false;
            } else {
                //拿0的个数补充序列中出现的间隔
                zeroCount -= (numbers[i + 1] - numbers[i] - 1);
            }
        }
        //最后若间隔不小于0，那么说明可以组成顺子
        return zeroCount >= 0;
    }


    public static void main(String[] args) {
        int[] numbers = {0,3,2,6,4};
        System.out.println(isContinuous2(numbers));
    }
}
