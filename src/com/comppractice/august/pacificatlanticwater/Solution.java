package com.comppractice.august.pacificatlanticwater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().pacificAtlantic(new int[][] {
                { 10, 10, 1, 11, 2, 15, 17, 6, 17, 10, 0, 10, 18, 9, 16, 13, 8, 9, 12, 6, 3, 2, 5, 19, 4, 14 },
                { 12, 19, 13, 2, 7, 2, 3, 8, 17, 4, 2, 1, 8, 13, 7, 2, 10, 16, 12, 3, 4, 12, 4, 16, 0, 12 },
                { 1, 12, 9, 18, 12, 16, 17, 5, 13, 0, 7, 13, 12, 13, 6, 2, 11, 19, 7, 2, 6, 11, 11, 0, 17, 6 },
                { 1, 12, 2, 0, 11, 7, 7, 3, 7, 13, 11, 1, 11, 15, 5, 13, 14, 12, 4, 10, 5, 16, 3, 17, 18, 12 },
                { 9, 16, 11, 5, 9, 13, 7, 18, 18, 14, 19, 10, 9, 4, 8, 14, 4, 19, 13, 1, 14, 8, 0, 3, 12, 10 },
                { 10, 19, 9, 11, 1, 18, 1, 2, 1, 8, 1, 5, 2, 15, 14, 13, 18, 18, 11, 4, 15, 3, 15, 12, 12, 2 },
                { 0, 10, 9, 2, 15, 9, 12, 7, 0, 0, 12, 18, 9, 12, 18, 4, 18, 10, 3, 1, 17, 14, 13, 18, 9, 4 },
                { 3, 19, 9, 16, 16, 19, 11, 19, 6, 9, 18, 0, 12, 11, 13, 1, 15, 6, 9, 18, 9, 6, 3, 12, 12, 2 },
                { 0, 16, 15, 12, 3, 19, 18, 9, 5, 1, 4, 3, 19, 15, 1, 0, 7, 10, 14, 4, 8, 10, 15, 16, 14, 8 },
                { 15, 9, 3, 15, 19, 17, 17, 10, 9, 8, 16, 11, 3, 15, 15, 9, 1, 14, 11, 13, 16, 7, 1, 7, 13, 5 },
                { 9, 19, 6, 7, 19, 14, 4, 18, 6, 10, 19, 13, 12, 7, 7, 15, 6, 10, 7, 8, 8, 8, 19, 13, 17, 14 },
                { 14, 7, 1, 15, 2, 6, 12, 4, 2, 19, 17, 17, 8, 9, 19, 10, 0, 12, 4, 12, 4, 16, 15, 18, 8, 0 },
                { 4, 8, 5, 8, 0, 2, 19, 18, 1, 7, 13, 9, 13, 16, 6, 15, 15, 12, 18, 5, 8, 11, 6, 17, 5, 11 },
                { 17, 16, 9, 19, 12, 6, 13, 19, 0, 6, 7, 9, 7, 13, 9, 18, 5, 15, 16, 8, 18, 9, 6, 0, 11, 14 },
                { 11, 5, 13, 3, 12, 19, 5, 15, 2, 15, 9, 16, 6, 12, 8, 0, 19, 19, 11, 0, 16, 8, 15, 15, 1, 12 },
                { 15, 16, 16, 19, 14, 1, 2, 11, 14, 8, 16, 13, 2, 0, 3, 8, 1, 5, 4, 15, 12, 5, 13, 3, 5, 3 } }));
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        boolean[][][] isWaterFlowToAtlantaAndPacific = new boolean[heights.length][heights[0].length][2];
        boolean[][] visitedInCurrentContext = new boolean[heights.length][heights[0].length];

        for (int i = 0; i < isWaterFlowToAtlantaAndPacific.length; i++) {
            for (int j = 0; j < isWaterFlowToAtlantaAndPacific[0].length; j++) {
                isWaterFlowToAtlantaAndPacific[i][j] = new boolean[] { false, false };
                if (i == 0 || j == 0) {
                    isWaterFlowToAtlantaAndPacific[i][j][0] = true;
                }
                if (i == isWaterFlowToAtlantaAndPacific.length - 1
                        || j == isWaterFlowToAtlantaAndPacific[0].length - 1) {
                    isWaterFlowToAtlantaAndPacific[i][j][1] = true;
                }
            }
        }

        boolean[][] isVisited = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                dfs(heights, isWaterFlowToAtlantaAndPacific, isVisited, visitedInCurrentContext, i, j);
            }
        }

        isVisited = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                dfs(heights, isWaterFlowToAtlantaAndPacific, isVisited, visitedInCurrentContext,
                        heights.length - (i + 1), heights[0].length - (j + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < isWaterFlowToAtlantaAndPacific.length; i++) {
            for (int j = 0; j < isWaterFlowToAtlantaAndPacific[i].length; j++) {
                if (isWaterFlowToAtlantaAndPacific[i][j][0] && isWaterFlowToAtlantaAndPacific[i][j][1]) {
                    result.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }

        return result;
    }

    public void dfs(int[][] heights, boolean[][][] waterFlow, boolean[][] visited, boolean[][] visitedInCurrentContext,
            int row, int col) {
        if (!visited[row][col] && !visitedInCurrentContext[row][col]) {
            visitedInCurrentContext[row][col] = true;
            int currentHeight = heights[row][col];
            if (col + 1 < heights[row].length && currentHeight >= heights[row][col + 1]) {
                dfs(heights, waterFlow, visited, visitedInCurrentContext, row, col + 1);
                waterFlow[row][col][0] = waterFlow[row][col][0] || waterFlow[row][col + 1][0];
                waterFlow[row][col][1] = waterFlow[row][col][1] || waterFlow[row][col + 1][1];
            }
            if (row + 1 < heights.length && currentHeight >= heights[row + 1][col]) {
                dfs(heights, waterFlow, visited, visitedInCurrentContext, row + 1, col);
                waterFlow[row][col][0] = waterFlow[row][col][0] || waterFlow[row + 1][col][0];
                waterFlow[row][col][1] = waterFlow[row][col][1] || waterFlow[row + 1][col][1];
            }
            if (row - 1 >= 0 && currentHeight >= heights[row - 1][col]) {
                dfs(heights, waterFlow, visited, visitedInCurrentContext, row - 1, col);
                waterFlow[row][col][0] = waterFlow[row][col][0] || waterFlow[row - 1][col][0];
                waterFlow[row][col][1] = waterFlow[row][col][1] || waterFlow[row - 1][col][1];
            }
            if (col - 1 >= 0 && currentHeight >= heights[row][col - 1]) {
                dfs(heights, waterFlow, visited, visitedInCurrentContext, row, col - 1);
                waterFlow[row][col][0] = waterFlow[row][col][0] || waterFlow[row][col - 1][0];
                waterFlow[row][col][1] = waterFlow[row][col][1] || waterFlow[row][col - 1][1];
            }
            visitedInCurrentContext[row][col] = false;
            visited[row][col] = true;
        }
    }
}
