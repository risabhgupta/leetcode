package com.comppractice.august.maximumsegmentsum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().maximumSegmentSum(
                        new int[] { 500, 822, 202, 707, 298, 484, 311, 680, 901, 319, 343, 340 },
                        new int[] { 6, 4, 0, 5, 2, 3, 10, 8, 7, 9, 1, 11 })));
    }

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        long[] result = new long[removeQueries.length];

        long[] sum = new long[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        Set<Node> nodeSet = new HashSet<>();
        Node node = new Node(0, nums.length - 1, sum[nums.length] - sum[0]);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(
                Comparator.comparingLong(nodeIns -> -nodeIns.segmentSum));
        priorityQueue.offer(node);

        for (int i = 0; i < removeQueries.length - 1; i++) {
            result[i] = removeAndQuery(node, removeQueries[i], nodeSet, priorityQueue, sum);
        }
        return result;
    }

    private long removeAndQuery(Node node, int removeIndex, Set<Node> nodes, PriorityQueue<Node> nodesQueue,
            long[] sum) {
        removeAndInsert(node, removeIndex, nodes, nodesQueue, sum);
        while (nodes.contains(nodesQueue.peek())) {
            nodesQueue.poll();
        }
        Node resultNode = nodesQueue.peek();
        return resultNode.segmentSum;
    }

    private void removeAndInsert(Node node, int index, Set<Node> removedNodeSet, PriorityQueue<Node> priorityQueue,
            long[] sum) {
        if (node.leftNode != null && index <= node.leftNode.upperBound) {
            removeAndInsert(node.leftNode, index, removedNodeSet, priorityQueue, sum);
        } else if (node.rightNode != null && index >= node.rightNode.lowerBound) {
            removeAndInsert(node.rightNode, index, removedNodeSet, priorityQueue, sum);
        } else if (node.lowerBound <= index && node.upperBound >= index) {
            removedNodeSet.add(node);
            priorityQueue.remove(node);

            if (node.lowerBound < index) {
                Node leftNode = new Node(node.lowerBound, index - 1, sum[index] - sum[node.lowerBound]);
                priorityQueue.offer(leftNode);
                node.leftNode = leftNode;
            }

            if (node.upperBound > index) {
                Node rightNode = new Node(index + 1, node.upperBound, sum[node.upperBound + 1] - sum[index + 1]);
                priorityQueue.offer(rightNode);
                node.rightNode = rightNode;
            }
        }
    }
}

class Node {
    int upperBound;
    int lowerBound;
    long segmentSum;

    Node rightNode;
    Node leftNode;

    public Node(int lowerBound, int upperBound, long segmentSum) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.segmentSum = segmentSum;
    }
}
