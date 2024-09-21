package com.comppractice.september.norminnormax;

public class Solution {
    public static void main(String[] args) {
        int[] array = {2, 1, 3};
        System.out.println(new Solution().findNonMinOrMax(array));
    }
    public int findNonMinOrMax(int[] nums) {

        if(nums.length > 2){
            int max = nums[0];
            int min = nums[0];
            for(int i=1; i<3; i++) {
                min = Math.min(nums[i], min);
                max = Math.max(nums[i], max);
            }
            for(int i=0; i<3; i++) {
                if(min!=nums[i] && max!=nums[i]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }
}
