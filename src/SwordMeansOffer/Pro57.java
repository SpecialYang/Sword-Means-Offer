package SwordMeansOffer;

public class Pro57 {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 数学证明法---得出的结论
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead == null || pHead.next == null) {
            return null;
        }
        boolean isIntersect = true;
        ListNode p = pHead;
        ListNode q = pHead;
        while(q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                break;
            }
        }
        isIntersect = p == q ? true : false;
        if(isIntersect) {
            p = pHead;
            while(p != q) {
                p = p.next;
                q = q.next;
            }
            return p;
        }else {
            return null;
        }
    }

    /**
     * 利用环长法
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop2(ListNode pHead) {
        if(pHead == null || pHead.next == null) {
            return null;
        }
        boolean isIntersect = true;
        ListNode p = pHead;
        ListNode q = pHead;
        while(q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                break;
            }
        }
        isIntersect = p == q ? true : false;
        if(isIntersect) {
            int circleLength = caculateCircleLength(q);
            System.out.println(circleLength);
            q = pHead;
            while(circleLength-- > 0) {
                q = q.next;
            }
            p = pHead;
            while(p != q) {
                p = p.next;
                q = q.next;
            }
            return p;
        }else {
            return null;
        }
    }

    /**
     * 计算环长
     * @param node
     * @return
     */
    public int caculateCircleLength(ListNode node) {
        ListNode start = node;
        int length = 0;
        do {
            length++;
            node = node.next;
        }while(node != start);
        return length;
    }

    /**
     * 断链法
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop3(ListNode pHead) {
        if(pHead == null || pHead.next == null) {
            return null;
        }
        boolean isIntersect = true;
        ListNode p = pHead;
        ListNode q = pHead;
        while(q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                break;
            }
        }
        isIntersect = p == q ? true : false;
        if(isIntersect) {
            p = pHead;
            q = pHead.next;
            while(q != null) {
                p.next = null;
                p = q;
                q = q.next;
            }
            return p;
        }else {
            return null;
        }
    }


    public ListNode insert(ListNode pHead, ListNode node) {
        node.next = pHead;
        return node;
    }

    public void printList(ListNode pHead) {
        while(pHead != null) {
            System.out.print(pHead.val + " ");
            pHead = pHead.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Pro57 pro67 = new Pro57();
        ListNode pHead = null;
        for(int i = 4; i > 0; i--) {
            pHead = pro67.insert(pHead, pro67.new ListNode(i));
        }
        pro67.printList(pHead);
        pHead.next.next.next.next = pHead.next;
        System.out.println(pro67.EntryNodeOfLoop3(pHead).val);
    }
}
