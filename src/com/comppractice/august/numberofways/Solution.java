package com.comppractice.august.numberofways;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countPaths(7,
                new int[][] { { 0, 6, 7 }, { 0, 1, 2 }, { 1, 2, 3 }, { 1, 3, 3 }, { 6, 3, 3 }, { 3, 5, 1 }, { 6, 5, 1 },
                        { 2, 5, 1 }, { 0, 4, 5 }, { 4, 6, 2 } }
        ));
    }

    public int countPaths(int n, int[][] roads) {
        int[] minimumCostTravel = new int[n];
        Arrays.fill(minimumCostTravel, Integer.MAX_VALUE);
        int[] numberOfWays = new int[n];

        Map<Integer, List<int[]>> adjacencyList = new HashMap<>();

        for (int[] road : roads) {
            adjacencyList.computeIfAbsent(road[0], k -> new LinkedList<>()).add(new int[] { road[1], road[2] });
            adjacencyList.computeIfAbsent(road[1], k -> new LinkedList<>()).add(new int[] { road[0], road[2] });
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(road -> road[1]));
        priorityQueue.offer(new int[] { 0, 0 });
        numberOfWays[0] = 1;

        while (!priorityQueue.isEmpty()) {
            int[] currentCityWeight = priorityQueue.poll();
            int currentCity = currentCityWeight[0];

            for (int[] nextRoad : adjacencyList.getOrDefault(currentCity, Collections.emptyList())) {
                int nextCost = currentCityWeight[1] + nextRoad[1];
                if (nextCost < minimumCostTravel[nextRoad[0]]) {
                    numberOfWays[nextRoad[0]] = 1;
                    minimumCostTravel[nextRoad[0]] = nextCost;
                    priorityQueue.offer(new int[] { nextRoad[0], nextCost });
                } else if (nextCost == minimumCostTravel[nextRoad[0]]) {
                    numberOfWays[nextRoad[0]] = numberOfWays[nextRoad[0]] + numberOfWays[currentCity];
                    priorityQueue.offer(new int[] { nextRoad[0], nextCost });
                }
            }
        }
        return numberOfWays[n - 1];
    }
}
