package com.comppractice.august.largestsequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Solution {
    public static void main(String[] args) {
        final int[] arr = { 100, 4, 200, 1, 3, 2 };
        System.out.println(new Solution().maxSubArray(arr));
    }

    public int maxSubArray(int[] nums) {
        Set<Integer> numbers = new HashSet<>();
        for (int num : nums) {
            numbers.add(num);
        }

        Map<Integer, Integer> memo = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            max = Math.max(getNextConsecutiveCount(num, numbers, memo), max);
        }

        return max;
    }

    public int getNextConsecutiveCount(int number, Set<Integer> numbers, Map<Integer, Integer> memo) {
        if (memo.get(number) != null) {
            return memo.get(number);
        } else {
            int count = 0;
            if (numbers.contains(number)) {
                count = 1 + getNextConsecutiveCount(number + 1, numbers, memo);
                memo.put(number, count);
            }
            return count;
        }
    }
}
