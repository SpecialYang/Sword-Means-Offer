package SwordMeansOffer;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Special on 2018/7/4 13:30
 */
public class Pro23 {

    /**
     * 以弹出序列为主
     * @param pushA
     * @param popA
     * @return
     */
    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        if(pushA == null || popA == null) {
            return false;
        }
        int indexPush = 0;
        int indexPop = 0;
        Stack<Integer> stack = new Stack<>();
        boolean isPopOrder = true;
        //循环条件为弹出序列是否遍历完毕！
        while(indexPop < popA.length) {
            while(stack.isEmpty() || (indexPush < pushA.length
                    && stack.peek() != popA[indexPop])) {
                stack.push(pushA[indexPush++]);
            }
            /**
             * 结束循环后，如果栈顶还不等于弹出序列的当前值
             * 这时压入序列都遍历完了还不能与弹出序列的当前值一样
             * 则说明弹出序列不合法
             */
            if(stack.peek() != popA[indexPop]) {
                isPopOrder = false;
                break;
            }
            //栈顶与弹出序列当前值一样，栈弹出，弹出序列后移下一位
            stack.pop();
            indexPop++;
        }
        return isPopOrder;
    }

    /**
     * 以压入序列为主
     * 思路清晰，代码简单，推荐这个解法
     * @param pushA
     * @param popA
     * @return
     */
    public static boolean IsPopOrder2(int[] pushA, int[] popA) {
        if(pushA == null || popA == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int indexPop = 0;
        for(int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while(!stack.isEmpty() && stack.peek() == popA[indexPop]) {
                stack.pop();
                indexPop++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] push = new int[n];
            int[] pop = new int[n];
            for(int i = 0; i < n; i++) {
                push[i] = input.nextInt();
            }
            for(int i = 0; i < n; i++) {
                pop[i] = input.nextInt();
            }
            System.out.println(IsPopOrder(push, pop));
        }
    }
}
