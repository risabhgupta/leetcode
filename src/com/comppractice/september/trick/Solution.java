package com.comppractice.september.trick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        int[][] edges = { { 67, 29 }, { 13, 29 }, { 77, 29 }, { 36, 29 }, { 82, 29 }, { 54, 29 }, { 57, 29 },
                { 53, 29 }, { 68, 29 }, { 26, 29 }, { 21, 29 }, { 46, 29 }, { 41, 29 }, { 45, 29 }, { 56, 29 },
                { 88, 29 }, { 2, 29 }, { 7, 29 }, { 5, 29 }, { 16, 29 }, { 37, 29 }, { 50, 29 }, { 79, 29 }, { 91, 29 },
                { 48, 29 }, { 87, 29 }, { 25, 29 }, { 80, 29 }, { 71, 29 }, { 9, 29 }, { 78, 29 }, { 33, 29 },
                { 4, 29 }, { 44, 29 }, { 72, 29 }, { 65, 29 }, { 61, 29 } };
        System.out.println(new Solution().magnificentSets(92, edges));
    }

    public int magnificentSets(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] edge : edges) {
            adjacencyList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        if (!isBipartite(adjacencyList, 1)) {
            return -1;
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            int totalLevels = getTotalLevels(adjacencyList, i, n);
            if (totalLevels == -1) {
                return -1;
            }
            max = Math.max(max, totalLevels);
        }

        return max + n - adjacencyList.size();
    }

    private boolean isBipartite(Map<Integer, List<Integer>> adjacencyList, int start) {
        Map<Integer, Integer> colorMap = new HashMap<>(); // Stores the color of each node (0 or 1)
        Queue<Integer> queue = new LinkedList<>();

        // Start BFS from the start node
        queue.offer(start);
        colorMap.put(start, 0); // Assign the first color (0) to the start node

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentColor = colorMap.get(node);  // Get the color of the current node

            // Traverse all neighbors of the current node
            for (int neighbor : adjacencyList.getOrDefault(node, new ArrayList<>())) {
                // If the neighbor hasn't been colored yet, assign the opposite color
                if (!colorMap.containsKey(neighbor)) {
                    colorMap.put(neighbor, 1 - currentColor); // Alternate color
                    queue.offer(neighbor);
                }
                // If the neighbor is already colored and has the same color as the current node, the graph is not
                // bipartite
                else if (colorMap.get(neighbor) == currentColor) {
                    return false;
                }
            }
        }

        return true;  // The graph is bipartite if no conflicting colors were found
    }

    private int getTotalLevels(Map<Integer, List<Integer>> adjacencyList, int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();  // To keep track of visited nodes
        queue.offer(start);
        visited.add(start);
        int levels = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int element = queue.poll();

                // Add all unvisited neighbors of the current node to the queue
                for (int neighbor : adjacencyList.getOrDefault(element, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            levels++;
        }

        return levels;
    }

}
