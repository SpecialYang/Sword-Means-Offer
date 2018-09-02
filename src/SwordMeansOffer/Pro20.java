package SwordMeansOffer;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Special on 2018/6/28 21:46
 */
public class Pro20 {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 递归实现
     * 关键就在于把大问题转化为子问题即可
     * @param root
     */
    public static void Mirror1(TreeNode root) {
        if(root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            Mirror1(root.left);
            Mirror1(root.right);
        }
    }

    /**
     * 非递归实现, 借助栈或者列表遍历树中每一个非叶子节点
     * 交换其左右子树即可
     * @param root
     */
    public static void Mirror2(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
    }

    static String str;
    static int index;

    /**
     * 根据前序序列化构建二叉树
     * @return
     */
    public static TreeNode buildTree() {
        if(index >= str.length()) {
            return null;
        }
        char ch = str.charAt(index);
        if(ch == '#'){
            return null;
        }else {
            TreeNode node = new TreeNode(Integer.valueOf(String.valueOf(ch)));
            index++;
            node.left = buildTree();
            index++;
            node.right = buildTree();
            return node;
        }
    }

    static StringBuilder sb;

    /**
     * 前序遍历
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if(node != null) {
            sb.append(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }else {
            sb.append('#');
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            str = input.next();
            index = 0;
            TreeNode root = buildTree();
            sb = new StringBuilder();
            preOrder(root);
            System.out.println(sb.toString());
            Mirror1(root);
            sb.delete(0, sb.length());
            preOrder(root);
            System.out.println(sb.toString());
        }
    }
}
