package com.comppractice.september.linkedlistinbintree;

import java.util.HashSet;
import java.util.Set;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        return traverse(root, head, head, new HashSet<>());
    }

    public boolean traverse(TreeNode treeNode, ListNode listNode, ListNode head, Set<TreeNode> visited) {
        if (listNode == head) {
            if (visited.contains(treeNode)) {
                System.out.println(treeNode.val);
                return false;
            }
            visited.add(treeNode);
        }

        if (listNode == null) {
            return true;
        }
        if (treeNode == null) {
            return false;
        }

        return ((listNode.val == treeNode.val) && (traverse(treeNode.left, listNode.next, head, visited) || traverse(treeNode.right, listNode.next, head, visited)))
                || traverse(treeNode.left, head, head, visited)
                || traverse(treeNode.right, head, head, visited);

    }
}