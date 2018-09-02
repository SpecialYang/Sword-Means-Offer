package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/19 22:58
 */
public class Pro17 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    static ListNode head;

    /**
     * 利用三个指针进行链表反序
     * @param head
     * @return
     */
    public static ListNode ReverseList1(ListNode head) {
        //pre始终指向已反序的最后一个节点
        ListNode pre = null;
        //middle始终指向正在考察节点
        ListNode middle = head;
        //after始终指向待反序的第一个节点，也就是middle之后
        ListNode after = null;
        while(middle != null) {
            //更新after
            after = middle.next;
            //对考察的节点进行反序
            middle.next = pre;
            //更新pre
            pre = middle;
            //后移middle, 换下一个待考察节点
            middle = after;
        }
        //因为pre始终指向已反序的最后的一个节点
        return pre;
    }

    /**
     * 充分利用head指针和外加2个辅助指针
     * @param head
     * @return
     */
    public static ListNode ReverseList2(ListNode head) {
        ListNode pre = null;
        ListNode after = null;
        while(head != null) {
            after = head.next;
            head.next = pre;
            pre = head;
            head = after;
        }
        return pre;
    }

    /**
     * 牛逼的递归实现
     * @param head
     * @return
     */
    public static ListNode ReverseList3(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode reverseHead = ReverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    /**
     * 打印链表
     * @param p
     * @return
     */
    public static String toString(ListNode p){
        StringBuilder sb = new StringBuilder();
        while(p != null) {
            sb.append(p.val);
            sb.append(" ");
            p = p.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            head = new ListNode(input.nextInt());
            //头插法构成链表
            for(int i = 1; i < n; i++) {
                ListNode node = new ListNode(input.nextInt());
                node.next = head;
                head = node;
            }
            System.out.println(toString(head));
            head = ReverseList3(head);
            System.out.println(toString(head));
        }
    }
}
