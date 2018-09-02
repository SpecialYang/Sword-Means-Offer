package SwordMeansOffer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Special on 2018/6/8 10:30
 */
public class Pro03 {

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode (int val) {
            this.val = val;
        }
    }

    /**
     * 正常打印，最后交换
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        while(listNode != null) {
            result.add(listNode.val);
            listNode = listNode.next;
        }
        for(int i = 0; i < result.size() / 2; i++) {
            int temp = result.get(i);
            result.set(i, result.get(result.size() - i - 1));
            result.set(result.size() - i - 1, temp);
        }
        return result;
    }

    static ArrayList<Integer> result = new ArrayList<>();

    /**
     * 递归做法，简单粗暴
     * 为了避免每次都要创建ArrayList，所以声明为成员变量
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if(listNode != null) {
            printListFromTailToHead2(listNode.next);
            result.add(listNode.val);
        }
        return result;
    }

    /**
     * 借助栈
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        while(listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()) {
            result.add(stack.pop().val);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            ListNode head = new ListNode(input.nextInt());
            //头插法构成链表
            for(int i = 1; i < n; i++) {
                ListNode node = new ListNode(input.nextInt());
                node.next = head;
                head = node;
            }
            ArrayList<Integer> result1 = printListFromTailToHead1(head);
            for(int item : result1){
                System.out.print(item + " ");
            }
            System.out.println();

            ArrayList<Integer> result2 = printListFromTailToHead2(head);
            for(int item : result2){
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
