package com.comppractice.august.googleq3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution().maxWeightAfterRemoval(new int[][] {
                                { 0, 1, 4 },
                                { 1, 2, 3 },
                                { 2, 3, 2 },
                                { 0, 3, 1 },
                                { 2, 4, 10 }
                        },
                        new int[] { 1, 0, 4, 2, 3 })));
    }

    public int[] maxWeightAfterRemoval(int[][] edges, int[] queries) {
        Map<Integer, Set<Integer>> nodeEdges = new HashMap<>();
        TreeSet<Integer> edgesSet = new TreeSet<>(Comparator.comparingInt(edgeId -> -edges[edgeId][2]));

        for (int edgeId = 0; edgeId < edges.length; edgeId++) {
            nodeEdges.computeIfAbsent(edges[edgeId][0], k -> new HashSet<>()).add(edgeId);
            nodeEdges.computeIfAbsent(edges[edgeId][1], k -> new HashSet<>()).add(edgeId);
            edgesSet.add(edgeId);
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            edgesSet.removeAll(nodeEdges.get(queries[i]));
            if (!edgesSet.isEmpty()) {
                result[i] = edgesSet.first();
            } else {
                result[i] = -1;
            }
        }
        return result;
    }
}
