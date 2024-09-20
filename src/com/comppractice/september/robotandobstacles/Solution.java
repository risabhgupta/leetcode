package com.comppractice.september.robotandobstacles;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


class Solution {

    public static final TreeSet<Integer> EMPTY_TREESET = new TreeSet<>();

    public static void main(String[] args) {
        int[] commands = { 4, -1, 4, -2, 4 };
        int[][] obstacles = { { 2, 4 } };
        System.out.println(new Solution().robotSim(commands, obstacles));
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int[] currentCoordinate = { 0, 0 };
        int direction = 0;
        int distance = 0;

        Map<Integer, TreeSet<Integer>> obstacleOnXAxis = new HashMap<>();
        Map<Integer, TreeSet<Integer>> obstacleOnYAxis = new HashMap<>();

        for (int[] obstacle : obstacles) {
            obstacleOnYAxis.computeIfAbsent(obstacle[0], k -> new TreeSet<>()).add(obstacle[1]);
            obstacleOnXAxis.computeIfAbsent(obstacle[1], k -> new TreeSet<>()).add(obstacle[0]);
        }

        for (final int command : commands) {
            if (command == -1) {
                direction = (direction + 1 + 4) % 4;
            } else if (command == -2) {
                direction = (direction - 1 + 4) % 4;
            } else {
                Integer obstacleIndex;
                switch (direction) {
                    case 0:
                        obstacleIndex = obstacleOnYAxis.getOrDefault(currentCoordinate[0], EMPTY_TREESET)
                                .ceiling(currentCoordinate[1] + 1);

                        currentCoordinate[1] = Math.min(obstacleIndex == null ? Integer.MAX_VALUE : obstacleIndex - 1,
                                currentCoordinate[1] + command);
                        break;
                    case 2:
                        obstacleIndex = obstacleOnYAxis.getOrDefault(currentCoordinate[0], EMPTY_TREESET)
                                .floor(currentCoordinate[1] - 1);

                        currentCoordinate[1] = Math.max(obstacleIndex == null ? Integer.MIN_VALUE : obstacleIndex + 1,
                                currentCoordinate[1] - command);
                        break;
                    case 1:
                        obstacleIndex = obstacleOnXAxis.getOrDefault(currentCoordinate[1], EMPTY_TREESET)
                                .ceiling(currentCoordinate[0] + 1);

                        currentCoordinate[0] = Math.min(obstacleIndex == null ? Integer.MAX_VALUE : obstacleIndex - 1,
                                currentCoordinate[0] + command);
                        break;
                    case 3:
                        obstacleIndex = obstacleOnXAxis.getOrDefault(currentCoordinate[1], EMPTY_TREESET)
                                .floor(currentCoordinate[0] - 1);

                        currentCoordinate[0] = Math.max(obstacleIndex == null ? Integer.MIN_VALUE : obstacleIndex + 1,
                                currentCoordinate[0] - command);
                        break;
                }
            }
            distance = Math.max(distance,
                    currentCoordinate[0] * currentCoordinate[0] + currentCoordinate[1] * currentCoordinate[1]);
        }
        return distance;
    }
}
