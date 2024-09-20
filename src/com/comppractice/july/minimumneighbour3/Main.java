package com.comppractice.july.minimumneighbour3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().findTheCity(6,
                new int[][] { { 0, 3, 7 }, { 2, 4, 1 }, { 0, 1, 5 }, { 2, 3, 10 }, { 1, 3, 6 }, { 1, 2, 1 } }, 6));
    }
}

class Solution {
    private static int[][] getShortestDistanceMatrix(int n, int[][] citiesDistance) {
        int[][] shortestDistanceMatrix = new int[n][n];
        for (int i = 0; i < shortestDistanceMatrix.length; i++) {
            shortestDistanceMatrix[i] = Arrays.copyOf(citiesDistance[i], citiesDistance[i].length);
        }

        for (int via = 0; via < n; via++) {
            for (int startNode = 0; startNode < n; startNode++) {
                for (int destinationNode = 0; destinationNode < n; destinationNode++) {
                    shortestDistanceMatrix[startNode][destinationNode] = Math.min(
                            shortestDistanceMatrix[startNode][destinationNode],
                            getTotalPath(shortestDistanceMatrix[startNode][via],
                                    shortestDistanceMatrix[via][destinationNode]));
                }
            }
        }
        return shortestDistanceMatrix;
    }

    private static int getTotalPath(final int pathLength1, final int pathLength2) {
        if (pathLength1 == Integer.MAX_VALUE || pathLength2 == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return pathLength1 + pathLength2;
    }

    private static int[][] getGraphAsMatrix(final int n, final int[][] edges) {
        int[][] citiesDistance = new int[n][n];
        int i = 0;
        for (int[] cityDistance : citiesDistance) {
            Arrays.fill(cityDistance, Integer.MAX_VALUE);
            cityDistance[i] = 0;
            i++;
        }

        for (int[] edge : edges) {
            citiesDistance[edge[0]][edge[1]] = edge[2];
            citiesDistance[edge[1]][edge[0]] = edge[2];
        }
        return citiesDistance;
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] shortestDistanceMatrix = getShortestDistanceMatrix(n, getGraphAsMatrix(n, edges));

        List<Long> countOfCitiesUnderThreshold = Stream.of(shortestDistanceMatrix)
                .map(array -> Arrays.stream(array).filter(distance -> distance <= distanceThreshold).count())
                .collect(Collectors.toList());

        int temp = 0;
        for (int i = 0; i < countOfCitiesUnderThreshold.size(); i++) {
            if (countOfCitiesUnderThreshold.get(temp) >= countOfCitiesUnderThreshold.get(i)) {
                temp = i;
            }
        }
        return temp;
    }
}
