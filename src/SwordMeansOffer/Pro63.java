package SwordMeansOffer;

import java.util.Stack;

/**
 * Created by SpecialYang on 2018/10/29 23:19.
 */
public class Pro63 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    //方法一：递归形式的先序遍历
    StringBuilder sb = new StringBuilder();

    String Serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return sb.toString();
    }

    /**
     * 递归形式的先序遍历
     * @param node
     */
    void dfs(TreeNode node) {
        if (node == null) {
            sb.append("#");
        } else {
            sb.append(node.val);
            sb.append("!");
            dfs(node.left);
            dfs(node.right);
        }
    }

    int index = 0;

    /**
     * 反序列化
     * @param str
     * @return
     */
    TreeNode Deserialize(String str) {
        if (str == null || index >= str.length()) {
            return null;
        }
        //当前节点为空
        if (str.charAt(index) == '#') {
            index++;
            return null;
        }
        int val = 0;
        //截取当前到！之前构造节点值
        while (str.charAt(index) != '!') {
            val = val * 10 + str.charAt(index++) - '0';
        }
        TreeNode node = new TreeNode(val);
        index++;
        //生成左子树
        node.left = Deserialize(str);
        //生成右子树
        node.right = Deserialize(str);
        return node;
    }

    //方法二-------非递归形式的先序遍历
    String Serialize1(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                sb.append("#");
            } else {
                sb.append(node.val);
                sb.append("!");
                stack.push(node.right);
                stack.push(node.left);
            }

        }
        return sb.toString();
    }

}
