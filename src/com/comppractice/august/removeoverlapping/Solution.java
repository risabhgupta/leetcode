package com.comppractice.august.removeoverlapping;

import java.util.Arrays;
import java.util.Comparator;


class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[1]));

        int countDelete = 0;
        int[] currentInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (currentInterval[1] > intervals[i][0]) {
                countDelete++;
            } else {
                currentInterval = intervals[i];
            }
        }

        return countDelete;
    }
}
