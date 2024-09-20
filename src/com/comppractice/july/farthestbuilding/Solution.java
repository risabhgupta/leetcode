package com.comppractice.july.farthestbuilding;

import java.util.PriorityQueue;
import java.util.Queue;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().furthestBuilding(new int[]
                { 4, 2, 7, 6, 9, 14, 12 }, 5, 1));
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int reachedPoint = 0;
        int availableBrick = bricks;
        Queue<Integer> queue = new PriorityQueue<>(ladders + 1);
        for (int i = 1; i < heights.length; i++) {
            int heightToClimb = Math.max(heights[i] - heights[i - 1], 0);
            if (queue.size() < ladders + 1) {
                queue.add(heightToClimb);
            }
            int brickNeed = 0;
            if (queue.size() == ladders + 1) {
                brickNeed = queue.poll();
            }
            if (brickNeed > availableBrick) {
                break;
            } else {
                reachedPoint++;
                availableBrick -= brickNeed;
            }
        }
        return reachedPoint;
    }
}
