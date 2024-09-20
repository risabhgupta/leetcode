package com.comppractice.august.disjoinset;

public class Solution {
    public static void main(String[] args) {
        // Create a disjoint set with 10000 elements
        DisjointSet disjointSet = new DisjointSet(10000);

        // Perform a large number of union operations
        for (int i = 0; i < 9999; i++) {
            disjointSet.unionByRank(i, i + 1);
        }

        // Test the connectivity between various nodes
        System.out.println(disjointSet.isRelated(0, 9999));  // Should be true
        System.out.println(disjointSet.isRelated(0, 5000));  // Should be true
        System.out.println(disjointSet.isRelated(100, 5000)); // Should be true
        System.out.println(disjointSet.isRelated(5000, 9999)); // Should be true
        System.out.println(disjointSet.isRelated(0, 9999));  // Should be false (out of bounds, if not handled)

        // Additional union operations
        disjointSet.unionByRank(9999, 0);
        disjointSet.unionByRank(1234, 5678);
        disjointSet.unionByRank(8765, 4321);

        // More connectivity tests
        System.out.println(disjointSet.isRelated(1234, 5678)); // Should be true
        System.out.println(disjointSet.isRelated(8765, 4321)); // Should be true
        System.out.println(disjointSet.isRelated(1234, 4321)); // Should be false

        // Test after all unions
        System.out.println(disjointSet.isRelated(0, 9999));  // Should still be true
    }
}

class DisjointSet {
    public int[] representative;
    public int[] rank;

    public DisjointSet(int n) {
        representative = new int[n];
        for (int i = 0; i < representative.length; i++) {
            representative[i] = i;
        }

        rank = new int[n];
    }

    public int findRepresentative(int a) {
        if (representative[a] == a) {
            return a;
        } else {
            return representative[a] = findRepresentative(representative[a]);
        }
    }

    public void unionByRank(int a, int b) {
        int parentOfA = findRepresentative(a);
        int parentOfB = findRepresentative(b);
        if (parentOfA != parentOfB) {
            if (rank[parentOfA] > rank[parentOfB]) {
                representative[b] = parentOfA;
            } else if (rank[parentOfA] < rank[parentOfB]) {
                representative[a] = parentOfB;
            } else {
                representative[a] = parentOfB;
                rank[b]++;
            }
        }
    }

    public boolean isRelated(int a, int b) {
        int parentOfA = findRepresentative(a);
        int parentOfB = findRepresentative(b);

        return parentOfA == parentOfB;
    }
}
