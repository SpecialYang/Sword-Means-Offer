package SwordMeansOffer;

import java.util.Stack;

/**
 * Created by SpecialYang on 2018/10/31 07:25.
 */
public class Pro64 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 非递归版中序遍历
     * @param pRoot
     * @param k
     * @return
     */
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = pRoot;
        while(!stack.isEmpty() || p != null) {
            while(p != null) {
                stack.push(p);
                p = p.left;
            }
            TreeNode node = stack.pop();
            if ((--k) == 0) {
                return node;
            }
            p = node.right;
        }
        return null;
    }

    TreeNode kThNode = null;
    /**
     * 递归版中序遍历
     * @param pRoot
     * @param k
     * @return
     */
    TreeNode KthNode1(TreeNode pRoot, int k) {
        if (pRoot == null) {
            return null;
        }
        int[] count = new int[] {k};
        KthNode(pRoot, count);
        return kThNode;
    }

    void KthNode(TreeNode node, int[] count) {
        if (count[0] != 0 && node != null) {
            KthNode(node.left, count);
            if (--count[0] == 0) {
                kThNode = node;
                return;
            }
            KthNode(node.right, count);
        }
    }

}
