package com.comppractice.august.moststoneremoved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().removeStones(
                new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } }));
    }

    public int removeStones(int[][] stones) {
        DisjointSet disjointSet = new DisjointSet(stones.length);
        Map<Integer, List<Integer>> xGroupedStones = new HashMap<>();
        Map<Integer, List<Integer>> yGroupedStones = new HashMap<>();

        for (int i = 0; i < stones.length; i++) {
            xGroupedStones.computeIfAbsent(stones[i][0], k -> new ArrayList<>()).add(i);
            yGroupedStones.computeIfAbsent(stones[i][1], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> xAxisStones : xGroupedStones.values()) {
            for (int i = 0; i < xAxisStones.size() - 1; i++) {
                disjointSet.union(xAxisStones.get(i), xAxisStones.get(i + 1));
            }
        }

        for (List<Integer> yAxisStones : yGroupedStones.values()) {
            for (int i = 0; i < yAxisStones.size() - 1; i++) {
                disjointSet.union(yAxisStones.get(i), yAxisStones.get(i + 1));
            }
        }

        return stones.length - disjointSet.totalGroups();

    }

    static class DisjointSet {
        int[] rank;
        int[] representative;

        DisjointSet(int n) {
            rank = new int[n];
            representative = new int[n];

            for (int i = 0; i < n; i++) {
                representative[i] = i;
            }
        }

        private int findRepresentative(int a) {
            return representative[a] == a ? a : (representative[a] = findRepresentative(representative[a]));
        }

        public void union(int a, int b) {
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
            }
        }

        public int totalGroups() {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < representative.length; i++) {
                set.add(findRepresentative(i));
            }
            return set.size();
        }
    }
}
