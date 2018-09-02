package SwordMeansOffer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Special on 2018/8/22 21:31
 */
public class Pro48 {

    /**
     * 不使用任何数据结构，纯用标记数组
     * @param n
     * @param m
     * @return
     */
    public static int LastRemaining_Solution(int n, int m) {
        if(n == 0 || m <= 0) {
            return -1;
        }
        boolean[] visited = new boolean[n];
        int count = -1;
        int index = -1;
        int sum = 0;
        while(true) {
            index = index + 1 == n ? 0 : index + 1;
            if(!visited[index]) {
                count++;
            }
            if(count == m - 1) {
                visited[index] = true;
                if((++sum) == n) {
                    return index;
                }
                count = -1;
            }
        }
    }

    /**
     * 借助工具库  链表集合
     * @param n
     * @param m
     * @return
     */
    public static int LastRemaining_Solution2(int n, int m) {
        if(n == 0 || m <= 0) {
            return -1;
        }
        List<Integer> students = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            students.add(i);
        }
        int index = 0;
        while(students.size() > 1) {
            index = (index + m - 1) % students.size();
            students.remove(index);
        }
        return students.get(0);
    }

    /**
     * 约瑟夫环问题
     * @param n
     * @param m
     * @return
     */
    public static int LastRemaining_Solution3(int n, int m) {
        if(n == 0 || m <= 0) {
            return -1;
        }
        int last = 0;
        for(int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    public static void main(String[] args) {
        System.out.println(LastRemaining_Solution(4000, 977));
        System.out.println(LastRemaining_Solution2(4000, 977));
        System.out.println(LastRemaining_Solution3(4000, 977));
    }
}
