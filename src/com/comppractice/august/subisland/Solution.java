package com.comppractice.august.subisland;

public class Solution {
    public static void main(String[] args) {
        int[][] grid1 = { { 1, 0, 1, 0, 1 }, { 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 },
                { 1, 0, 1, 0, 1 } };

        int[][] grid2 = { { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 },
                { 1, 0, 0, 0, 1 } };

        System.out.println(new Solution().countSubIslands(grid1, grid2));
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        int rows = grid2.length;
        int cols = grid2[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid2[row][col] == 1 && dfs(grid1, grid2, row, col)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int row, int col) {
        int rows = grid2.length;
        int cols = grid2[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || grid2[row][col] == 0) {
            return true;
        }

        grid2[row][col] = 0; // Mark as visited by sinking the island

        boolean isPart = grid1[row][col] == 1;

        // Recursively visit all four directions
        isPart &= dfs(grid1, grid2, row + 1, col);
        isPart &= dfs(grid1, grid2, row - 1, col);
        isPart &= dfs(grid1, grid2, row, col + 1);
        isPart &= dfs(grid1, grid2, row, col - 1);

        return isPart;
    }

}
