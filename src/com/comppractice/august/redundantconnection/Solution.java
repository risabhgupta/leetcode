package com.comppractice.august.redundantconnection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
    public static void main(String[] args) {
        int[][] edges = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } };
        System.out.println(Arrays.toString(new Solution().findRedundantConnection(edges)));
    }

    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet disjointSet = new DisjointSet(1000);
        List<int[]> edgesNotNeeded = new ArrayList<>();

        for (int[] edge : edges) {
            if (disjointSet.unionByRank(edge[0], edge[1])) {
                edgesNotNeeded.add(edge);
            }
        }

        return edgesNotNeeded.get(edgesNotNeeded.size() - 1);
    }

    static class DisjointSet {
        int[] rank;
        int[] representative;

        DisjointSet(int n) {
            this.rank = new int[n];
            this.representative = new int[n];

            for (int i = 0; i < n; i++) {
                representative[i] = i;
            }
        }

        public boolean unionByRank(int a, int b) {
            int aRepresentative = findRepresentative(a);
            int bRepresentative = findRepresentative(b);

            if (aRepresentative != bRepresentative) {
                if (rank[aRepresentative] > rank[bRepresentative]) {
                    representative[bRepresentative] = aRepresentative;
                } else if (rank[aRepresentative] < rank[bRepresentative]) {
                    representative[aRepresentative] = bRepresentative;
                } else {
                    representative[aRepresentative] = bRepresentative;
                    rank[bRepresentative]++;
                }
                return false;
            } else {
                return true;
            }
        }

        private int findRepresentative(int a) {
            if (representative[a] == a) {
                return a;
            } else {
                return representative[a] = findRepresentative(representative[a]);
            }
        }
    }
}
