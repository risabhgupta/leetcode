package com.comppractice.september.satisfiabilityequation;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().equationsPossible(new String[] { "a==b", "b!=a" }));
    }

    public boolean equationsPossible(String[] equations) {
        DisjointSet set = new DisjointSet(26);

        for (String s : equations) {
            char[] equation = s.toCharArray();
            if (equation[1] == '=') {
                set.union(equation[0] - 'a', equation[3] - 'a');
            }
        }

        for (String s : equations) {
            char[] equation = s.toCharArray();
            if (equation[1] == '!') {
                if (set.find(equation[0] - 'a', equation[3] - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}

class DisjointSet {
    int[] parent;
    int[] rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int a, int b) {
        int parentOfA = findParent(a);
        int parentOfB = findParent(b);

        if (findParent(a) != findParent(b)) {
            if (rank[parentOfA] > rank[parentOfB]) {
                parent[parentOfA] = parentOfB;
            } else if (rank[parentOfA] < rank[parentOfB]) {
                parent[parentOfB] = parentOfA;
            } else {
                parent[parentOfB] = parentOfA;
                rank[parentOfA]++;
            }
        }
    }

    public boolean find(int a, int b) {
        return findParent(a) == findParent(b);
    }

    private int findParent(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = findParent(parent[a]);
        }
    }

}
