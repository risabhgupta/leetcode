package com.comppractice.july.stepbystepdirection;

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
    public String getDirections(TreeNode root, int startValue, int destValue) {
        final String[] strings = { null, null };
        searchString(root, startValue, destValue, strings, new StringBuilder());
        StringBuilder startString = new StringBuilder(strings[0]);
        StringBuilder destinationString = new StringBuilder(strings[1]);

        while (startString.length() > 0 && destinationString.length() > 0 && (startString.charAt(0)
                == destinationString.charAt(0))) {
            startString.deleteCharAt(0);
            destinationString.deleteCharAt(0);
        }

        StringBuilder shortestPath = new StringBuilder();
        //Move to a common point
        for (int i = 0; i < startString.length(); i++) {
            shortestPath.append('U');
        }
        shortestPath.append(destinationString);
        return shortestPath.toString();
    }

    private void searchString(TreeNode parent, int startValue, int destValue, String[] strings,
            StringBuilder pathBuilder) {
        if (parent.val == startValue) {
            strings[0] = pathBuilder.toString();
        } else if (parent.val == destValue) {
            strings[1] = pathBuilder.toString();
        }

        if (strings[0] != null && strings[1] != null) {
            return;
        }

        if (parent.left != null) {
            pathBuilder.append("L");
            searchString(parent.left, startValue, destValue, strings, pathBuilder);
            pathBuilder.deleteCharAt(pathBuilder.length() - 1);
        }

        if (parent.right != null) {
            pathBuilder.append("R");
            searchString(parent.right, startValue, destValue, strings, pathBuilder);
            pathBuilder.deleteCharAt(pathBuilder.length() - 1);
        }

    }

}
