package SwordMeansOffer;

import java.util.List;
import java.util.Stack;

/**
 * Created by SpecialYang on 2018/10/20 11:34.
 */
public class Pro59 {


    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    //方法一：纯递归中序遍历二叉树
    static List<TreeLinkNode> lists = null;

    public TreeLinkNode GetNext1(TreeLinkNode pNode) {
        TreeLinkNode root = getRootNode(pNode);
        inorderTraversal(root);
        int index = 0;
        for(int i = 0; i < lists.size(); i++) {
            if (lists.get(i) == pNode) {
                index = i + 1;
                break;
            }
        }
        return index < lists.size() ? lists.get(index) : null;
    }

    public TreeLinkNode getRootNode(TreeLinkNode pNode) {
        while(pNode != null) {
            if(pNode.next == null) {
                return pNode;
            }
            pNode = pNode.next;
        }
        return null;
    }

    public void inorderTraversal(TreeLinkNode node) {
        if(node != null) {
            inorderTraversal(node.left);
            lists.add(node);
            inorderTraversal(node.right);
        }
    }

    //方法二：变形的中序遍历，可中断
    static TreeLinkNode nextNode = null;
    static boolean flag = false;
    static boolean findFlag = false;

    public TreeLinkNode GetNext2(TreeLinkNode pNode) {
        TreeLinkNode root = getRootNode(pNode);
        nextNode = null;
        flag = false;
        findFlag = false;
        findNextNode1(root, pNode);
        return nextNode;
    }

    public void findNextNode1(TreeLinkNode node, TreeLinkNode pNode) {
        if(node != null && !findFlag) {
            findNextNode1(node.left, pNode);
            if(flag) {
                nextNode = node;
                findFlag = true;
            } else if (node == pNode) {
                flag = true;
            }
            findNextNode1(node.right, pNode);
        }
    }

    /**
     * 非递归中序遍历变形
     * @param node
     * @param pNode
     */
    public void findNextNode2(TreeLinkNode node, TreeLinkNode pNode) {
        Stack<TreeLinkNode> stack = new Stack<>();
        TreeLinkNode p = node;
        boolean flag = false;
        while(!stack.isEmpty() || p != null) {
            while(p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if(flag) {
                nextNode = p;
                break;
            }
            if(p == pNode) {
                flag = true;
            }
            p = p.right;
        }
    }

    //方法三-直接利用中序遍历的下一个节点的特点
    public TreeLinkNode GetNext3(TreeLinkNode pNode) {
        if(pNode == null) {
            return null;
        }
        if(pNode.right != null) {
            pNode = pNode.right;
            while(pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        } else {
            while(pNode.next != null) {
                if(pNode.next.left == pNode) {
                    return pNode.next;
                }
                pNode = pNode.next;
            }
        }
        return null;
    }

}
