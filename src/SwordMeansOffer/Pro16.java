package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/19 22:58
 */
public class Pro16 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
    static ListNode head;

    /**
     * 我的思路，用2个指针，一个先走k步，然后再一起走，当第二指针走到n位时，
     * 第一个指针正好指向(n - k)位置
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail1(ListNode head, int k) {
        if(head == null || k <= 0) {
            return null;
        }
        ListNode q = head;
        ListNode p = head;
        for(int i = 1; i <= k; i++) {
            if(p != null) {
                p = p.next;
            }else { // 说明k大于结点总数量了
                return null;
            }
        }
        while(p != null) {
            q = q.next;
            p = p.next;
        }
        return q;
    }

    /**
     * 牛逼的写法
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail2(ListNode head, int k) {
        if(head == null || k <= 0) {
            return null;
        }
        ListNode q = head;
        ListNode p = head;
        int i = 0;
        for(; p != null; i++) {
            if(i > k) {
                q = q.next;
            }
            p = p.next;
        }
        return i < k ? null : q;
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

    /**
     * 寻找链表的中间节点
     * 当节点数量为奇数时，则返回中间节点
     * 当节点数量为偶数时，则返回中间两个节点的第一个节点
     * @param head
     * @return
     */
    public static ListNode findMiddle(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode q = head;
        ListNode p = head.next;
        while(p != null && p.next != null) {
            q = q.next;
            p = p.next.next;
        }
        return q;
    }

    /**
     * 判断单链表是否有环
     * @param head
     * @return
     */
    public static boolean isCircle(ListNode head) {
        if(head == null) {
            return false;
        }
        ListNode q = head;
        ListNode p = head.next;
        while(p != null && p.next != null && p != q) {
            q = q.next;
            p = p.next.next;
        }
        return p != q ? false : true;
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
            ListNode p = getListNodeByIndex(head, 2);
            ListNode q = getListNodeByIndex(head, n - 1);
//            q.next = p;
//            ListNode node = FindKthToTail1(head, 5);
//            ListNode middle = findMiddle(head);
//            System.out.println(node.val);
//            System.out.println(middle.val);
            System.out.println(isCircle(head));
        }
    }
}
