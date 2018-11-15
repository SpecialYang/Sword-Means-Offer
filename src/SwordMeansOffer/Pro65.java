package SwordMeansOffer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by SpecialYang on 2018/11/7 22:04.
 */
public class Pro65 {


    //  方法一： 自动扩容数组法
    class AdaptiveArray {
        int size = 0;
        int[] value;

        public AdaptiveArray() {
            value = new int[10];
        }

        public AdaptiveArray(int capacity) {
            value = new int[capacity];
        }

        public void addElementByOrder(int item) {
            if (size == value.length) {
                Expansion(size);
            }
            value[size++] = item;
            for (int i = size - 1; i > 0 && value[i] < value[i - 1]; i--) {
                int temp = value[i];
                value[i] = value[i - 1];
                value[i - 1] = temp;
            }
        }

        public double getMiddleNumber() {
            if (size == 0) {
                return 0;
            }
            if((size & 1) == 1) {
                return value[size / 2];
            } else {
                int mid = size / 2;
                return ((double) value[mid] + value[mid - 1]) / 2;
            }
        }

        public void Expansion(int length) {
            int[] newValue = new int[length << 1];
            System.arraycopy(value, 0, newValue, 0, length);
            value = newValue;
        }

    }

    AdaptiveArray adaptiveArray = new AdaptiveArray();

    public void Insert(Integer num) {
        adaptiveArray.addElementByOrder(num);
    }

    public Double GetMedian() {
        return adaptiveArray.getMiddleNumber();
    }

    // 方法二：链表法

    class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    ListNode head = new ListNode(0);
    ListNode midLeft = null, midRight = null;
    int size = 0;

    public void Insert1(Integer num) {
        ListNode listNode = new ListNode(num);
        ListNode p = head;
        while (p.next != null && p.next.value < num) {
            p = p.next;
        }
        listNode.next = p.next;
        p.next = listNode;
        midLeft = findMiddleListNode(head.next);
        size++;
        if ((size & 1) == 1) {
            midRight = midLeft;
        } else {
            midRight = midLeft.next;
        }
    }

    public Double GetMedian1() {
        return ((double) midLeft.value + midRight.value) / 2;
    }

    /**
     * 寻找链表的中间节点
     * @param node
     * @return
     */
    public ListNode findMiddleListNode(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode first = node;
        ListNode second = node.next;
        while(second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        return first;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pro65 pro65 = new Pro65();
        while(input.hasNext()) {
            int num = input.nextInt();
            pro65.Insert1(num);
            System.out.println(pro65.GetMedian1());
        }
    }

    //方法3：最大堆和最小堆

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    /**
     * 时刻保证左右两部分的节点数一致
     * @param num
     */
    public void Insert2(Integer num) {
        if (size == 0) {
            maxHeap.offer(num);
            minHeap.offer(num);
        } else {
            if ((size & 1) == 1) {
                if (num <= minHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(num);
                } else {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            } else {
                if (num <= minHeap.peek()) {
                    maxHeap.offer(num);
                    minHeap.offer(maxHeap.peek());
                } else {
                    minHeap.offer(num);
                    maxHeap.offer(minHeap.peek());
                }
            }
        }
        size++;
    }

    public Double GetMedian2() {
        if (size == 0)  {
            return 0.0;
        }
        return ((double) maxHeap.peek() + minHeap.peek()) / 2;
    }

    /**
     * 当有奇数个节点时，最大堆保证为中间节点以前的所有节点
     * 当有偶数个节点时，最大堆与最小堆的大小一致，且顶部各为左部分的最大值和右部分的最小值
     * @param num
     */
    public void Insert3(Integer num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        }
        minHeap.offer(num);
        if (maxHeap.size() == minHeap.size() + 2) {
            minHeap.offer(maxHeap.poll());
        }
        if (maxHeap.size() + 1 == minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public Double GetMedian3() {
        return maxHeap.size() == minHeap.size() ?
                ((double) maxHeap.peek() + minHeap.peek()) / 2 : (double) maxHeap.peek();
    }

    /**
     * 这里还是规定了当节点数为奇数时，中间节点归在最大堆里
     * 运用了巧妙的方法：
     * 1.当节点数为奇数，表明要在最小堆里增加一个节点
     * 但是我们不能直接插入最小堆，先插入最大堆，然后弹出插入最小堆
     * 2.当节点数为偶数，表明要在最大堆里增加一个节点
     * 类似情况1，先插最小堆，然后再插最大堆
     * @param num
     */
    public void Insert4(Integer num) {
        if ((size & 1) == 0) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        size++;
    }
}
