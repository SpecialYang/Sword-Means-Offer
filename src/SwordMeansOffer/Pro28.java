package SwordMeansOffer;

import java.util.Stack;

/**
 * Created by Special on 2018/7/18 9:10
 *
 * 搜索树与双向链表
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

    //---2019.02.20 update 对解法一有了新的认识

    /**
     * 分解问题，解决当前问题，必先解决子问题
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert4(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        pRootOfTree = process(pRootOfTree);
        //找到双向链表最开始
        while (pRootOfTree.left != null) {
            pRootOfTree = pRootOfTree.left;
        }
        return pRootOfTree;
    }

    private TreeNode process(TreeNode node) {
        if (node == null) {
            return null;
        }
        //处理左子树
        TreeNode left = process(node.left);
        //处理右子树
        TreeNode right = process(node.right);
        //找到左子树的最后一个节点
        while (left != null && left.right != null) {
            left = left.right;
        }
        //拼接左子树
        if (left != null) {
            left.right = node;
            node.left = left;
        }
        //找到右子树的最开始的一个节点
        while (right != null && right.left != null) {
            right = right.left;
        }
        //连接右子树
        if (right != null) {
            node.right = right;
            right.left = node;
        }
        //返回当前节点
        return node;
    }

    /**
     * 牛逼的解法
     *
     * 直接从底层返回左子树和右子树的构成双向链表的开头和结尾
     * 避免每次都遍历
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert5(TreeNode pRootOfTree) {
        return process2(pRootOfTree)[0];
    }

    private TreeNode[] process2(TreeNode node) {
        if (node == null) {
            return new TreeNode[]{null, null};
        }
        //处理左子树
        TreeNode[] leftHeads = process2(node.left);
        //处理右子树
        TreeNode[] rightHeads = process2(node.right);
        if (leftHeads[1] != null) {
            leftHeads[1].right = node;
            node.left = leftHeads[1].right;
        }
        if (rightHeads[0] != null) {
            rightHeads[0].left = node;
            node.right = rightHeads[0];
        }
        TreeNode head = leftHeads[0] != null ? leftHeads[0] : node;
        TreeNode tail = rightHeads[1] != null ? rightHeads[1] : node;
        return new TreeNode[]{head, tail};
    }
}
