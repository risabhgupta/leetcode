package com.comppractice.august.reachnumber;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reachNumber(2));
    }

    public int reachNumber(int target) {
        return reachNumberWithStep(1, target);
    }

    private int reachNumberWithStep(int steps, int target) {
        if (target < 0) {
            return 1 + reachNumberWithStep(steps + 1, target + steps);
        } else if (target > 0) {
            return 1 + reachNumberWithStep(steps + 1, target - steps);
        } else {
            return 0;
        }
    }
}
