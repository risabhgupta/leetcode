package com.comppractice.august.minimumswaptogroup;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minSwaps(new int[] { 1 }));
    }

    public int minSwaps(int[] nums) {
        int firstPointer = 0;
        int newStartIndex = 0;
        int maxWidth = 0;
        int maxStartIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (firstPointer > maxWidth) {
                    maxWidth = firstPointer;
                    maxStartIndex = newStartIndex;
                }

                firstPointer = 0;
                newStartIndex = i + 1;
            } else {
                firstPointer++;
            }
        }
        return maxStartIndex;
    }
}
