package com.comppractice.july.flattenbinarytree;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        new Solution().flatten(root);

        TreeNode rootNode = root;
        while (rootNode != null) {
            System.out.print(rootNode.val + "->");
            rootNode = rootNode.right;
        }
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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            flatten(root.left);
        }
        if (root.right != null) {
            flatten(root.right);
        }

        if (root.left != null) {
            TreeNode rightNode = root.right;
            root.right = root.left;
            TreeNode toBeRight = root.right;
            while (toBeRight.right != null) {
                toBeRight = toBeRight.right;
            }
            toBeRight.right = rightNode;
            root.left = null;
        }
    }
}