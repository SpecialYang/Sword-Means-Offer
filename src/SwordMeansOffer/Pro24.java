package SwordMeansOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Special on 2018/7/5 22:02
 */
public class Pro24 {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if(root == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return result;
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
            ArrayList<Integer> result = PrintFromTopToBottom(root);
            for(int item : result) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
