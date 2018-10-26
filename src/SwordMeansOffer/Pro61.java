package SwordMeansOffer;

import java.util.*;

/**
 * Created by SpecialYang on 2018/10/25 23:29.
 */
public class Pro61 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        //表示第几层，决定每一行节点的方向
        int index = 0;
        while(!queue.isEmpty()) {
            index++;
            //获取当前层节点的个数
            int length = queue.size();
            //依次弹出当前层，并用list保存当前层的节点
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                row.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            //若是偶数行，则反序当前层节点
            if ((index & 1) == 0) {
                Collections.reverse(row);
            }
            result.add(row);
        }
        return result;
    }
}
