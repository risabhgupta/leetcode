package com.comppractice.july.jumbledsort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution().sortJumbled(new int[] { 8, 9, 4, 0, 2, 1, 3, 5, 7, 6 }, new int[] { 991, 338, 38 })));
    }
}

class Solution {
    private static int getMappedNumber(int number, int[] mapping) {
        if (number == 0) {
            return mapping[0];
        } else {
            int temp = number;
            int multiplication = 1;
            int replacedNumber = 0;
            while (temp != 0) {
                int digit = temp % 10;
                replacedNumber = replacedNumber + (mapping[digit] * multiplication);
                multiplication *= 10;
                temp /= 10;
            }
            return replacedNumber;
        }
    }

    public int[] sortJumbled(int[] mapping, int[] nums) {
        Map<Integer, Integer> numberMapping = new HashMap<>();

        for (int num : nums) {
            if (!numberMapping.containsKey(num)) {
                numberMapping.put(num, getMappedNumber(num, mapping));
            }
        }

        Integer[] numsBoxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(numsBoxed, Comparator.comparingInt(numberMapping::get));

        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsBoxed[i];
        }

        return nums;
    }
}