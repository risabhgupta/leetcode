package com.comppractice.august.smallessubarray;

import java.util.Arrays;


class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int[] maximumArray = new int[nums.length];
        Arrays.fill(maximumArray, 1);

        for (int bit = 0; bit < 32; bit++) {
            int lastIndexOfFullBit = -1;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (((nums[i] >> bit) & 1) == 1) {
                    lastIndexOfFullBit = i;
                }
                maximumArray[i] = Math.max(maximumArray[i], lastIndexOfFullBit - i + 1);
            }
        }

        return maximumArray;
    }
}
