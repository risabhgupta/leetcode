package com.comppractice.august.pairleftover;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().numberOfPairs(new int[] { 1, 3, 2, 1, 3, 2, 2 })));
    }

    public int[] numberOfPairs(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        int[] result = new int[2];
        result[1] = nums.length;

        for (int num : nums) {
            if (numsSet.contains(num)) {
                result[0]++;
                numsSet.remove(num);
                result[1] -= 2;
            } else {
                numsSet.add(num);
            }
        }

        return result;
    }
}
