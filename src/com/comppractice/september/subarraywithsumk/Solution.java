package com.comppractice.september.subarraywithsumk;

import java.util.HashMap;
import java.util.Map;


class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumCount = new HashMap<>();
        int sum = 0;
        int res = 0;
        preSumCount.put(0, 1);

        for (int num : nums) {
            sum += num;
            res += preSumCount.getOrDefault(sum - k, 0);
            preSumCount.put(sum, preSumCount.getOrDefault(sum, 0) + 1);
        }

        return res;
    }
}
