package com.comppractice.august.maximumsegmentsum2;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


public class Solution {

    public void maximumSegmentSum(int[] nums, int[] removeQueries) {
        long[] result = new long[removeQueries.length];

        long[] sum = new long[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        TreeMap<Integer, Long> segmentMap = new TreeMap<>();
        segmentMap.put(0, sum[nums.length]);

        for (int i = 0; i < removeQueries.length; i++) {
            Map.Entry<Integer, Long> nearestEntry = segmentMap.floorEntry(removeQueries[i]);

            int startIndex = nearestEntry.getKey();
            long startValue = nearestEntry.getValue();

            segmentMap.remove(removeQueries[i]);

            if (startIndex < removeQueries[i]) {
                segmentMap.put(startIndex, sum[removeQueries[i]] - sum[startIndex]);
            }
        }
        return;
    }
}
