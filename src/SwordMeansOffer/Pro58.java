package SwordMeansOffer;

/**
 * Gitbub: https://github.com/SpecialYy/Sword-Means-Offer
 * Created by SpecialYang on 2018/10/15 00:10.
 */
public class Pro58 {

     public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 添加头结点的方式降低了当首部有重复的节点时的复杂性
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        //添加头结点
        ListNode head = new ListNode(0);
        head.next = pHead;
        //p为目前构建的链表的最后一个节点
        ListNode p = head;
        //s为窗口的左边界，d为窗口的右边界
        ListNode s = pHead;
        ListNode d = pHead.next;
        while (d != null) {
            //窗口中出现不重复的节点
            if (s.val != d.val) {
                //若当前窗口大小为1，说明s不是重复节点
                if (s.next == d) {
                    //把s加到当前的链表
                    p.next = s;
                    //更新p
                    p = p.next;
                }
                /**
                 * 下面主要是更新窗口
                 * 1. 若当前窗口为1，s已加入到链表中，所以更新s
                 * 2. 若当前窗口不为1，说明s到d-1的窗口中都是重复的，所以更新s
                 */
                s = d;
                d = d.next;
            } else {
                //窗口中出现重复元素，更新窗口的右边界
                d = d.next;
            }
        }
        /**
         * 上面的循环结束，可能是窗口为1或者大于1时推出的
         * 可根据窗口的左边界判断出窗口的大小
         */
        p.next = s.next == null ? s : null;
        return head.next;
    }
}
