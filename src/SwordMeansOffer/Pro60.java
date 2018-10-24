package SwordMeansOffer;

/**
 * Created by SpecialYang on 2018/10/24 23:34.
 */
public class Pro60 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        //考察左子树和右子树是否对称的
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode node1, TreeNode node2) {
        //递归结束条件，左子树的节点和右子树的节点都已遍历到叶子节点
        if (node1 == null && node2 == null) {
            return true;
        }
        //判断当前遍历到的节点的值是否相同
        if (node1 != null && node2 != null
                && node1.val == node2.val) {
            /**
             * 若相同，则继续考察节点1的左孩子和节点2的右孩子是否相同
             * 节点1的右孩子和节点2的左孩子是否相同
             */
            return isSymmetrical(node1.left, node2.right) &&
                    isSymmetrical(node1.right, node2.left);
        }
        /**
         * 当前节点的值不相同，返回false，上层函数由于&&的短路特性
         * 则不再递归，直到回溯到最开始
         */
        return false;
    }


}
