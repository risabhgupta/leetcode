package com.comppractice.september.safestwalk;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Integer[][] grid = {{1, 1}, {1, 1}};

        List<List<Integer>> grids = new ArrayList<>();
        for (Integer[] row : grid) {
            grids.add(Arrays.asList(row));
        }

        System.out.println(new Solution().findSafeWalk(grids, 4));
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        Map<Integer, List<int[]>> adjacencyList = new HashMap<>();
        int rowLength = grid.size();
        int colLength = grid.get(0).size();
        int totalNodes = rowLength * colLength;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                int current = (i * colLength) + j;
                int right = j + 1 < colLength ? i * colLength + (j + 1) : -1;
                int down = i + 1 < rowLength ? (i + 1) * colLength + j : -1;
                int left = j - 1 >= 0 ? i * colLength + j - 1 : -1;
                int up = i - 1 >= 0 ? (i - 1) * colLength + j : -1;

                //add right
                if (right >= 0) {
                    adjacencyList.computeIfAbsent(current, k -> new ArrayList<>()).add(new int[]{right, grid.get(i).get(j + 1)});
                }
                //add left
                if (left >= 0) {
                    adjacencyList.computeIfAbsent(current, k -> new ArrayList<>()).add(new int[]{left, grid.get(i).get(j - 1)});
                }
                //add up
                if (up >= 0) {
                    adjacencyList.computeIfAbsent(current, k -> new ArrayList<>()).add(new int[]{up, grid.get(i - 1).get(j)});
                }
                //add down
                if (down >= 0) {
                    adjacencyList.computeIfAbsent(current, k -> new ArrayList<>()).add(new int[]{down, grid.get(i + 1).get(j)});
                }
            }
        }

        return getShortestPathDistance(0, totalNodes - 1, adjacencyList, health - grid.get(0).get(0));
    }

    private boolean getShortestPathDistance(int source, int destination, Map<Integer, List<int[]>> adjacencyList, int health) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(k -> k[1]));
        queue.offer(new int[]{source, 0});
        boolean[] visited = new boolean[destination + 1];


        while (!queue.isEmpty()) {
            int[] currentNode = queue.poll();
            if(health - currentNode[1] < 1) {
                return false;
            }
            if (currentNode[0] == destination) {
                return true;
            }
            visited[currentNode[0]] = true;


            for (int[] node : adjacencyList.getOrDefault(currentNode[0], Collections.emptyList())) {
                int nextNode = node[0];
                if (!visited[nextNode] && currentNode[1] + node[1] <= health - 1) {
                    queue.offer(new int[]{nextNode, currentNode[1] + node[1]});
                }
            }
        }
        return false;
    }
}
