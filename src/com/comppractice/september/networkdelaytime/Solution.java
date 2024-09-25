package com.comppractice.september.networkdelaytime;

import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] edge : times) {
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        Set<Integer> visited = new HashSet<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node[1]));
        pq.offer(new int[]{k, 0});

        int time = 0;
        while (!pq.isEmpty()) {
            int[] currentNodeWeight = pq.poll();
            int currentNode = currentNodeWeight[0];
            int currentWeight = currentNodeWeight[1];

            visited.add(currentNode);
            time = currentWeight;

            for (int[] edge : adj.getOrDefault(currentNode, Collections.emptyList())) {
                int nextNode = edge[1];
                if (!visited.contains(nextNode)) {
                    int nextWeight = currentWeight + edge[2];
                    pq.offer(new int[]{nextNode, nextWeight});
                }
            }
        }

        return visited.size() < n ? -1 : time;
    }
}