package SwordMeansOffer;

import java.util.Stack;

/**
 * Created by Special on 2018/7/18 9:10
 */
public class Pro28 {


    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 将二叉树搜索树转换为双向链表
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        constructDoublyLinkedList(pRootOfTree);
        while(pRootOfTree.left != null) {
            pRootOfTree = pRootOfTree.left;
        }
        return pRootOfTree;
    }

    /**
     * 对于每一个节点，对左右子树处理后进行连接
     * @param treeNode
     */
    public void constructDoublyLinkedList(TreeNode treeNode) {
        if(treeNode == null) {
            return;
        }
        if(treeNode.left == null && treeNode.right == null) {
            return;
        }
        constructDoublyLinkedList(treeNode.left);
        constructDoublyLinkedList(treeNode.right);
        TreeNode pre = findRightNodeOfLeftTree(treeNode.left);
        TreeNode after = findLeftNodeOfRightTree(treeNode.right);
        if(pre != null) {
            treeNode.left = pre;
            pre.right = treeNode;
        }
        if(after != null) {
            treeNode.right = after;
            after.left = treeNode;
        }
    }

    /**
     * 搜寻左子树中最右的节点
     * @param treeNode
     * @return
     */
    public TreeNode findRightNodeOfLeftTree(TreeNode treeNode) {
        if(treeNode == null) {
            return null;
        }
        while(treeNode.right != null) {
            treeNode = treeNode.right;
        }
        return treeNode;
    }

    /**
     * 搜寻右子树的最左的节点
     * @param treeNode
     * @return
     */
    public TreeNode findLeftNodeOfRightTree(TreeNode treeNode) {
        if(treeNode == null) {
            return null;
        }
        while(treeNode.left != null) {
            treeNode = treeNode.left;
        }
        return treeNode;
    }


    //---------------方法二----------------
    /**
     * 标识中序遍历已遍历的最后一个节点
     */
    static TreeNode lastNode;

    /**
     * 利用中序遍历的思想来将二叉排序树转化为双向链表
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert2(TreeNode pRootOfTree) {
        lastNode = null;
        constructDoublyLinkedList2(pRootOfTree, lastNode);
        while(pRootOfTree.left != null) {
            pRootOfTree = pRootOfTree.left;
        }
        return pRootOfTree;
    }

    /**
     * 中序遍历的变形
     * @param treeNode
     * @param lastNode
     */
    public void constructDoublyLinkedList2(TreeNode treeNode, TreeNode lastNode) {
        if(treeNode != null) {
            constructDoublyLinkedList2(treeNode.left, lastNode);
            if(lastNode != null) {
                lastNode.right = treeNode;
            }
            treeNode.left = lastNode;
            lastNode = treeNode;
            constructDoublyLinkedList2(treeNode.right, lastNode);
        }
    }

    //--------------方法3----------------

    /**
     * 非递归中序遍历的变形
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert3(TreeNode pRootOfTree) {
        if(pRootOfTree == null) {
            return null;
        }
        TreeNode head = null;
        TreeNode lastNode = null;
        Stack<TreeNode> stack = new Stack<>();
        while(pRootOfTree != null || !stack.isEmpty()) {
            while(pRootOfTree != null) {
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            TreeNode node = stack.pop();
            if(lastNode != null) {
                lastNode.right = node;
            }
            node.left = lastNode;
            lastNode = node;
            //第一访问的节点必为双向链表的第一个节点
            if(head == null) {
                head = node;
            }
            pRootOfTree = node.right;
        }
        return head;
    }
}
