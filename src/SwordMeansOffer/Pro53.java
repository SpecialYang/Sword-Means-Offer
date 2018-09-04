package SwordMeansOffer;

/**
 * Created by Special on 2018/9/4 17:43
 */
public class Pro53 {

    /**
     * B[i] = left[i - 1] * right[i + 1]
     * @param A
     * @return
     */
    public static int[] multiply(int[] A) {
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        int[] B = new int[A.length];
        int result = 1;
        //初始化左部分连乘
        for(int i = 0; i < A.length; i++) {
            left[i] = (result *= A[i]);
        }
        result = 1;
        //初始化右部分连乘
        for(int i = A.length - 1; i >= 0; i--) {
            right[i] = (result *= A[i]);
        }
        for(int i = 0; i < A.length; i++) {
            B[i] = i == 0 ? 1 : left[i - 1];
            B[i] *= i == A.length - 1 ? 1 : right[i + 1];
        }
        return B;
    }

    /**
     * 简化版
     * @param A
     * @return
     */
    public static int[] multiply2(int[] A) {
        int[] B = new int[A.length];
        B[0] = 1;
        for(int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int result = 1;
        for(int i = A.length - 2; i >= 0; i--) {
            result *= A[i + 1];
            B[i] *= result;
        }
        return B;
    }

    public static void main(String[] args) {
        int[] num = {1,2,3,4,5};
        int[] B = multiply2(num);
        for(int i : B) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
