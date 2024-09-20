package com.comppractice.august.intervalscontainer;

import java.util.*;


/**
 * Scenario:
 * <p>
 * You are working on a Java application that processes a large set of integer data. The application needs to
 * categorize these integers into different groups based on predefined intervals. Each interval is defined by a start
 * and an end value, inclusive.
 * <p>
 * Task:
 * <p>
 * Given a list of integers and a set of predefined intervals, your task is to group the integers according to these
 * intervals.
 * [1, 4, 8, 15, 23, 5, 16, 30, 19]
 * Numbers in interval [1, 10]: [1, 4, 5, 8]
 * Numbers in interval [11, 20]: [15, 16, 19]
 * Numbers in interval [21, 30]: [23, 30]
 */

public class Solution1 {

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> numbers1 = Arrays.asList(1, 4, 8, 15, 23, 5, 16, 30, 19);
        List<int[]> intervals1 = Arrays.asList(new int[] { 1, 10 }, new int[] { 11, 20 }, new int[] { 21, 30 });
        System.out.println("Test Case 1:");
        runTest(numbers1, intervals1);
        // Expected Output:
        // Numbers in interval [1, 10]: [1, 4, 5, 8]
        // Numbers in interval [11, 20]: [15, 16, 19]
        // Numbers in interval [21, 30]: [23, 30]

        // Test Case 2: Empty list of numbers
        List<Integer> numbers2 = new ArrayList<>();
        List<int[]> intervals2 = Arrays.asList(new int[] { 1, 10 }, new int[] { 11, 20 }, new int[] { 21, 30 });
        System.out.println("Test Case 2:");
        runTest(numbers2, intervals2);
        // Expected Output:
        // Numbers in interval [1, 10]: []
        // Numbers in interval [11, 20]: []
        // Numbers in interval [21, 30]: []

        // Test Case 3: Numbers out of intervals' range
        List<Integer> numbers3 = Arrays.asList(100, 200, 300);
        List<int[]> intervals3 = Arrays.asList(new int[] { 1, 10 }, new int[] { 11, 20 }, new int[] { 21, 30 });
        System.out.println("Test Case 3:");
        runTest(numbers3, intervals3);
        // Expected Output:
        // Numbers in interval [1, 10]: []
        // Numbers in interval [11, 20]: []
        // Numbers in interval [21, 30]: []

        // Test Case 4: Overlapping intervals (although the problem assumes non-overlapping intervals)
        List<Integer> numbers4 = Arrays.asList(5, 15, 25);
        List<int[]> intervals4 = Arrays.asList(new int[] { 1, 15 }, new int[] { 10, 25 });
        System.out.println("Test Case 4:");
        runTest(numbers4, intervals4);
        // Expected Output:
        // Numbers in interval [1, 15]: [5, 15]
        // Numbers in interval [10, 25]: [15, 25]

        // Test Case 5: Large range of intervals
        List<Integer> numbers5 = Arrays.asList(100, 200, 300, 400, 500);
        List<int[]> intervals5 = Arrays.asList(new int[] { 50, 150 }, new int[] { 150, 250 }, new int[] { 250, 350 },
                new int[] { 350, 450 }, new int[] { 450, 550 });
        System.out.println("Test Case 5:");
        runTest(numbers5, intervals5);
        // Expected Output:
        // Numbers in interval [50, 150]: [100]
        // Numbers in interval [150, 250]: [200]
        // Numbers in interval [250, 350]: [300]
        // Numbers in interval [350, 450]: [400]
        // Numbers in interval [450, 550]: [500]
    }

    private static void runTest(List<Integer> numbers, List<int[]> intervals) {
        Map<int[], List<Integer>> groupedNumbers = groupNumbersByIntervals(numbers, intervals);

        // Print the grouped numbers
        for (Map.Entry<int[], List<Integer>> entry : groupedNumbers.entrySet()) {
            int[] interval = entry.getKey();
            List<Integer> groupedList = entry.getValue();
            System.out.println("Numbers in interval [" + interval[0] + ", " + interval[1] + "]: " + groupedList);
        }
        System.out.println();
    }

    public static Map<int[], List<Integer>> groupNumbersByIntervals(List<Integer> numbers, List<int[]> intervals) {
        TreeSet<Integer> set = new TreeSet<>(numbers);

        Map<int[], List<Integer>> groupedNumbers = new HashMap<>();
        for (int[] interval : intervals) {
            groupedNumbers.put(interval, new ArrayList<>(set.subSet(interval[0], true, interval[1], true)));
        }
        return groupedNumbers;
    }
}

