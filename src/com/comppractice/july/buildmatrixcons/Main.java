package com.comppractice.july.buildmatrixcons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(
                new Solution().buildMatrix(3, new int[][] { { 1, 2 }, { 3, 2 } }, new int[][] { { 2, 1 }, { 3, 2 } })));
    }
}

class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        Node[] rowGraph = createGraph(rowConditions, k);
        Node[] columnGraph = createGraph(colConditions, k);

        List<Integer> sortedRowElements = getSortedElementsTopologically(k, rowGraph);
        List<Integer> sortedColumnElements = getSortedElementsTopologically(k, columnGraph);

        int[][] matrix = new int[k][k];

        for (int number = 1; number <= k; number++) {
            int rowIndex = sortedRowElements.indexOf(number);
            int columnIndex = sortedColumnElements.indexOf(number);
            if (rowIndex < 0 || columnIndex < 0) {
                matrix = new int[0][0];
                break;
            }
            matrix[rowIndex][columnIndex] = number;
        }
        return matrix;
    }

    private List<Integer> getSortedElementsTopologically(final int k, final Node[] rowGraph) {
        List<Integer> sortedElements = new ArrayList<>();
        boolean[] nodeVisited = new boolean[k];
        boolean[] nodeVisitedInPath = new boolean[k];
        for (Node node : rowGraph) {
            traverseGraphAndAddElements(node, nodeVisited, nodeVisitedInPath, sortedElements);
        }
        Collections.reverse(sortedElements);
        return sortedElements;
    }

    private void traverseGraphAndAddElements(Node node, boolean[] nodeVisited, boolean[] nodeVisitedInCurrentPath,
            List<Integer> sortedElements) {
        if (nodeVisitedInCurrentPath[node.value - 1]) {
            return;
        }

        if (nodeVisited[node.value - 1]) {
            return;
        }

        nodeVisitedInCurrentPath[node.value - 1] = true;
        for (Node childNode : node.nodes) {
            traverseGraphAndAddElements(childNode, nodeVisited, nodeVisitedInCurrentPath, sortedElements);
        }

        if (node.nodes.stream().allMatch(childNode -> nodeVisited[childNode.value - 1])) {
            sortedElements.add(node.value);
        }

        nodeVisitedInCurrentPath[node.value - 1] = false;
        nodeVisited[node.value - 1] = true;
    }

    private Node[] createGraph(final int[][] conditions, int numberOfNodes) {
        Node[] nodes = new Node[numberOfNodes];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (int[] condition : conditions) {
            nodes[condition[0] - 1].nodes.add(nodes[condition[1] - 1]);
        }

        return nodes;
    }

    static class Node {
        List<Node> nodes;
        int value;

        public Node(int value) {
            this.value = value;
            this.nodes = new ArrayList<>();
        }
    }

}