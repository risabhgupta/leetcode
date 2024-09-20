package com.comppractice.july.minimumneighbour2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().findTheCity(6,
                new int[][] { { 0, 3, 7 }, { 2, 4, 1 }, { 0, 1, 5 }, { 2, 3, 10 }, { 1, 3, 6 }, { 1, 2, 1 } }, 6));
    }
}

class Solution {
    private static int[][] getShortestDistanceMatrix(int n, int[][] edges) {
        int[][] shortestDistanceMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            shortestDistanceMatrix[i] = getShortestDistanceToNodes(i, n, getGraphAsMatrix(n, edges));
        }
        return shortestDistanceMatrix;
    }

    private static int[] getShortestDistanceToNodes(int startNode, int n, int[][] citiesDistance) {

        int[] shortestPath = new int[n];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        PriorityQueue<Integer> queueOrder = new PriorityQueue<>(Comparator.comparingInt(o -> shortestPath[o]));
        shortestPath[startNode] = 0;
        queueOrder.offer(startNode);

        Set<Integer> visitedNode = new HashSet<>();

        while (!queueOrder.isEmpty()) {
            Integer currentNode = queueOrder.poll();
            if (!visitedNode.contains(currentNode)) {
                for (int j = 0; j < n; j++) {
                    if (citiesDistance[currentNode][j] != Integer.MAX_VALUE) {
                        shortestPath[j] = Math.min(citiesDistance[currentNode][j] + shortestPath[currentNode],
                                shortestPath[j]);
                        queueOrder.offer(j);
                    }
                }
                visitedNode.add(currentNode);
            }
        }

        return shortestPath;
    }

    public static int[][] getGraphAsMatrix(final int n, final int[][] edges) {
        int[][] citiesDistance = new int[n][n];
        for (int[] cityDistance : citiesDistance) {
            Arrays.fill(cityDistance, Integer.MAX_VALUE);
        }

        for (int[] edge : edges) {
            citiesDistance[edge[0]][edge[1]] = edge[2];
            citiesDistance[edge[1]][edge[0]] = edge[2];
        }
        return citiesDistance;
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int[][] shortestDistanceMatrix = getShortestDistanceMatrix(n, edges);

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
