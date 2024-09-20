package com.comppractice.august.spiralmatrixiii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().spiralMatrixIII(5, 6, 1, 4)));
    }

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        Map<String, Boolean> visited = new HashMap<>();
        int[] direction_row = { 0, 1, 0, -1 };
        int[] direction_col = { 1, 0, -1, 0 };
        List<Integer[]> result = new ArrayList<>();
        dfs(rStart, cStart, rows, cols, 3, direction_row, direction_col, visited, result);
        int[][] transformedResult = new int[result.size()][2];
        int i = 0;
        for (Integer[] coordinate : result) {
            transformedResult[i++] = new int[] { coordinate[0], coordinate[1] };
        }
        return transformedResult;
    }

    public void dfs(int row, int col, int totalRows, int totalColumns, int direction, int[] direction_row,
            int[] direction_col, Map<String, Boolean> visited, List<Integer[]> results) {
        visited.put(row + "," + col, true);
        if (row >= 0 && row < totalRows && col < totalColumns && col >= 0) {
            results.add(new Integer[] { row, col });
        }

        if (results.size() < totalRows * totalColumns) {
            int nextRowInSameDirection = row + direction_row[direction];
            int nextColInSameDirection = col + direction_col[direction];

            int nextDirection = (direction + 1) % 4;
            int nextRowInNextDirection = row + direction_row[nextDirection];
            int nextColInNextDirection = col + direction_col[nextDirection];

            if (!visited.getOrDefault(nextRowInNextDirection + "," + nextColInNextDirection, false)) {
                dfs(nextRowInNextDirection, nextColInNextDirection, totalRows, totalColumns, nextDirection,
                        direction_row, direction_col, visited, results);
            } else {
                dfs(nextRowInSameDirection, nextColInSameDirection, totalRows, totalColumns, direction, direction_row,
                        direction_col, visited, results);
            }
        }
    }
}
