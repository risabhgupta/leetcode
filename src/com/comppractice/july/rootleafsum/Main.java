package com.comppractice.july.rootleafsum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class Main {

    public static void main(String[] args) {
        TreeNode root = createTree(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 });
        System.out.println(new Solution().pathSum(root, 22));
    }

    public static TreeNode createTree(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.add(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        traverseAndAdd(root, targetSum, result, new Stack<>());
        return result;
    }

    private void traverseAndAdd(TreeNode node, int targetSum, List<List<Integer>> result, Stack<Integer> path) {
        path.push(node.val);
        targetSum -= node.val;
        if (node.left == null && node.right == null && targetSum == 0) {
            result.add(new ArrayList<>(path));
        } else {
            if (node.left != null) {
                traverseAndAdd(node.left, targetSum, result, path);
            }

            if (node.right != null) {
                traverseAndAdd(node.right, targetSum, result, path);
            }
        }
        path.pop();
    }
}