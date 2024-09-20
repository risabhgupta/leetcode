package com.comppractice.september.minimumtime;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findMinDifference(Arrays.asList(new String[] { "23:59", "00:00" })));
    }

    public int findMinDifference(List<String> timePoints) {
        boolean[] time = new boolean[1440];
        int prev = -1;
        int first = -1;
        int minDiff = Integer.MAX_VALUE;
        for (String timePoint : timePoints) {
            int minutes = getMinutes(timePoint);
            if (time[minutes]) {
                return 0;
            }
            time[minutes] = true;
        }

        for (int i = 0; i < 1440; i++) {
            if (time[i]) {
                if (first == -1) {
                    first = i;
                }
                if (prev != -1) {
                    minDiff = Math.min(minDiff, i - prev);
                }
                prev = i;
            }
        }
        return Math.min(minDiff, 1440 + first - prev);
    }

    private int getMinutes(String s) {
        char[] arr = s.toCharArray();
        return (((10 * (arr[0] - '0')) + (arr[1] - '0')) * 60) + ((arr[3] - '0') * 10) + arr[4] - '0';
    }

    private int getNearest(TreeSet<Integer> set, int num) {
        Integer a = set.floor(num);
        Integer b = set.ceiling(num);

        int min = Integer.MAX_VALUE;

        if (a != null) {
            min = num - a;
        }

        if (b != null) {
            min = Math.min(min, b - num);
        }

        return min;
    }
}
