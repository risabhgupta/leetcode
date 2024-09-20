package com.comppractice.august.squareparam;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[] { -1, 1, 1, -1, 0 }, new int[] { 1, 1, -1, -1, 0 }));
    }

    public boolean solution(int[] X, int[] Y) {
        int minimumOfX = X[0];
        int minimumOfY = Y[0];
        int maximumOfX = X[0];
        int maximumOfY = Y[0];

        for (int i = 0; i < X.length; i++) {
            minimumOfX = Math.min(minimumOfX, X[i]);
            minimumOfY = Math.min(minimumOfY, Y[i]);
            maximumOfX = Math.max(maximumOfX, X[i]);
            maximumOfY = Math.max(maximumOfY, Y[i]);
        }

        boolean result = true;
        if ((maximumOfX - minimumOfX) != (maximumOfY - minimumOfY)) {
            result = false;
        } else {
            for (int i = 0; i < X.length; i++) {
                result = result && (((X[i] - minimumOfX == 0 || X[i] - maximumOfX == 0) && Y[i] >= minimumOfY
                        && Y[i] <= maximumOfY) || ((Y[i] - minimumOfY == 0 || Y[i] - maximumOfY == 0)
                        && X[i] >= minimumOfX && X[i] <= maximumOfX));
            }
        }
        return result;
    }
}
