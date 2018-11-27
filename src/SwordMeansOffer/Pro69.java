package SwordMeansOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by SpecialYang on 2018/11/24 22:03.
 */
public class Pro69 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class BinarySearchTree {
        //跟节点
        TreeNode root;
        //构造序列的索引，用于判断是否已经遍历完毕
        int index;
        //构造序列
        List<Integer> values;

        public BinarySearchTree(List<Integer> values) {
            this.values = values;
        }

        /**
         * 构造二叉搜索树
         * @return
         */
        public TreeNode buildBinarySearchTree() {
            root = new TreeNode(values.get(index++));
            while (index < values.size()) {
                buildBinarySearchTree(root);
                index++;
            }
            return root;
        }

        /**
         * 构造二叉搜索树的helper函数，先创建跟节点的目的
         * 是为了便于少一次判断
         * @param root
         */
        private void buildBinarySearchTree(TreeNode root) {
            int value = values.get(index);
            TreeNode treeNode = new TreeNode(value);
            TreeNode p = root;
            TreeNode q = p;
            //保留父节点，是为了便于插入
            while (p != null) {
                q = p;
                p = p.val > value ? p.left : p.right;
            }
            if (q.val > value) {
                q.left = treeNode;
            } else {
                q.right = treeNode;
            }
        }

        /**
         * 非递归中序遍历，为了检验构造的树是否正确
         * @return
         */
        public List<Integer> InOrderTraverse() {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = root;
            while (p != null || !stack.isEmpty()) {
                while(p != null) {
                    stack.push(p);
                    p = p.left;
                }
                p = stack.pop();
                result.add(p.val);
                p = p.right;
            }
            return result;
        }
    }

    public boolean getPathOfTreeNode(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        if (root == target) {
            path.add(target);
            return true;
        }
        path.add(root);
        if (!(getPathOfTreeNode(root.left, target, path)
        || getPathOfTreeNode(root.right, target, path))) {
            path.remove(path.size() - 1);
            return false;
        }
        return true;
    }

    public TreeNode getLastCommonTreeNode(List<TreeNode> path1, List<TreeNode> path2) {
        int index1 = 0;
        int index2 = 0;
        TreeNode result = null;
        while (index1 < path1.size() && index2 < path2.size()) {
            if (path1.get(index1) != path2.get(index2)) {
                return result;
            }
            result = path1.get(index1);
            index1++;
            index2++;
        }
        return result;
    }

    /**
     * 普通树的最近的公共祖父节点查找
     * @param root
     * @param target1
     * @param target2
     * @return
     */
    public TreeNode getLastCommonTreeNodeOfSimpleTree(TreeNode root, TreeNode target1, TreeNode target2) {
        if (root == null || target1 == null || target2 == null) {
            return null;
        }
        List<TreeNode> path1 = new ArrayList<>();
        getPathOfTreeNode(root, target1, path1);
        List<TreeNode> path2 = new ArrayList<>();
        getPathOfTreeNode(root, target2, path2);
        return (path1.isEmpty() || path2.isEmpty())
                ? null : getLastCommonTreeNode(path1, path2);
    }

    /**
     * 二叉搜索树的最近公共父节点查找
     * @param root
     * @param target1
     * @param target2
     * @return
     */
    public TreeNode getLastCommonTreeNodeOfBinarySearchTree(TreeNode root, TreeNode target1, TreeNode target2) {
       if (target1.val < root.val && target2.val < root.val) {
           return getLastCommonTreeNodeOfBinarySearchTree(root.left, target1, target2);
       } else if (target1.val > root.val && target2.val > root.val) {
           return getLastCommonTreeNodeOfBinarySearchTree(root.right, target1, target2);
       } else {
           return root;
       }
    }

    public static void main(String[] args) {
        Pro69 pro69 = new Pro69();
        List<Integer> values = new ArrayList<>();
        values.add(5);
        values.add(1);
        values.add(4);
        values.add(6);
        values.add(2);
        values.add(7);
        values.add(9);
        values.add(0);
        BinarySearchTree binarySearchTree = pro69.new BinarySearchTree(values);
        TreeNode root = binarySearchTree.buildBinarySearchTree();
        List<Integer> result = binarySearchTree.InOrderTraverse();
        for (Integer item : result) {
            System.out.print(item + " ");
        }
        System.out.println();

        TreeNode commonTreeNode = pro69.getLastCommonTreeNodeOfBinarySearchTree(root, root.left.right.left, root.left.left);
        System.out.println(commonTreeNode.val);
    }
}
