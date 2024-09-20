package com.comppractice.august.kthsmallesdistancepair;

import java.util.Arrays;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().smallestDistancePair(new int[] { 1,3,1}, 2));
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int maxDistance = nums[nums.length - 1] - nums[0];
        return binarySearchAndCountPair(nums, maxDistance, k);
    }

    private int binarySearchAndCountPair(int[] nums, int maxDistance, int target) {
        int countPair = countPair(nums, maxDistance);
        if (maxDistance == target) {
            return countPair;
        }
        if (countPair > target) {
            return binarySearchAndCountPair(nums, (maxDistance / 2), target);
        } else {
            return binarySearchAndCountPair(nums, (3 * maxDistance / 2) + 1, target);
        }
    }

    private int countPair(int[] nums, int maxDistance) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] <= maxDistance) {
                    count++;
                }
            }
        }
        return count;
    }

}
