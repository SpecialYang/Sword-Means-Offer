package SwordMeansOffer;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Special on 2018/6/10 17:43
 */
public class Pro05 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pro05 test = new Pro05();
        while(input.hasNext()) {
            int n = input.nextInt();
            for(int i = 0; i < n; i++) {
                test.push(i);
            }
            for(int i = 0; i < n; i++) {
                System.out.print(test.pop() + " ");
            }
            System.out.println();
        }
    }
}
