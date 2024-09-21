package com.comppractice.september.matrixscore;

/**
 861. Score After Flipping Matrix
 Solved
 Medium
 Topics
 Companies
 You are given an m x n binary matrix grid.

 A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).

 Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

 Return the highest possible score after making any number of moves (including zero moves).



 Example 1:


 Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 Output: 39
 Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 Example 2:

 Input: grid = [[0]]
 Output: 1
 */
class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0}
        };

        System.out.println(new Solution().matrixScore(matrix));
    }

    public int matrixScore(int[][] grid) {
        int score = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = grid[i][j] ^ 1;
                }
            }
            score = (int) (score + Math.pow(2, grid[0].length - 1));
        }

        for (int col = 1; col < grid[0].length; col++) {
            int sum = 0;
            for (int i = 0; i < grid.length; i++) {
                sum += grid[i][col];
            }
            int multiplier = Math.max(sum, grid.length - sum);
            score = (int) (score + Math.pow(2, grid[0].length - 1 - col) * multiplier);
        }

        return score;
    }
}
