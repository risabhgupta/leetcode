package com.comppractice.august.sprialmatrix;

import java.util.LinkedList;
import java.util.List;


public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {  1,  2,  3,  4,  5 },
                {  6,  7,  8,  9, 10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 },
                { 21, 22, 23, 24, 25 },
                { 26, 27, 28, 29, 30 },
                { 31, 32, 33, 34, 35 },
                { 36, 37, 38, 39, 40 }
        };
        System.out.println(new Solution().spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        dfs(matrix, 0, 0, result, visited, 0);
        return result;
    }

    public void dfs(int[][] matrix, int row, int col, List<Integer> result, boolean[][] visited, int direction) {
        if (visited[row][col]) {
            return;
        } else {
            result.add(matrix[row][col]);
            visited[row][col] = true;

            int newColumn = col + new int[] { 1, 0, -1, 0 }[direction];
            int newRow = row + new int[] { 0, 1, 0, -1 }[direction];

            if (newColumn >= 0 && newColumn < matrix[0].length && newRow >= 0 && newRow < matrix.length && !visited[newRow][newColumn]) {
                dfs(matrix, newRow, newColumn, result, visited, direction);
            } else {
                //Change direction
                direction++;
                direction %= 4;

                newColumn = col + new int[] { 1, 0, -1, 0 }[direction];
                newRow = row + new int[] { 0, 1, 0, -1 }[direction];
                if (newColumn >= 0 && newColumn < matrix[0].length && newRow >= 0 && newRow < matrix.length
                        && !visited[newRow][newColumn]) {
                    dfs(matrix, newRow, newColumn, result, visited, direction);
                }
            }
        }
    }
}
