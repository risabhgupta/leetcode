package com.comppractice.august.cousinsinthetree;


import java.util.LinkedList;
import java.util.Queue;

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
    public TreeNode replaceValueInTree(TreeNode root) {
        TreeNode newRoot = new TreeNode();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            int val = 0;
            if (currNode.left != null) {
                val += currNode.left.val;
                queue.offer(currNode.left);
            }
            if (currNode.right != null) {
                val += currNode.right.val;
                queue.offer(currNode.right);
            }
            currNode.val = val;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            int leftVal = currNode.left == null ? 0 : currNode.left.val;
            int rightVal = currNode.right == null ? 0 : currNode.right.val;

            if (currNode.right != null) {
                currNode.right.val = leftVal;
                queue.offer(currNode.right);
            }
            if (currNode.left != null) {
                currNode.left.val = rightVal;
                queue.offer(currNode.left);
            }
        }

        root.val = 0;
        adjustTree(root, 0);

        return root;

    }

    private void adjustTree(TreeNode root, int i) {
        if (root.left != null) {
            adjustTree(root.left, root.val);
        }
        if (root.right != null) {
            adjustTree(root.right, root.val);
        }
        root.val = i;
    }
}