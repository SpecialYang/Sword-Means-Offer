package SwordMeansOffer;

/**
 * Created by Special on 2018/8/24 11:33
 */
public class Pro49 {

    /**
     * 利用短路的逻辑与
     * @param n
     * @return
     */
    public static int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (n != 0 && (sum += Sum_Solution(n - 1)) >= 0);
        return sum;
    }

    /**
     * 短路的逻辑或
     * @param n
     * @return
     */
    public static int Sum_Solution2(int n) {
        int sum = n;
        boolean flag = (n == 0 || (sum += Sum_Solution2(n - 1)) >= 0);
        return sum;
    }

    /**
     * Sn = (1/2)n^2 + (1/2)n
     * @param n
     * @return
     */
    public static int Sum_Solution3(int n) {
        return ((int)Math.pow(n, 2) + n) >> 1;
    }

    /**
     * 快速模乘
     * @param n
     * @return
     */
    static int count;

    public static int Sum_Solution4(int n) {
        int a = n, b = n + 1;
        count = 0;
        return sum(a, b) >> 1;
    }

    static int sum(int a, int b) {
        int sum = 0;
        boolean flag = ((b & 1) != 0) && (sum += (a << count)) > 0;
        count++;
        boolean flag1 = (b != 0) && (sum += sum(a, b >> 1)) > 0;
        return sum;
    }

    public static int Sum_Solution5(int n) {
        int a = n, b = n + 1;
        return sumAToB(a, b) >> 1;
    }

    static int sumAToB(int a, int b) {
        int sum = 0;
        boolean flag1 = ((b & 1) != 0) && (sum += a) > 0;
        boolean flag2 = ((b != 0)) && (sum += sumAToB(a << 1, b >> 1)) > 0;
        return sum;
    }


    public static void main(String[] args) {
//        System.out.println(Sum_Solution(100));
//        System.out.println(Sum_Solution2(100));
//        System.out.println(Sum_Solution3(1));
//        System.out.println(Sum_Solution4(4));
        System.out.println(Sum_Solution4(5));
//        System.out.println(Sum_Solution4(6));
        System.out.println(Sum_Solution4(100));
        System.out.println(Sum_Solution4(10000));
        System.out.println(Sum_Solution5(10000));
        System.out.println(Sum_Solution(10000));
    }
}
