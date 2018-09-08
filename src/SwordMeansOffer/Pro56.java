package SwordMeansOffer;

import java.util.Arrays;

/**
 * Created by Special on 2018/9/8 9:50
 */
public class Pro56 {

    //-------------方法一-------------
    //Java中采用双字节存储char类型，最多有2的15次方个
    static int MAX = 1 << 15;
    //记录字符出现的次数
    int[] visited = new int[MAX];
    //保持插入字符的顺序
    StringBuilder sb = new StringBuilder();

    public void Insert(char ch) {
        if(visited[ch] == 0) {
            sb.append(ch);
        }
        visited[ch]++;
    }

    public char FirstAppearingOnce() {
        for(int i = 0; i < sb.length(); i++) {
            if(visited[sb.charAt(i)] == 1) {
                return sb.charAt(i);
            }else if(visited[sb.charAt(i)] > 1){
                sb.delete(0, 1);
                i -= 1;
            }
        }
        return '#';
    }

    int[] count = new int[MAX];
    int index = 0;

    {
        Arrays.fill(count, -1);
    }

    //--------------方法二--------------
    public void Insert2(char ch) {
        if(count[ch] == -1) {
            count[ch] = (index++);
        }else if(count[ch] >= 0) {
            count[ch] = -2;
        }
    }

    public char FirstAppearingOnce2() {
        char ch = '#';
        int minIndex = Integer.MAX_VALUE;
        for(int i = 0; i < MAX; i++) {
            if(count[i] >= 0 && count[i] < minIndex) {
                ch = (char) i;
                minIndex = count[i];
            }
        }
        return ch;
    }

}
