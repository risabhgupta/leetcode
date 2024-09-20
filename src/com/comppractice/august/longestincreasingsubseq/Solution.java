package com.comppractice.august.longestincreasingsubseq;

public class Solution {
    public static void main(String... arg) {
        System.out.println(new Solution().lengthOfLIS(new int[] { 0,1,0,3,2,3}));
    }

    public int lengthOfLIS(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j >= 0; j--) {
                if (nums[j] > nums[i]) {
                    if(i - 1 > 0 && j + 1 < nums.length) {
                        dp[i][j] = Math.max(dp[i - 1][j + 1] + 1, dp[i][j + 1]);
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = 1;
                }
            }
        }

        return dp[nums.length - 1][0];
    }
}
