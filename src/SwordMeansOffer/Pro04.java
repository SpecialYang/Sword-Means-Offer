package SwordMeansOffer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 根据前序和中序重建二叉树
 * Created by Special on 2018/6/9 14:14
 */
public class Pro04 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //标识前序数组中位置
    static int index;

    public static TreeNode reConstructBinaryTree1(int [] pre,int [] in) {
        index = 0;
        int length = pre.length;
        return reConstructBinaryTree(pre, in, 0, length - 1);
    }

    /**
     * 递归版实现重建二叉树
     * @param pre
     * @param in
     * @param s 重建区间的开始位置，inclusive
     * @param e 重建区间的结束位置，inclusive
     * @return
     */
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in, int s, int e) {
        TreeNode treeNode = null;
        if(s <= e) {
            treeNode = new TreeNode(pre[index]);
            //看看当前的子树的根节点在中序的哪个位置
            int i = getiByVal(pre[index++], in, s, e);
            //递归子树根节点的左子树
            treeNode.left = reConstructBinaryTree(pre, in, s, i - 1);
            //递归子树根节点的右子树
            treeNode.right = reConstructBinaryTree(pre, in, i + 1, e);
        }
        return treeNode;
    }

    /**
     * 获得根节点在中序遍历的位置
     * 复杂度为线性，当然你可以利用哈希
     * @param target
     * @param in
     * @return
     */
    private static int getiByVal(int target, int[] in, int s, int e) {
        for(int i = s; i <= e; i++) {
            if(target == in[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 改良做法
     * @param pre
     * @param in
     * @return
     */
    public static TreeNode reConstructBinaryTree2(int [] pre,int [] in) {
        if(pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode treeNode = new TreeNode(pre[0]);
        for(int i = 0; i < in.length; i++) {
            if(pre[0] == in[i]) {
                 treeNode.left = reConstructBinaryTree2(Arrays.copyOfRange(pre, 1, i + 1),
                                                        Arrays.copyOfRange(in, 0, i));
                treeNode.right = reConstructBinaryTree2(Arrays.copyOfRange(pre, i + 1, pre.length),
                                                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return treeNode;
    }
    /**
     * 递归版的前序遍历二叉树
     * 用于检验构建的树是否正确
     * @param treeNode
     */
    static void preTraverse (TreeNode treeNode) {
        if(treeNode != null) {
            System.out.print(treeNode.val + " ");
            preTraverse(treeNode.left);
            preTraverse(treeNode.right);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            int n = input.nextInt();
            int[] pre = new int[n];
            int[] in = new int[n];
            for(int i = 0; i < n; i++) {
                pre[i] = input.nextInt();
            }
            for(int i = 0; i < n; i++) {
                in[i] = input.nextInt();
            }
            TreeNode root = reConstructBinaryTree1(pre, in);
            preTraverse(root);
            System.out.println();
            root = reConstructBinaryTree2(pre, in);
            preTraverse(root);
            System.out.println();
        }
    }
}
