package com.comppractice.august.countoffairpairs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countFairPairs(new int[] { 0, 1, 7, 4, 4, 5 }, 3, 6));
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long totalPair = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];
            int lowerLimit = lower - num;
            int upperLimit = upper - num;

            int indexLower = binaryLeftSearch(nums, 0, i, lowerLimit);
            int indexUpper = binaryRightSearch(nums, 0, i, upperLimit);

            if (indexLower >= 0 && indexUpper >= 0) {
                totalPair += (indexUpper - indexLower);
            }

        }
        return totalPair;
    }

    public int binaryLeftSearch(int[] nums, int leftIndex, int rightIndex, int value) {
        if (leftIndex >= rightIndex) {
            return leftIndex;
        }
        int middle = (leftIndex + rightIndex) / 2;
        if (nums[middle] >= value) {
            return binaryLeftSearch(nums, leftIndex, middle, value);
        } else {
            return binaryLeftSearch(nums, middle + 1, rightIndex, value);
        }
    }

    public int binaryRightSearch(int[] nums, int leftIndex, int rightIndex, int value) {
        if (leftIndex >= rightIndex) {
            return rightIndex;
        }
        int middle = (leftIndex + rightIndex) / 2;
        if (nums[middle] <= value) {
            return binaryRightSearch(nums, middle + 1, rightIndex, value);
        } else {
            return binaryRightSearch(nums, leftIndex, middle, value);
        }
    }
}
