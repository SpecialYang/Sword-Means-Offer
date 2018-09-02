package SwordMeansOffer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Special on 2018/7/8 17:25
 */
public class Pro26 {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }


    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(root.val);
        FindPath(result, temp, root.val, root, target);
        return result;
    }

    /**
     * dfs遍历方式，我们的根节点已经在遍历前加入
     * @param result 最后的结果
     * @param temp   当前的路径
     * @param sum    当前的和
     * @param node   路径中最后一个节点
     * @param target 目标值
     */
    private static void FindPath(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int sum, TreeNode node, int target) {
        /*
            判断是否该路径中最后一个节点是否是叶子节点
            若是，判断该路径的和是否等于target
            若等于，把该路径加入路径集合
         */
        if(node.left == null && node.right == null && sum == target) {
            ArrayList<Integer> path = new ArrayList<>();
            path.addAll(temp);
            result.add(new ArrayList<>(temp));
            return;
        }
        //遍历该点的左子树
        if(node.left != null) {
            temp.add(node.left.val);
            FindPath(result, temp, sum + node.left.val, node.left, target);
            //回溯时，记得删除之前加入的点
            temp.remove(temp.size() - 1);
        }
        //遍历该点的右子树
        if(node.right != null) {
            temp.add(node.right.val);
            FindPath(result, temp, sum + node.right.val, node.right, target);
            //回溯时，记得删除之前加入的点
            temp.remove(temp.size() - 1);
        }
    }

    public static ArrayList<ArrayList<Integer>> FindPath2(TreeNode root,int target) {
        if(root == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        FindPath(result, temp, 0, root, target);
        return result;
    }

    /**
     * 利用二叉树先序遍历的知识来做
     * 每次都对根节点处理
     * @param result
     * @param temp
     * @param sum
     * @param node
     * @param target
     */
    private static void FindPath2(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int sum, TreeNode node, int target) {
        temp.add(node.val);
        sum += node.val;
        if(node.left == null && node.right == null && sum == target) {
            ArrayList<Integer> path = new ArrayList<>();
            path.addAll(temp);
            result.add(new ArrayList<>(temp));
            return;
        }
        //遍历该点的左子树
        if(node.left != null) {
            FindPath2(result, temp, sum, node.left, target);
        }
        //遍历该点的右子树
        if(node.right != null) {
            FindPath2(result, temp, sum, node.right, target);
        }
        temp.remove(temp.size() - 1);
    }

    /**
     * 非递归实现，利用last保留为上一次遍历的结点。
     * 因为回溯时，右子树会回溯到父节点上，所以通过last来确定右子树是否已经访问
     * @param root
     * @param target
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindPath3(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        TreeNode cur = root, last = null;
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        while(cur != null || !stack.isEmpty()) {
            if(cur == null) {
                TreeNode top = stack.peek();
                if(top.right != null && top.right != last) {
                    cur = top.right;
                } else {
                    last = top;
                    stack.pop();
                    temp.remove(temp.size() - 1);
                    sum -= last.val;
                }
            }else {
                stack.push(cur);
                temp.add(cur.val);
                sum += cur.val;
                if(cur.left == null && cur.right == null && sum == target) {
                    result.add(new ArrayList<>(temp));
                }
                cur = cur.left;
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            str = input.next();
            index = 0;
            TreeNode root = buildTree();
            int target = input.nextInt();
            ArrayList<ArrayList<Integer>> result = FindPath(root, target);
            for(ArrayList<Integer> item : result) {
                System.out.println(item.toString());
            }
            System.out.println();
        }
    }
}
