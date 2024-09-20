package com.comppractice.august.howmanyrectangles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().countRectangles(new int[][] { { 1, 2 }, { 2, 3 }, { 2, 5 } },
                new int[][] { { 2, 1 }, { 1, 4 } })));
    }

    public int[] countRectangles(int[][] rectangles, int[][] points) {
        TreeMap<Integer, Set<int[]>> xRectangleCount = new TreeMap<>();
        TreeMap<Integer, Set<int[]>> yRectangleCount = new TreeMap<>();

        for (final int[] ints : rectangles) {
            xRectangleCount.computeIfAbsent(ints[0], x -> new HashSet<>()).add(ints);
            yRectangleCount.computeIfAbsent(ints[1], y -> new HashSet<>()).add(ints);
        }

        int[] result = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            Set<int[]> xRectangles = xRectangleCount.tailMap(points[i][0], true).values().stream().flatMap(Set::stream)
                    .collect(Collectors.toCollection(HashSet::new));
            Set<int[]> yRectangles = yRectangleCount.tailMap(points[i][1], true).values().stream().flatMap(Set::stream)
                    .collect(Collectors.toCollection(HashSet::new));

            int count = 0;
            for (int[] rectangle : xRectangles) {
                if (yRectangles.contains(rectangle)) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }
}
