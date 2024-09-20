package com.comppractice.august.maxpoints;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxPoints(
                new int[][] { { 1, 2, 3 }, { 1, 5, 1 }, { 3, 1, 1 } }));
    }

    public long maxPoints(int[][] points) {
        int[][] memo = new int[points.length][points[0].length];

        int result = 0;
        for (int j = 0; j < points[0].length; j++) {
            result = Math.max(selectedAndGetScore(0, j, points, memo), result);
        }

        return result;
    }

    private int selectedAndGetScore(int row, int col, int[][] points, int[][] memo) {
        if (row < points.length) {
            if (memo[row][col] == 0) {
                for (int j = 0; j < points[0].length; j++) {
                    int contributionScore = points[row][j] - Math.abs(col - j);
                    if(contributionScore > memo[row][col] ) {
                        memo[row][col] = Math.max(memo[row][col],
                                contributionScore + selectedAndGetScore(row + 1, j, points, memo));
                    }
                }
            }
            return memo[row][col];
        } else {
            return 0;
        }
    }
}