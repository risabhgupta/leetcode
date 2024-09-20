package com.comppractice.august.numberofprovinces;

import java.util.Stack;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findCircleNum(
                new int[][] { { 1, 0, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 }, { 1, 0, 1, 1 } }));
    }

    public int findCircleNum(int[][] isConnected) {
        int numberOfCircles = 0;
        boolean[] visited = new boolean[isConnected.length];
        boolean[] visitedInContext = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                dfsStack(isConnected, i, visited, visitedInContext);
                numberOfCircles++;
            }
        }
        return numberOfCircles;
    }

    private int dfs(final int[][] isConnected, int city, final boolean[] visited, final boolean[] visitedInContext) {
        if (visited[city] || visitedInContext[city]) {
            return 0;
        } else {
            int numberOfConnectedCities = 0;
            visitedInContext[city] = true;
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[city][i] == 1) {
                    numberOfConnectedCities += (1 + dfs(isConnected, i, visited, visitedInContext));
                }
            }
            visitedInContext[city] = false;
            visited[city] = true;
            return numberOfConnectedCities;
        }
    }

    private void dfsStack(final int[][] isConnected, int city, final boolean[] visited,
            final boolean[] visitedInContext) {
        Stack<Integer> stack = new Stack<>();
        stack.push(city);
        while (!stack.isEmpty()) {
            int currentCity = stack.pop();
            visited[currentCity] = true;
            visitedInContext[currentCity] = true;
            for (int i = 0; i < isConnected.length; i++) {
                if (!visited[i] && !visitedInContext[i] && isConnected[currentCity][i] == 1) {
                    stack.push(i);
                }
            }
            visitedInContext[currentCity] = false;
        }
    }
}