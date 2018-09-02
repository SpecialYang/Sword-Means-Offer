package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/7/24 20:24
 */
public class Pro33 {

    /**
     * 拼接字符串，然后遍历，求1的个数
     * @param n
     * @return
     */
    public static int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for(int i = 1; i <= n; i++) {
            String num = String.valueOf(i);
            for (int j = 0; i < num.length(); i++) {
                count += num.charAt(i) == '1' ? 1 : 0;
            }
        }
        return count;
    }


    public static int NumberOf1Between1AndN_Solution2(int n) {
        int count = 0;
        for(int i = 1; i <= n; i++) {
            count += NumberOfOne(i);
        }
        return count;
    }

    /**
     * 遍历数的每一位，统计1的个数
     * @param num
     * @return
     */
    public static int NumberOfOne(int num) {
        int count = 0;
        while(num != 0) {
            if(num % 10 == 1) {
                count++;
            }
            num /= 10;
        }
        return count;
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int num = input.nextInt();
            System.out.println(NumberOf1Between1AndN_Solution2(num));
        }
    }
}
