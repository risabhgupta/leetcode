package com.comppractice.september.solvingqwithbrainpower;

public class Solution {
    public static void main(String[] args) {
        int[][] questions = { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 } };
        System.out.println(new Solution().mostPoints(questions));
    }

    public long mostPoints(int[][] questions) {
        long[] memo = new long[questions.length];
        return solve(0, questions, memo);
    }

    private long solve(int i, int[][] questions, long[] memo) {
        if (i < questions.length) {
            if (memo[i] == 0) {
                long chooseToSolve = questions[i][0] + solve(i + questions[i][1] + 1, questions, memo);
                long chooseNotToSolve = solve(i + 1, questions, memo);
                memo[i] = Math.max(chooseToSolve, chooseNotToSolve);
            }
            return memo[i];
        } else {
            return 0;
        }
    }
}
