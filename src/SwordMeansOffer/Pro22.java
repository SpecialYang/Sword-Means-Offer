package SwordMeansOffer;

import java.util.Stack;

/**
 * Created by Special on 2018/6/30 14:48
 */
public class Pro22 {

    //数据栈---存数据
    static Stack<Integer> data = new Stack<>();
    //辅助栈---存最小值
    static Stack<Integer> minData = new Stack<>();

    public static void push(int node) {
        data.push(node);
        if(minData.isEmpty()) {
            minData.push(node);
        } else {
            minData.push(Math.min(node, minData.peek()));
        }
    }

    public static void pop() {
        if(data.size() == 0) {
            throw new RuntimeException("Stack is empty!");
        }
        data.pop();
        minData.pop();
    }

    public static int top() {
        return data.peek();
    }

    public static int min() {
        return minData.peek();
    }

    public static void main(String[] args) {
        push(3);
        push(4);
        push(2);
        push(1);
        pop();
        pop();
        push(0);
    }
}
