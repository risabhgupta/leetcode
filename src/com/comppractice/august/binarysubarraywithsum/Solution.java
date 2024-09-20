package com.comppractice.august.binarysubarraywithsum;

import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().numSubarraysWithSum(new int[] { 1,0,1,0,1 }, 2));
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int sum = 0;
        int totalPossibleSubarray = 0;
        countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);

        for (final int num : nums) {
            sum = sum + num;
            totalPossibleSubarray += countMap.getOrDefault(sum - goal, 0);

            countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
        }

        return totalPossibleSubarray;
    }
}
