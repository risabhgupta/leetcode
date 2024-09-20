package com.comppractice.july.duplicatethird;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[] { 1, 0, 1, 1 }, 1, 2));
    }
}

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int finalI = i;
            if (frequency.keySet().stream().anyMatch(key -> (nums[finalI] <= key + valueDiff) && (nums[finalI] >= key -
                    valueDiff))) {
                return true;
            }

            int count = 0;
            if (frequency.get(nums[i]) != null) {
                count = frequency.get(nums[i]);
            }
            frequency.put(nums[i], count + 1);

            if (i - indexDiff >= 0) {
                if (frequency.get(nums[i - indexDiff]) == 1) {
                    frequency.remove(nums[i - indexDiff]);
                } else {
                    frequency.put(nums[i - indexDiff], frequency.get(nums[i - indexDiff]) - 1);
                }
            }
        }
        return false;
    }
}