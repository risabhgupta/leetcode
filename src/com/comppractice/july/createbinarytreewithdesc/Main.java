package com.comppractice.july.createbinarytreewithdesc;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().createBinaryTree(
                new int[][] { { 20, 15, 1 }, { 20, 17, 0 }, { 50, 20, 1 }, { 50, 80, 0 }, { 80, 19, 1 } }));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    boolean rootNode;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
        this.rootNode = true;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.rootNode = true;
    }
}

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> treeNodeMap = new HashMap<>();
        for (final int[] description : descriptions) {
            TreeNode treeNode = getNode(description[0], treeNodeMap);
            if (description[2] == 1) {
                treeNode.left = getNode(description[1], treeNodeMap);
                treeNode.left.rootNode = false;
            } else {
                treeNode.right = getNode(description[1], treeNodeMap);
                treeNode.right.rootNode = false;
            }
        }
        return treeNodeMap.values().stream().filter(treeNode -> treeNode.rootNode).findFirst().orElse(null);
    }

    public TreeNode getNode(int value, Map<Integer, TreeNode> treeNodeMap) {
        return treeNodeMap.computeIfAbsent(value, TreeNode::new);
    }
}
