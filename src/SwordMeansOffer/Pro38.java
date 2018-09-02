package SwordMeansOffer;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Special on 2018/8/10 21:38
 */
public class Pro38 {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 空间换时间
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while(pHead1 != null) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }
        while(pHead2 != null) {
            if(set.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 相交的链表必有相同的尾部
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode p = pHead1, q = pHead2;
        int length1 = 1, length2 = 1;
        while(p.next != null) {
            p = p.next;
            length1++;
        }
        while(q.next != null) {
            q = q.next;
            length2++;
        }
        if(p == q) {
            int diff = Math.abs(length1 - length2);
            if(length1 > length2) {
                p = pHead1;
                q = pHead2;
            } else {
                p = pHead2;
                q = pHead1;
            }
            while(diff-- > 0) {
                p = p.next;
            }
            while(p != q) {
                p = p.next;
                q = q.next;
            }
            return p;
        }
        return null;
    }

    /**
     * 利用补齐法达到使两个链表长度相同
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {
        ListNode p = pHead1, q = pHead2;
        while(p != q) {
            p = p == null ? pHead2 : p.next;
            q = q == null ? pHead1 : q.next;
        }
        return p;
    }

    public static ListNode insertHead(ListNode pHead, int val) {
        ListNode node = new ListNode(val);
        node.next = pHead;
        pHead = node;
        return pHead;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            ListNode pHead1 = null, pHead2 = null;
            for(int i = 0; i < n; i++) {
                pHead1 = insertHead(pHead1, input.nextInt());
            }
            n = input.nextInt();
            for(int i = 0; i < n; i++) {
                pHead2 = insertHead(pHead2, input.nextInt());
            }
            pHead2.next = pHead1.next.next;
            System.out.println(FindFirstCommonNode(pHead1, pHead2).val);
            System.out.println(FindFirstCommonNode2(pHead1, pHead2).val);
        }
    }
}
