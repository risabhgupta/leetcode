package com.comppractice.august.surroundedregions;

import java.util.Arrays;


public class Solution {

    public static void main(String[] args) {
        char[][] array = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        new Solution().solve(array);
    }

    public void solve(char[][] board) {
        final DisjointSet disjoint = getDisjointSet(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    int[] representative = disjoint.findRepresentative(i, j);
                    if (representative[0] != 0 && representative[0] != board.length - 1 && representative[1] != 0
                            && representative[1] != board[i].length - 1) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    private DisjointSet getDisjointSet(final char[][] board) {
        DisjointSet disjoint = new DisjointSet(board.length, board[0].length);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    if (i + 1 < board.length) {
                        if (board[i + 1][j] == 'O') {
                            disjoint.union(i, j, i + 1, j);
                        }
                    }
                    if (j + 1 < board[0].length) {
                        if (board[i][j + 1] == 'O') {
                            disjoint.union(i, j, i, j + 1);
                        }
                    }
                }
            }
        }
        return disjoint;
    }

    static class DisjointSet {
        int[][][] representative;
        int[][] rank;

        DisjointSet(int m, int n) {
            representative = new int[m][n][2];
            rank = new int[m][n];

            Arrays.fill(rank[0], 100);
            Arrays.fill(rank[rank.length - 1], 100);

            for (int i = 0; i < m; i++) {
                rank[i][0] = 100;
                rank[i][rank[i].length - 1] = 100;
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    representative[i][j] = new int[] { i, j };
                }
            }
        }

        public int[] findRepresentative(int row, int col) {
            if (row == representative[row][col][0] && representative[row][col][1] == col) {
                return representative[row][col];
            } else {
                return representative[row][col] = findRepresentative(representative[row][col][0],
                        representative[row][col][1]);
            }
        }

        public void union(int row, int col, int row2, int col2) {
            int[] aRepresentative = findRepresentative(row, col);
            int[] bRepresentative = findRepresentative(row2, col2);

            if (aRepresentative != bRepresentative) {
                if (rank[aRepresentative[0]][aRepresentative[1]] > rank[bRepresentative[0]][bRepresentative[1]]) {
                    representative[bRepresentative[0]][bRepresentative[1]] = aRepresentative;
                } else if (rank[aRepresentative[0]][aRepresentative[1]]
                        < rank[bRepresentative[0]][bRepresentative[1]]) {
                    representative[aRepresentative[0]][aRepresentative[1]] = bRepresentative;
                } else {
                    representative[aRepresentative[0]][aRepresentative[1]] = bRepresentative;
                    rank[aRepresentative[0]][aRepresentative[1]]++;
                }
            }
        }
    }
}
