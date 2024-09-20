package com.comppractice.august.kthlargestelement;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[] { 4, 5, 8, 2 });
        System.out.println(kthLargest.add(7));
        System.out.println(kthLargest.add(9));
    }
}

class KthLargest {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x));
    int k;
    int[] nums;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.nums = nums;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        priorityQueue.offer(val);
        if (priorityQueue.size() > k) {
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */