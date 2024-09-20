package com.comppractice.august.digicertinterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * Q - Given an array of time intervals (start, end)(start and end time are inclusive) for classroom lectures
 * (possibly overlapping), find the minimum number of rooms required to run a class without disturbing other classes.
 * <p>
 * clsses can start at same time or can end at same time.
 * <p>
 * For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
 * <p>
 * For example, given [(0, 60), (0, 50), (31,90), (60, 150), (61, 150), ], you should return 3.
 * <p>
 * Also display classroom number for each time interval
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().classRoomRequired(new int[][] { { 30, 75 }, { 0, 50 }, { 60, 150 } }));
    }

    public Map<Integer, List<int[]>> classRoomRequired(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        Map<Integer, List<int[]>> classRoomDetails = new HashMap<>();

        priorityQueue.offer(intervals[0]);
        int classRoomReq = 1;
        classRoomDetails.computeIfAbsent(1, k -> new ArrayList<>()).add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek()[1] < intervals[i][0]) {
                priorityQueue.poll();
            }
            priorityQueue.offer(intervals[i]);
            classRoomReq = Math.max(priorityQueue.size(), classRoomReq);
            classRoomDetails.computeIfAbsent(classRoomReq, k -> new ArrayList<>()).add(intervals[i]);
        }

        return classRoomDetails;
    }
}
