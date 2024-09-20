package com.comppractice.august.pathwithmaxprob;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProbability(6,
                new int[][] { { 2, 0 }, { 2, 3 }, { 2, 5 }, { 2, 4 }, { 5, 3 }, { 3, 1 }, { 0, 3 }, { 4, 5 },
                        { 5, 0 } },
                new double[] { 0.8701, 0.9375, 0.5994, 0.1174, 0.6767, 0.6912, 0.0488, 0.1562, 0.9872 }, 5, 3));

    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        double[] maxProbTillNow = new double[n];
        Map<Integer, List<double[]>> graph = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], k -> new LinkedList<>()).add(new double[] { edges[i][1], succProb[i] });
            graph.computeIfAbsent(edges[i][1], k -> new LinkedList<>()).add(new double[] { edges[i][0], succProb[i] });
        }

        PriorityQueue<double[]> queue = new PriorityQueue<>(Comparator.comparingDouble(x -> -x[1]));
        boolean[] visited = new boolean[n];

        queue.offer(new double[] { start_node, 1 });

        while (!queue.isEmpty() && queue.peek()[0] != end_node) {
            double[] nodeToVisit = queue.poll();
            int currentNode = (int) nodeToVisit[0];

            visited[currentNode] = true;
            for (double[] path : graph.getOrDefault(currentNode, Collections.emptyList())) {
                int i = (int) path[0];
                double probabilityOfNextNode = nodeToVisit[1] * path[1];
                if (!visited[i] && maxProbTillNow[i] < probabilityOfNextNode) {
                    queue.offer(new double[] { i, probabilityOfNextNode });
                    maxProbTillNow[i] = probabilityOfNextNode;
                    System.out.println(queue.size());
                }
            }
        }

        return maxProbTillNow[end_node];
    }
}