package com.comppractice.july.deletenodesforest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = new HashSet<>();
        Set<TreeNode> disjointNodes = new HashSet<>();
        disjointNodes.add(root);
        for (final int j : to_delete) {
            toDelete.add(j);
        }

        traverseAndDelete(null, root, toDelete, disjointNodes);
        disjointNodes.remove(null);

        return new ArrayList<>(disjointNodes);
    }

    private void traverseAndDelete(TreeNode parentNode, TreeNode node, Set<Integer> toDelete,
            Set<TreeNode> disjointNodes) {
        if (node != null) {
            traverseAndDelete(node, node.left, toDelete, disjointNodes);
            traverseAndDelete(node, node.right, toDelete, disjointNodes);
            if (toDelete.contains(node.val)) {
                if (parentNode != null) {
                    if (parentNode.left == node) {
                        disjointNodes.remove(node);
                        parentNode.left = null;
                        disjointNodes.add(node.left);
                        disjointNodes.add(node.right);

                    } else {
                        disjointNodes.remove(node);
                        parentNode.right = null;
                        disjointNodes.add(node.left);
                        disjointNodes.add(node.right);
                    }
                } else {
                    disjointNodes.remove(node);
                    disjointNodes.add(node.left);
                    disjointNodes.add(node.right);
                }
            }
        }

    }

}