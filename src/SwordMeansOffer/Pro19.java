package SwordMeansOffer;

/**
 * Created by Special on 2018/6/28 8:31
 */
public class Pro19 {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 在树1中寻找与树2根节点相同的节点
     * @param root1
     * @param root2
     * @return
     */
    public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean result = false;
        if(root1 != null && root2 != null) {
            if(root1.val == root2.val) {
                result = doesTree1HaveTree2(root1, root2);
            }
            if(!result) {
                result = HasSubtree(root1.left, root2);
            }
            if(!result) {
                result = HasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    /**
     * 判断以root1为根的子树是否与以root2为根的子树相同
     * @param root1
     * @param root2
     * @return
     */
    public static boolean doesTree1HaveTree2(TreeNode root1, TreeNode root2) {
        if(root2 == null) {
            return true;
        }
        if(root1 == null) {
            return false;
        }
        if(root1.val != root2.val) {
            return false;
        }
        return doesTree1HaveTree2(root1.left, root2.left) &&
                doesTree1HaveTree2(root2.right, root2.right);
    }

}
