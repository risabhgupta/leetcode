package com.comppractice.september.maxslidingwindow;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239. Sliding Window Maximum
 * Hard
 * Topics
 * Companies
 * Hint
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */


public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(arr, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(i -> -nums[i]));
        for (int i = 0; i < k  - 1; i++) {
            priorityQueue.offer(i);
        }

        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            priorityQueue.offer(i + k - 1);
            result[i] = nums[priorityQueue.peek()];
            while(priorityQueue.peek() <= i) {
                priorityQueue.poll();
            }
        }
        return result;
    }
}
