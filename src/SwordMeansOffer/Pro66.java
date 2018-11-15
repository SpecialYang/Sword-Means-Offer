package SwordMeansOffer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by SpecialYang on 2018/11/12 23:30.
 */
public class Pro66 {

    /**
     * 普通做法，不断分段求最大值
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (num == null || size <= 0 || size > num.length) {
            return result;
        }
        for (int i = 0; i <= num.length - size; i++) {
            result.add(maxNumOfInternal(num, i, i + size));
        }
        return result;
    }

    public int maxNumOfInternal(int[] num, int start, int end) {
        int result = num[start];
        for (int i = start + 1; i < end; i++) {
            result = Math.max(result, num[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        Pro66 pro66 = new Pro66();
        ArrayList<Integer> result = pro66.maxInWindows2(new int[] {2,3,4,2,6,2,5,1}, 3);
        for (int item : result) {
            System.out.println(item);
        }
    }

    /**
     * 维护一个队头为最大值的队列，单调递减队列
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows2(int [] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (num == null || size <= 0 || size > num.length) {
            return result;
        }
        //初始化窗口
        LinkedList<Integer> windows = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            while (windows.size() != 0 && num[windows.getLast()] <= num[i]) {
                windows.removeLast();
            }
            windows.addLast(i);
        }
        result.add(num[windows.getFirst()]);

        for (int i = size; i < num.length; i++) {
            if (i - windows.getFirst() == size) {
                windows.removeFirst();
            }
            while(windows.size() != 0 && num[windows.getLast()] <= num[i]) {
                windows.removeLast();
            }
            windows.addLast(i);
            result.add(num[windows.getFirst()]);
        }
        return result;
    }
}
