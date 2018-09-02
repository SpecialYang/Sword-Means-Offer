package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/7/7 0:14
 */
public class Pro25 {

    /**
     * 主函数
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0) {
            return false;
        }
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    /**
     * 递归判断当前树结构的后序遍历是否满足二叉搜索
     * @param sequence
     * @param start
     * @param root
     * @return
     */
    public static boolean VerifySquenceOfBST(int[] sequence, int start, int root) {
        if(start >= root) {
            return true;
        }
        int indexOfRightRoot = start;
        //找到右子树的根节点
        while(indexOfRightRoot < root && sequence[indexOfRightRoot] <= sequence[root]) {
            indexOfRightRoot++;
        }
        //判断右子树是否全部大于根节点
        for(int i = indexOfRightRoot; i < root; i++) {
            if(sequence[i] < sequence[root]) {
                return false;
            }
        }
        //递归判断左右子树
        return VerifySquenceOfBST(sequence, start, indexOfRightRoot - 1)
                && VerifySquenceOfBST(sequence, indexOfRightRoot, root - 1);
    }

    /**
     * 非递归实现
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBST2(int [] sequence) {
        if(sequence == null || sequence.length == 0) {
            return false;
        }
        int index = 0;
        int size = sequence.length;
        while(--size > 0) {
            //找右子树的第一个节点
            while(sequence[index] < sequence[size]) {
                index++;
            }
            //判断右子树是否满足规定
            while(index < size && sequence[index++] > sequence[size]);
            //若index小于size,说明右子树有个值小于根节点，说明不合法
            if(index < size) {
                return false;
            }
            index = 0;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] sequence = new int[n];
            for(int i = 0; i < n; i++) {
                sequence[i] = input.nextInt();
            }
            System.out.println(VerifySquenceOfBST(sequence));
        }
    }
}
