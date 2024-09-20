package com.comppractice.august.cutrods;

import java.util.Arrays;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().cutRod(new int[] { 1, 5, 8, 9, 10, 17, 17, 20 }, 8));
    }

    public int cutRod(int[] price, int n) {
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        memo[0] = price[0];

        return cutRod(price, n, memo);
    }

    private int cutRod(int[] price, final int n, int[] memo) {
        if (n == 0) {
            return 0;
        } else if (memo[n - 1] != -1) {
            return memo[n - 1];
        } else {
            int maximumCost = 0;
            for (int length = 1; length <= n; length++) {
                maximumCost = Math.max(price[length - 1] + cutRod(price, n - length, memo), maximumCost);
            }
            memo[n - 1] = maximumCost;
            return maximumCost;
        }
    }
}
