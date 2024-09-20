package com.comppractice.august.magicsquare;

import java.util.HashSet;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(
                new Solution().numMagicSquaresInside(new int[][] { { 4, 3, 8, 4 }, { 9, 5, 1, 9 }, { 2, 7, 6, 2 } }));

    }

    public int numMagicSquaresInside(int[][] grid) {
        if (grid.length < 3 || grid[0].length < 3) {
            return 0;
        }
        int[][] rowWiseSum = new int[grid.length + 6][grid[0].length + 6];
        int[][] colWiseSum = new int[grid.length + 6][grid[0].length + 6];
        int[][] leftDiagonalSum = new int[grid.length + 6][grid[0].length + 6];
        int[][] rightDiagonalSum = new int[grid.length + 6][grid[0].length + 6];

        for (int i = 3; i < grid.length + 3; i++) {
            for (int j = 3; j < grid[0].length + 3; j++) {
                rowWiseSum[i][j] = rowWiseSum[i][j - 1] + grid[i - 3][j - 3];
                colWiseSum[i][j] = colWiseSum[i - 1][j] + grid[i - 3][j - 3];
                leftDiagonalSum[i][j] = leftDiagonalSum[i - 1][j - 1] + grid[i - 3][j - 3];
                rightDiagonalSum[i][j] = rightDiagonalSum[i - 1][j + 1] + grid[i - 3][j - 3];
            }
        }

        for (int i = grid.length + 3 - 1; i > 2; i--) {
            for (int j = grid[0].length + 3 - 1; j > 2; j--) {
                rowWiseSum[i][j] = rowWiseSum[i][j] - rowWiseSum[i][j - 3];
                colWiseSum[i][j] = colWiseSum[i][j] - colWiseSum[i - 3][j];
                leftDiagonalSum[i][j] = leftDiagonalSum[i][j] - leftDiagonalSum[i - 3][j - 3];
                rightDiagonalSum[i][j] = rightDiagonalSum[i][j] - rightDiagonalSum[i - 3][j + 3];
            }
        }

        int count = 0;
        for (int i = 5; i < rowWiseSum.length - 3; i++) {
            for (int j = 5; j < rowWiseSum[0].length - 3; j++) {
                boolean isMagic = validMagicBox(grid, i - 3, j - 3);

                int sumRowWise = rowWiseSum[i][j];
                isMagic &= sumRowWise == rowWiseSum[i - 1][j];
                isMagic &= sumRowWise == rowWiseSum[i - 2][j];
                isMagic &= sumRowWise == colWiseSum[i][j] && sumRowWise == colWiseSum[i][j - 1];
                isMagic &= sumRowWise == colWiseSum[i][j - 2];

                isMagic &= sumRowWise == leftDiagonalSum[i][j];
                isMagic &= sumRowWise == rightDiagonalSum[i][j - 2];

                if (isMagic) {
                    count++;
                }

            }
        }
        return count;
    }

    private boolean validMagicBox(int[][] grid, int row, int col) {
        Set<Integer> distinctNumber = new HashSet<>();
        for (int i = row - 2; i <= row; i++) {
            for (int j = col - 2; j <= col; j++) {
                if (grid[i][j] > 9 || grid[i][j] < 1 || distinctNumber.contains(grid[i][j])) {
                    return false;
                } else {
                    distinctNumber.add(grid[i][j]);
                }
            }
        }
        return true;
    }
}
