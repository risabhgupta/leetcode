package com.comppractice.august.regioncutslashes;

public class Solution {
    public static void main(String... arg) {
        System.out.println(new Solution().regionsBySlashes(new String[] { " /", "/ " }));
    }

    public int regionsBySlashes(String[] grid) {
        int[][] bitMap = getBitMap(grid);
        boolean[][] visited = new boolean[bitMap.length][bitMap[0].length];
        boolean[][] visitedInCurrentContext = new boolean[bitMap.length][bitMap[0].length];
        int count = 0;
        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[0].length; j++) {
                if (bitMap[i][j] == 0 && !visited[i][j]) {
                    dfs(bitMap, i, j, visited, visitedInCurrentContext);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int[][] bitMap, int row, int col, boolean[][] visited, boolean[][] visitedInCurrentContext) {
        if (row >= 0 && col >= 0 && row < bitMap.length && col < bitMap[0].length && !visited[row][col]
                && !visitedInCurrentContext[row][col] && bitMap[row][col] == 0) {
            visitedInCurrentContext[row][col] = true;
            dfs(bitMap, row + 1, col, visited, visitedInCurrentContext);
            dfs(bitMap, row, col - 1, visited, visitedInCurrentContext);
            dfs(bitMap, row - 1, col, visited, visitedInCurrentContext);
            dfs(bitMap, row, col + 1, visited, visitedInCurrentContext);

            visited[row][col] = true;
            visitedInCurrentContext[row][col] = false;
        }
    }

    private static int[][] getBitMap(final String[] grid) {
        int[][] bitMap = new int[grid.length * 3][grid[0].length() * 3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                int rowDesignPointer = i * 3;
                int colDesignPointer = j * 3;
                if (grid[i].charAt(j) == '\\') {
                    bitMap[rowDesignPointer][colDesignPointer] = 1;
                    bitMap[rowDesignPointer + 1][colDesignPointer + 1] = 1;
                    bitMap[rowDesignPointer + 2][colDesignPointer + 2] = 1;
                } else if (grid[i].charAt(j) == '/') {
                    bitMap[rowDesignPointer][colDesignPointer + 2] = 1;
                    bitMap[rowDesignPointer + 1][colDesignPointer + 1] = 1;
                    bitMap[rowDesignPointer + 2][colDesignPointer] = 1;
                }
            }
        }
        return bitMap;
    }
}
