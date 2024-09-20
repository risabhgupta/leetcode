package com.comppractice.august.rangesumofsortedsubrray;

import java.util.Arrays;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().rangeSum(new int[]{1,2,3,4}, 4, 1, 5));
    }
    public int rangeSum(int[] nums, int n, int left, int right) {
        long[] newArray = new long[n * (n + 1) / 2];
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            newArray[k] = nums[i];
            k++;
            for (int j = i + 1; j < nums.length; j++) {
                newArray[k] = newArray[k - 1] + nums[j];
                k++;
            }
        }

        Arrays.sort(newArray);

        long sum = 0;
        for (int i = left - 1; i < right; i++) {
            sum += newArray[i];
        }

        return (int)(sum % 1000000007);
    }
}
