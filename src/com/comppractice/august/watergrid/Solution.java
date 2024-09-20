package com.comppractice.august.watergrid;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minDays(new int[][] { { 1, 1 } }));
    }

    public int minDays(int[][] grid) {
        if (checkNumberOfIsland(grid) != 1) {
            return 0;
        } else {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = 0;
                        if (checkNumberOfIsland(grid) != 1) {
                            return 1;
                        }
                        grid[i][j] = 1;
                    }
                }
            }
        }
        return 2;
    }

    private int checkNumberOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        boolean[][] visitedInCurrentContext = new boolean[grid.length][grid[0].length];

        int numberOfIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] > 0) {
                    dfs(grid, i, j, visited, visitedInCurrentContext);
                    numberOfIsland++;
                }
            }
        }
        return numberOfIsland;
    }

    private void dfs(int[][] grid, int row, int col, boolean[][] visited, boolean[][] visitedInCurrentContext) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length && grid[row][col] > 0
                && !visited[row][col] && !visitedInCurrentContext[row][col]) {
            visited[row][col] = true;
            visitedInCurrentContext[row][col] = true;
            dfs(grid, row, col + 1, visited, visitedInCurrentContext);
            dfs(grid, row + 1, col, visited, visitedInCurrentContext);
            dfs(grid, row, col - 1, visited, visitedInCurrentContext);
            dfs(grid, row - 1, col, visited, visitedInCurrentContext);
            visitedInCurrentContext[row][col] = false;
        }
    }
}
