package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/22 14:02
 */
public class Pro18 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    static ListNode head1;
    static ListNode head2;

    /**
     * 第一次思路
     * 重点的处理放在了处理合并后链表的第一个节点上了！
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge1(ListNode list1,ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        ListNode head = null;
        //为了避免在下面循环中判断头指针是否为空，特意先处理出头结点
        if(list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        }else {
            head = list2;
            list2 = list2.next;
        }
        ListNode p = head;
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                p.next = list1;
                p = p.next;
                list1 = list1.next;
            }else {
                p.next = list2;
                p = p.next;
                list2 = list2.next;
            }
        }
        if(list1 == null) {
            p.next = list2;
        }
        if(list2 == null) {
            p.next = list1;
        }
        return head;
    }

    /**
     * 精简实现，添加一个额外的头结点，简化编码
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge2(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                p.next = list1;
                p = p.next;
                list1 = list1.next;
            }else {
                p.next = list2;
                p = p.next;
                list2 = list2.next;
            }
        }
        if(list1 == null) {
            p.next = list2;
        }
        if(list2 == null) {
            p.next = list1;
        }
        return head.next;
    }

    /**
     * 漂亮的递归实现
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge3(ListNode list1,ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        if(list1.val <= list2.val) {
            list1.next = Merge3(list1.next, list2);
            return list1;
        }else {
            list2.next = Merge3(list1, list2.next);
            return list2;
        }
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
            head1 = new ListNode(input.nextInt());
            //头插法构成链表
            for(int i = 1; i < n; i++) {
                ListNode node = new ListNode(input.nextInt());
                node.next = head1;
                head1 = node;
            }
            n = input.nextInt();
            head2 = new ListNode(input.nextInt());
            //头插法构成链表
            for(int i = 1; i < n; i++) {
                ListNode node = new ListNode(input.nextInt());
                node.next = head2;
                head2 = node;
            }
            System.out.println(toString(head1));
            System.out.println(toString(head2));
            ListNode head = Merge2(head1, head2);
            System.out.println(toString(head));
        }
    }
}
