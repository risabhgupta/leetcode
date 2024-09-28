package com.comppractice.september.longestincreasingpath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Code
 * Testcase
 * Test Result
 * Test Result
 * 329. Longest Increasing Path in a Matrix
 * Solved
 * Hard
 * Topics
 * Companies
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * <p>
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 * <p>
 * Input: matrix = [[1]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        System.out.println(new Solution().longestIncreasingPath(matrix));
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] longestPathCache = new int[rows][cols];
        int maxPath = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, longestPathCache));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int row, int col, int[][] longestPathCache) {
        if (longestPathCache[row][col] != 0) return longestPathCache[row][col];

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{row, col, 1});
        int maxLen = 1;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int currRow = current[0];
            int currCol = current[1];
            int pathLen = current[2];

            maxLen = Math.max(maxLen, pathLen);

            for (int[] dir : directions) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                if (nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[0].length
                        && matrix[nextRow][nextCol] > matrix[currRow][currCol]) {
                    stack.push(new int[]{nextRow, nextCol, pathLen + 1});
                }
            }
        }

        longestPathCache[row][col] = maxLen;
        return maxLen;
    }
}
