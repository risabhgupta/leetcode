package com.comppractice.september.jumpgame;

public class Solution {
    public static void main(String[] args) {
        int[] arr = { 3, 2, 1, 0, 4 };
        System.out.println(new Solution().canJump(arr));
    }

    public boolean canJump(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        return canReach(0, nums, visited);
    }

    private boolean canReach(int i, int[] nums, boolean[] visited) {
        if (i == nums.length - 1) {
            return true;
        } else {
            visited[i] = true;
            for (int jump = 1; jump <= nums[i] && i + jump < nums.length; jump++) {
                if (!visited[i + jump] && canReach(i + jump, nums, visited)) {
                    return true;
                }
            }
            return false;
        }
    }
}
