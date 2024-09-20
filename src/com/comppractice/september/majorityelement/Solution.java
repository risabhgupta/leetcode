package com.comppractice.september.majorityelement;

class Solution {
    public int majorityElement(int[] nums) {
        int maxElement = -1;
        int freq = 0;

        for (int num : nums) {
            if (freq == 0) {
                maxElement = num;
                freq = 1;
            } else {
                if (maxElement == num) {
                    freq++;
                } else {
                    freq--;
                }
            }
        }

        return maxElement;
    }
}
