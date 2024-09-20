package com.comppractice.july.goodleafnodepair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {
    public static void main(String[] args) {
        TreeNode root = createTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        System.out.println(new Solution().countPairs(root, 3));
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
    public int countPairs(TreeNode root, int distance) {
        List<String> leafNodePath = new ArrayList<>();
        traverse(root, leafNodePath, new StringBuilder());

        int count = 0;

        for (int i = 0; i < leafNodePath.size(); i++) {
            for (int j = i + 1; j < leafNodePath.size(); j++) {
                int distanceBetweenTwo = getDistance(leafNodePath.get(i), leafNodePath.get(j));
                if (distanceBetweenTwo <= distance) {
                    count++;
                }
            }
        }
        return count;
    }

    private int getDistance(String start, String end) {
        int commonPoint = 0;
        for (int i = 0; i < Math.min(start.length(), end.length()); i++) {
            if (start.charAt(i) == end.charAt(i)) {
                commonPoint++;
            } else {
                break;
            }
        }
        return start.length() + end.length() - (2 * commonPoint);
    }

    public void traverse(TreeNode node, List<String> leafNodePath, StringBuilder path) {

        if (node.left == null && node.right == null) {
            leafNodePath.add(path.toString());
        }

        if (node.left != null) {
            path.append("L");
            traverse(node.left, leafNodePath, path);
            path.deleteCharAt(path.length() - 1);
        }

        if (node.right != null) {
            path.append("R");
            traverse(node.right, leafNodePath, path);
            path.deleteCharAt(path.length() - 1);
        }

    }
}
