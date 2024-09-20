package com.comppractice.september.largestandbitwiseand;

public class Solution {
    public static void main(String[] args) {
        System.out.println(
                new Solution().longestSubarray(new int[] { 1, 2, 3, 4, 5, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3 }));
    }

    public int longestSubarray(int[] nums) {
        int maxNumber = -1;
        int count = 0;
        int max = 0;

        for (int num : nums) {
            if (num == maxNumber) {
                count++;
                if(count > max) {
                    max = count;
                }
            } else if (num > maxNumber) {
                count = 1;
                max = 1;
                maxNumber = num;
            } else {
                count = 0;
            }
        }
        return max;
    }

}
