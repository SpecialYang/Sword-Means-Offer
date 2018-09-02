package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/19 22:58
 */
public class Pro15 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
    static ListNode head;

    /**
     * 对于给定节点的指针，可以在O(1)的时间内删除它
     * @param target
     */
    public static void deleteNode(ListNode target) {
        if(head == null || target == null) {
            return;
        }
        if(target.next != null) { //当目标节点不是尾节点时
            ListNode p = target.next;
            target.val = p.val;
            target.next = p.next;
        }else if(head == target) { //当目标节点是尾节点且只要一个时
            head = null;
        }else { //当目标节点时尾节点时
            ListNode p = head;
            while(p.next != target) {
                p = p.next;
            }
            p.next = target.next;
        }
    }
    /**
     * 获得链表中某一项的指针
     * @param head
     * @param index
     * @return
     */
    static ListNode getListNodeByIndex(ListNode head, int index) {
        int count = 0;
        ListNode p = head;
        while(count != index) {
            p = p.next;
            count++;
        }
        return p;
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
            ListNode target = getListNodeByIndex(head, 0);
            deleteNode(target);
            System.out.println(toString(head));
        }
    }
}
