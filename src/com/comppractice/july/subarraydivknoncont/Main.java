package com.comppractice.july.subarraydivknoncont;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().subarraysDivByK(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
    }
}

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += subArraySumConditionCheck(nums, k, i, 0, 0, new ArrayList<>());
        }
        return count;
    }

    private int subArraySumConditionCheck(int[] nums, int divisibleBy, int index, int sumTillNow, int count,
            List<Integer> elements) {
        sumTillNow += nums[index];
        elements.add(nums[index]);
        if (sumTillNow % divisibleBy == 0) {
            count++;
            System.out.println(elements);
        }

        for (int i = index + 1; i < nums.length; i++) {
            count = subArraySumConditionCheck(nums, divisibleBy, i, sumTillNow, count, elements);
        }

        elements.remove(elements.size() - 1);
        return count;
    }
}
