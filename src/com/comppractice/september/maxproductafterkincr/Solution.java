package com.comppractice.september.maxproductafterkincr;

import java.util.PriorityQueue;

/**
 * 2233. Maximum Product After K Increments
 * Medium
 * Topics
 * Companies
 * Hint
 * You are given an array of non-negative integers nums and an integer k. In one operation, you may choose any element from nums and increment it by 1.
 * <p>
 * Return the maximum product of nums after at most k operations. Since the answer may be very large, return it modulo 109 + 7. Note that you should maximize the product before taking the modulo.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,4], k = 5
 * Output: 20
 * Explanation: Increment the first number 5 times.
 * Now nums = [5, 4], with a product of 5 * 4 = 20.
 * It can be shown that 20 is maximum product possible, so we return 20.
 * Note that there may be other ways to increment nums to have the maximum product.
 * Example 2:
 * <p>
 * Input: nums = [6,3,3,2], k = 2
 * Output: 216
 * Explanation: Increment the second number 1 time and increment the fourth number 1 time.
 * Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
 * It can be shown that 216 is maximum product possible, so we return 216.
 * Note that there may be other ways to increment nums to have the maximum product.
 */
public class Solution {
    public static void main(String[] args) {
        int[] array = {92, 36, 15, 84, 57, 60, 72, 86, 70, 43, 16};
        System.out.println(new Solution().maximumProduct(array, 62));
    }

    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.offer(num);
        }

        while (k > 0) {
            Integer poll = priorityQueue.poll();
            priorityQueue.offer(poll + 1);
            k--;
        }

        long res = 1;
        while (!priorityQueue.isEmpty()) {
            res = (res * priorityQueue.poll()) % 1000000007;
        }

        return (int) res;
    }
}
