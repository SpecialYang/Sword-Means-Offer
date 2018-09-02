package SwordMeansOffer;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Special on 2018/7/9 23:47
 */
public class Pro27 {

    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 复杂链表的复制
     * @param pHead
     * @return
     */
    public static RandomListNode Clone(RandomListNode pHead) {
        //仅看next，来复制整个链表
        RandomListNode copyList = ClonesNodes(pHead);
        //连接random域
        ConnectSiblingNodes(pHead, copyList);
        return copyList;
    }

    /**
     * 考察每一个节点的random在原链表中的位置，从头开始遍历直到random指定的节点为止，计算所需步长
     * 然后在复制的链表中走相应的步长即可达到其节点对应的random域
     * @param pHead
     * @param copyList
     */
    private static void ConnectSiblingNodes(RandomListNode pHead, RandomListNode copyList) {
        RandomListNode p = pHead, q = copyList;
        while(p != null) {
            RandomListNode randomNext = p.random;
            if(randomNext != null) {
                int steps = 0; //所需步长
                RandomListNode t = pHead;
                while(t != randomNext) {
                    t = t.next;
                    steps++;
                }
                t = copyList;
                int count = 0; //记录已走步长
                while(count < steps) {
                    t = t.next;
                    count++;
                }
                q.random = t;
            }
            p = p.next;
            q = q.next;
        }
    }

    /**
     * 仅按next域，复制整个链表
     * 我们对于复制的链表添加了一个头结点，方便添加节点和返回头指针！
     * @param pHead
     * @return
     */
    private static RandomListNode ClonesNodes(RandomListNode pHead) {
        RandomListNode copyList = new RandomListNode(0);
        RandomListNode p = pHead, q = copyList;
        while(p != null) {
            RandomListNode node = new RandomListNode(p.label);
            q.next = node;
            q = q.next;
            p = p.next;
        }
        return copyList.next;
    }

    //------------------方法二--------------------
    /**
     * 复杂链表的复制
     * @param pHead
     * @return
     */
    public static RandomListNode Clone2(RandomListNode pHead) {
        //仅看next，来复制整个链表
        RandomListNode copyList = ClonesNodes2(pHead);
        HashMap<RandomListNode, RandomListNode> record = initRecord(pHead, copyList);
        //连接random域
        ConnectSiblingNodes2(pHead, copyList, record);
        return copyList;
    }

    /**
     * 记录原链表与复制链表结点之间的对应关系
     * @param pHead
     * @param copyList
     * @return
     */
    private static HashMap<RandomListNode, RandomListNode> initRecord(RandomListNode pHead, RandomListNode copyList) {
        HashMap<RandomListNode, RandomListNode> record = new HashMap<>();
        RandomListNode p = pHead, q = copyList;
        while(p != null) {
            record.put(p, q);
            p = p.next;
            q = q.next;
        }
        return record;
    }

    /**
     * 通过查record确定random域的位置
     * @param pHead
     * @param copyList
     * @param record
     */
    private static void ConnectSiblingNodes2(RandomListNode pHead, RandomListNode copyList, HashMap<RandomListNode, RandomListNode> record) {
        RandomListNode p = pHead, q = copyList;
        while(p != null) {
            RandomListNode randomNext = p.random;
            q.random = record.get(randomNext);
            p = p.next;
            q = q.next;
        }
    }

    /**
     * 仅按next域，复制整个链表
     * 我们对于复制的链表添加了一个头结点，方便添加节点和返回头指针！
     * @param pHead
     * @return
     */
    private static RandomListNode ClonesNodes2(RandomListNode pHead) {
        RandomListNode copyList = new RandomListNode(0);
        RandomListNode p = pHead, q = copyList;
        while(p != null) {
            RandomListNode node = new RandomListNode(p.label);
            q.next = node;
            q = q.next;
            p = p.next;
        }
        return copyList.next;
    }

    //--------------方法3-----------
    public static RandomListNode Clone3(RandomListNode pHead) {
        ClonesNodes3(pHead);
        ConnectSiblingNodes3(pHead);
        //拆分
        RandomListNode copyList = splitList(pHead);
        return copyList;
    }

    /**
     * 拆分链表
     * @param pHead
     * @return
     */
    private static RandomListNode splitList(RandomListNode pHead) {
        RandomListNode p = pHead, copyList = null;
        if(p != null) {
            copyList = p.next;
            p.next = copyList.next;
            p = p.next;
        }
        RandomListNode q = copyList;
        while(p != null) {
            q.next = p.next;
            q = q.next;
            p.next = q.next;
            p = p.next;
        }
        return copyList;
    }


    private static void ConnectSiblingNodes3(RandomListNode pHead) {
        RandomListNode p = pHead, q;
        while(p != null) {
            q = p.next;
            RandomListNode random = p.random;
            if(random != null) {
                q.random = random.next;
            }
            p = q.next;
        }
    }


    private static void ClonesNodes3(RandomListNode pHead) {
        RandomListNode p = pHead;
        while(p != null) {
            RandomListNode node = new RandomListNode(p.label);
            node.next = p.next;
            p.next = node;
            p = node.next;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {

        }
    }
}
