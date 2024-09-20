package com.comppractice.august.dijikstraexample;

import java.util.*;


class Node implements Comparable<Node> {
    int vertex, weight;

    Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override public int compareTo(Node other) {
        return this.weight - other.weight; // For min-heap
    }
}

public class Solution {
    public int[] dijkstra(int[][] graph, int source) {
        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int u = currentNode.vertex;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0) { // Check if there is an edge
                    int newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.offer(new Node(v, dist[v]));
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = { { 0, 10, 20, 0, 0, 0 }, { 10, 0, 0, 50, 10, 0 }, { 20, 0, 0, 20, 33, 0 },
                { 0, 50, 20, 0, 20, 2 }, { 0, 10, 33, 20, 0, 1 }, { 0, 0, 0, 2, 1, 0 } };

        Solution solution = new Solution();
        int source = 0;
        int[] distances = solution.dijkstra(graph, source);

        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < distances.length; i++) {
            System.out.println(i + "\t\t" + distances[i]);
        }
    }
}
