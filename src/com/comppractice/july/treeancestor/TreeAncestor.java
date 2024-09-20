package com.comppractice.july.treeancestor;

public class TreeAncestor {
    Integer[][] ancestorCache;
    int[] parentNodeMap;

    public TreeAncestor(int n, int[] parent) {
        parentNodeMap = parent;
        ancestorCache = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            ancestorCache[i] = new Integer[n];
        }
    }

    public Integer getKthAncestor(int node, int k) {
        Integer ancestorNode = null;
        if (node == -1) {
            ancestorNode = node;
        } else {
            if (k == 0) {
                ancestorNode = node;
            } else {
                if (ancestorCache[node][k] == null) {
                    ancestorNode = getKthAncestor(parentNodeMap[node], k - 1);
                } else {
                    ancestorNode = ancestorCache[node][k];
                }
            }
            ancestorCache[node][k] = ancestorNode;
        }
        return ancestorNode;
    }
}
