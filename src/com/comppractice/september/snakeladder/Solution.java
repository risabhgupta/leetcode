package com.comppractice.september.snakeladder;

import java.util.Arrays;


public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 35, -1, -1, 13, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 15, -1, -1, -1, -1 }
        };

        int[][] matrix2 = {
                { -1, -1 }, { -1, 3 }
        };

        int[][] matrix3 = {
                { -1, -1, 19, 10, -1 },
                { 2, -1, -1, 6, -1 },
                { -1, 17, -1, 19, -1 },
                { 25, -1, 20, -1, -1 },
                { -1, -1, -1, -1, 15 }
        };

        int[][] matrix4 = {
                { -1, 83, -1, 46, -1, -1, -1, -1, 40, -1 },
                { -1, 29, -1, -1, -1, 51, -1, 18, -1, -1 },
                { -1, 35, 31, 51, -1, 6, -1, 40, -1, -1 },
                { -1, -1, -1, 28, -1, 36, -1, -1, -1, -1 },
                { -1, -1, -1, -1, 44, -1, -1, 84, -1, -1 },
                { -1, -1, -1, 31, -1, 98, 27, 94, 74, -1 },
                { 4, -1, -1, 46, 3, 14, 7, -1, 84, 67 },
                { -1, -1, -1, -1, 2, 72, -1, -1, 86, -1 },
                { -1, 32, -1, -1, -1, -1, -1, -1, -1, 19 },
                { -1, -1, -1, -1, -1, 72, 46, -1, 92, 6 } };

/*        System.out.println(new Solution().snakesAndLadders(matrix));

        System.out.println(new Solution().snakesAndLadders(matrix2));

        System.out.println(new Solution().snakesAndLadders(matrix3));*/

        System.out.println(new Solution().snakesAndLadders(matrix4));
    }

    public int snakesAndLadders(int[][] board) {
        int total = board.length * board.length;
        int rows = board.length;
        int cols = board[0].length;
        int[] stair = new int[total + 1];
        int index = 1;

        for (int i = rows - 1; i >= 0; i--) {
            if ((rows - 1 - i) % 2 == 0) {
                for (int j = 0; j < cols; j++) {
                    stair[index++] = board[i][j];
                }
            } else {
                for (int j = cols - 1; j >= 0; j--) {
                    stair[index++] = board[i][j];
                }
            }
        }

        int[] memo = new int[total + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        boolean[] visitedInContext = new boolean[total + 1];
        boolean[] visited = new boolean[total + 1];

        int moves = minMovesFrom(stair, 1, total, memo, visitedInContext, visited);
        return moves == Integer.MAX_VALUE ? -1 : moves;
    }

    private int minMovesFrom(int[] stair, int start, int total, int[] memo, boolean[] visitedInContext,
            boolean[] visited) {
        if (start == total) {
            return 0;
        }

        if (visited[start]) {
            return memo[start];
        }

        visitedInContext[start] = true;
        for (int i = 1; i <= 6 && start + i <= total; i++) {
            int nextPosition = start + i;
            if (stair[nextPosition] != -1) {
                nextPosition = stair[nextPosition];
            }
            if (!visitedInContext[nextPosition]) {
                int result = minMovesFrom(stair, nextPosition, total, memo, visitedInContext, visited);
                if (result != Integer.MAX_VALUE) {
                    memo[start] = Math.min(memo[start], 1 + result);
                }
            }
        }
        visitedInContext[start] = false;
        visited[start] = true;
        return memo[start];
    }

}
