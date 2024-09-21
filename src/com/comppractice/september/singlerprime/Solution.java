package com.comppractice.september.singlerprime;

import java.util.*;

class Solution {
    Set<Integer> primeNumbers = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13));

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {2, 5}};
        System.out.println(new Solution().countPaths(8, edges));
    }

    public long countPaths(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        Map<Integer, List<List<Integer>>> dict = new HashMap<>();

        // Build adjacency list from edges
        for (int[] edge : edges) {
            adjacencyList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        long result = 0;

        // Iterate over each node in primeNumbers
        for (int node : primeNumbers) {
            for (int neighbor : adjacencyList.getOrDefault(node, Collections.emptyList())) {
                if (!primeNumbers.contains(neighbor)) {
                    List<Integer> path = findNumber(adjacencyList, neighbor);
                    dict.computeIfAbsent(node, k -> new ArrayList<>()).add(path);
                    // Here we might accumulate some result based on the paths
                    result += path.size(); // Example: Counting the nodes in paths
                }
            }
        }
        System.out.println(dict);
        // Return the accumulated result
        return result;
    }

    private List<Integer> findNumber(Map<Integer, List<Integer>> adjacencyList, int nodeNum) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        queue.offer(nodeNum);
        Set<Integer> visited = new HashSet<>();
        visited.add(nodeNum);

        // BFS to find all reachable non-prime nodes
        while (!queue.isEmpty()) {
            int parentNode = queue.poll();
            path.add(parentNode);
            for (int childNode : adjacencyList.getOrDefault(parentNode, Collections.emptyList())) {
                if (!primeNumbers.contains(childNode) && visited.add(childNode)) {
                    queue.offer(childNode);
                }
            }
        }

        return path;
    }
}