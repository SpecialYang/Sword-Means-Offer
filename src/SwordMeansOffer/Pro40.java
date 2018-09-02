package SwordMeansOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Special on 2018/8/12 23:51
 */
public class Pro40 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 广度优先遍历
     * @param root
     * @return
     */
    public static int TreeDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int currentLayerSize = queue.size();
            depth++;
            for(int i = 0; i < currentLayerSize; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    public static int TreeDepth3(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        TreeNode last = root, nLast = null;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left != null) {
                queue.offer(node.left);
                nLast = node.left;
            }
            if(node.right != null) {
                queue.offer(node.right);
                nLast = node.right;
            }
            if(node == last) {
                depth++;
                last = nLast;
            }
        }
        return depth;
    }

    public static int TreeDepth2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return dfs(root, 1);
    }

    public static int dfs(TreeNode node, int depth) {
        if(node.left == null && node.right == null) {
            return depth;
        }
        int result = 0;
        if(node.left != null) {
            result = Math.max(result, dfs(node.left, depth + 1));
        }
        if(node.right != null) {
            result = Math.max(result, dfs(node.right, depth + 1));
        }
        return result;
    }

    public static int TreeDepth4(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = TreeDepth4(root.left);
        int right = TreeDepth4(root.right);
        return Math.max(left, right) + 1;
    }
}
