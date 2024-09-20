package com.comppractice.august.removestones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().removeStones(
                new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } }));
    }

    public int removeStones(int[][] stones) {
        Map<Integer, Integer> countOnXCoordinate = new HashMap<>();
        Map<Integer, Integer> countOnYCoordinate = new HashMap<>();

        // Populate the maps with the count of stones on each coordinate
        for (int[] stone : stones) {
            countOnXCoordinate.put(stone[0], countOnXCoordinate.getOrDefault(stone[0], 0) + 1);
            countOnYCoordinate.put(stone[1], countOnYCoordinate.getOrDefault(stone[1], 0) + 1);
        }

        // Create a priority queue with a custom comparator based on the degree of stones
        PriorityQueue<int[]> queueStones = new PriorityQueue<>(
                Comparator.comparingInt(
                        stone -> getDegree(stone, countOnXCoordinate, countOnYCoordinate)
                )
        );

        Set<int[]> stoneSet = new HashSet<>(Arrays.asList(stones));
        queueStones.addAll(stoneSet);

        int count = 0;

        // Process the stones in the queue
        while (!queueStones.isEmpty()) {
            int[] removedStone = queueStones.poll();
            if (getDegree(removedStone, countOnXCoordinate, countOnYCoordinate) > 2) {
                stoneSet.remove(removedStone);

                // Update the maps after removing a stone
                countOnYCoordinate.put(removedStone[1], countOnYCoordinate.get(removedStone[1]) - 1);
                countOnXCoordinate.put(removedStone[0], countOnXCoordinate.get(removedStone[0]) - 1);
                count++;

                // Rebuild the priority queue to reflect the updated state of the maps
                queueStones.clear();
                queueStones.addAll(stoneSet);
            }
        }

        return count;
    }

    private static int getDegree(int[] stone, Map<Integer, Integer> countOnXCoordinate,
            Map<Integer, Integer> countOnYCoordinate) {
        return countOnXCoordinate.getOrDefault(stone[0], 0) + countOnYCoordinate.getOrDefault(stone[1], 0);
    }

}
