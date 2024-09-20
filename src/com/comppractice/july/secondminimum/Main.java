package com.comppractice.july.secondminimum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        int n = 2;
        int time = 3;
        int change = 5;
        int[][] edges = { { 1, 2 } };
        System.out.println(new Solution().secondMinimum(n, edges, 3, 2));
    }
}

class Solution {
    public static int[][] getGraphAsMatrix(final int n, final int[][] edges, final int weight) {
        int[][] graphMatrix = new int[n][n];
        for (int i = 0; i < graphMatrix.length; i++) {
            Arrays.fill(graphMatrix[i], Integer.MAX_VALUE);
            graphMatrix[i][i] = 0;
        }

        for (int[] edge : edges) {
            graphMatrix[edge[0] - 1][edge[1] - 1] = weight;
            graphMatrix[edge[1] - 1][edge[0] - 1] = weight;
        }
        return graphMatrix;
    }

    public static void depthFirstTraversal(int[][] graphMatrix, int[] pathVisited, int currentNode, int destinationNode,
            int currentTime, int changeTime, List<Integer> availableTime) {
        if (pathVisited[currentNode] >= 2) {
            return;
        }
        if (currentNode == destinationNode) {
            availableTime.add(currentTime);
        }

        pathVisited[currentNode]++;
        for (int i = 0; i < graphMatrix.length; i++) {
            if (i != currentNode && graphMatrix[currentNode][i] != Integer.MAX_VALUE) {
                depthFirstTraversal(graphMatrix, pathVisited, i, destinationNode,
                        currentTime + graphMatrix[currentNode][i] + (((currentTime / changeTime) % 2) * (changeTime - (
                                currentTime % changeTime))), changeTime, availableTime);
            }
        }
        pathVisited[currentNode]--;
    }

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int[][] graphMatrix = getGraphAsMatrix(n, edges, time);
        List<Integer> traversalTimeAvailable = new ArrayList<>();

        int[] visitedTimes = new int[n];
        Arrays.fill(visitedTimes, 0);

        depthFirstTraversal(graphMatrix, visitedTimes, 0, n - 1, 0, change, traversalTimeAvailable);

        Collections.sort(traversalTimeAvailable);
        return traversalTimeAvailable.get(1);
    }
}