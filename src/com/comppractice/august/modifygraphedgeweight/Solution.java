package com.comppractice.august.modifygraphedgeweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        int[][] weightedGraph = { { 4, 1, -1 }, { 2, 0, -1 }, { 0, 3, -1 }, { 4, 3, -1 } };
        System.out.println(Arrays.deepToString(new Solution().modifiedGraphEdges(5, weightedGraph, 0, 1, 5)));
    }

    static class Graph {
        Map<Integer, List<int[]>> adjacencyList;

        Graph(int n, int[][] edges) {
            adjacencyList = new HashMap<>();
            for (int[] edge : edges) {
                adjacencyList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[] { edge[1], edge[2] });
                adjacencyList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new int[] { edge[0], edge[2] });
            }
        }

        public int getShortestPath(int source, int destination) {
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.currentWeight));
            Map<Integer, Integer> distances = new HashMap<>();

            priorityQueue.offer(new Node(source, 0));
            distances.put(source, 0);

            while (!priorityQueue.isEmpty()) {
                Node currentNode = priorityQueue.poll();
                int currentPos = currentNode.currentPos;

                if (currentPos == destination) {
                    return currentNode.currentWeight;
                }

                for (int[] edge : adjacencyList.getOrDefault(currentPos, Collections.emptyList())) {
                    int neighbor = edge[0];
                    int weight = edge[1];
                    if (weight > 0) {
                        int newDist = currentNode.currentWeight + weight;
                        if (newDist < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                            distances.put(neighbor, newDist);
                            priorityQueue.offer(new Node(neighbor, newDist));
                        }
                    }
                }
            }

            return -1;  // Return -1 if the destination is unreachable
        }

    }

    static class Node {
        int currentPos;
        int currentWeight;

        Node(final int currentPos, final int currentWeight) {
            this.currentPos = currentPos;
            this.currentWeight = currentWeight;
        }
    }

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        Graph graph = new Graph(n, edges);
        int[] lastEdgeModified = null;
        int distance = graph.getShortestPath(source, destination);
        if (distance != -1 && target > distance) {
            return new int[0][0];
        } else if (target == distance) {
            return edges;
        } else {
            for (int[] edge : edges) {
                if (edge[2] == -1) {
                    graph = new Graph(n, edges);
                    int sourceToThis = graph.getShortestPath(source, edge[0]);
                    int nextToDest = graph.getShortestPath(edge[1], destination);
                    if (sourceToThis != -1 && nextToDest != -1 && (sourceToThis + nextToDest < target)) {
                        edge[2] = target - sourceToThis - nextToDest;
                        target -= edge[2];
                        lastEdgeModified = edge;
                    } else {
                        edge[2] = 1;
                        lastEdgeModified = edge;
                        target--;
                    }
                }
            }
        }
        lastEdgeModified[2] += target;
        return edges;
    }

}
