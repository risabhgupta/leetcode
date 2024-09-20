package com.comppractice.july.luckynumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().luckyNumbers(new int[][] { { 3, 7, 8, 4 }, { 9, 11, 13, 2 }, { 15, 16, 17, 1 } }));
    }
}

class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] minInRow = new int[rows];
        int[] maxInCol = new int[cols];

        // Initialize minInRow with the maximum possible value
        Arrays.fill(minInRow, Integer.MAX_VALUE);

        // Find the minimum element in each row and the maximum element in each column
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                minInRow[row] = Math.min(minInRow[row], matrix[row][col]);
                maxInCol[col] = Math.max(maxInCol[col], matrix[row][col]);
            }
        }

        List<Integer> result = new ArrayList<>();
        // Find all lucky numbers
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == minInRow[row] && matrix[row][col] == maxInCol[col]) {
                    result.add(matrix[row][col]);
                }
            }
        }

        return result;
    }
}