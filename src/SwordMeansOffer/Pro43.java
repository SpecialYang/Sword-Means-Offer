package SwordMeansOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Special on 2018/8/17 0:58
 */
public class Pro43 {

    /**
     * 尺取法
     * @param sum
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(sum == 1) {
            return result;
        }
        //定义尺子
        ArrayList<Integer> temp = new ArrayList<>();
        int tempSum = 0;
        /**
         * 因为尺子最短长度为2，又因为mid + mid + 1 > sum
         * 所以只需遍历到sum的一半即可
         */
        int mid = (sum + 1) / 2;
        for(int i = 1; i <= mid; i++) {
            //尺子向后扩展
            tempSum += i;
            temp.add(i);
            //如果当前尺子的总和大于sum, 那么就需要削掉尺子的左端
            if(tempSum > sum) {
                //直到尺子的总和小于等于sum为止
                while(tempSum > sum) {
                    tempSum -= temp.get(0);
                    temp.remove(0);
                }
            }
            /**
             * 如果尺子的总和等于sum，则为目标尺子，添加到结果集中
             * 削掉尺子左端一个，继续向后考察
             */
            if(tempSum == sum) {
                ArrayList<Integer> item = new ArrayList<>();
                for(int t : temp) {
                    item.add(t);
                }
                result.add(item);
                tempSum -= temp.get(0);
                temp.remove(0);
            }
        }
        return result;
    }

    /**
     * 改进尺子
     * @param sum
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(sum <= 2) {
            return result;
        }
        int left = 1, right = 2, tempSum = 3;
        while(left < right) {
            if(tempSum < sum) {
                right++;
                tempSum += right;
            } else if(tempSum > sum) {
                tempSum -= left;
                left++;
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                for(int i = left; i <= right; i++) {
                    temp.add(i);
                }
                tempSum -= left;
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int sum = input.nextInt();
            List<ArrayList<Integer>> result = FindContinuousSequence(sum);
            for(ArrayList<Integer> item : result) {
                for(int i : item) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }

        }
    }
}
