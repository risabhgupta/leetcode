package com.comppractice.august.minimumnumofarrowtoburstballon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findMinArrowShots(new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } }));
    }

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(x -> x[0]));

        int[] currentInterval = points[0];
        int countArrow = 1;

        for (int i = 1; i < points.length; i++) {
            final int[] point = points[i];
            if (currentInterval[1] > points[i][0]) {
                currentInterval[0] = points[i][0];
                currentInterval[1] = Math.min(points[i][1], currentInterval[1]);
            } else {
                countArrow++;
                currentInterval = point;
            }
        }

        return countArrow;
    }
}
