package SwordMeansOffer;

/**
 * Created by Special on 2018/8/13 22:17
 */
public class Pro41 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int TreeDepth(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = TreeDepth(node.left);
        int right = TreeDepth(node.right);
        return Math.max(left, right) + 1;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null) {
            return true;
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right)
                && Math.abs(TreeDepth(root.right) - TreeDepth(root.left)) < 2;
    }

    static boolean isBalance = true;
    public static boolean IsBalanced_Solution2(TreeNode root) {
        if(root == null) {
            return true;
        }
        isBalance = true;
        caculateBalanceTree(root);
        return isBalance;
    }

    private static int caculateBalanceTree(TreeNode node) {
        if(node == null || !isBalance) {
            return 0;
        }
        int left = caculateBalanceTree(node.left);
        int right = caculateBalanceTree(node.right);
        isBalance = Math.abs(left - right) < 2 ? true : false;
        return Math.max(left, right) + 1;
    }

    public static class DepthHolder {
        int depth;
    }

    public static boolean IsBalanced_Solution3(TreeNode root) {
        if(root == null) {
            return true;
        }
        DepthHolder depthHolder = new DepthHolder();
        return isBalance(root, depthHolder);
    }

    private static boolean isBalance(TreeNode node, DepthHolder depthHolder) {
        if(node == null) {
            depthHolder.depth = 0;
            return true;
        }
        DepthHolder leftDepthHolder = new DepthHolder(), rightDepthHoder = new DepthHolder();
        if(isBalance(node.left, leftDepthHolder) && isBalance(node.right, rightDepthHoder)) {
            if(Math.abs(leftDepthHolder.depth - rightDepthHoder.depth) < 2) {
                depthHolder.depth = Math.max(leftDepthHolder.depth, rightDepthHoder.depth) + 1;
                return true;
            }
        }
        return false;
    }
}
