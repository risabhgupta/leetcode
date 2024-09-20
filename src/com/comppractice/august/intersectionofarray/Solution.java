package com.comppractice.august.intersectionofarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().intersection(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 },
                new int[] { 2, 4, 5, 7, 9, 0, 12, 33 })));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        int maxIndex = Math.max(nums1.length, nums2.length);
        Set<Integer> firstSet = new HashSet<>();
        Set<Integer> secondSet = new HashSet<>();
        List<Integer> resultSet = new ArrayList<>();

        for (int i = 0; i < maxIndex; i++) {
            if (i < nums1.length) {
                firstSet.add(nums1[i]);
                if (secondSet.contains(nums1[i])) {
                    resultSet.add(nums1[i]);
                }
            }

            if (i < nums2.length) {
                secondSet.add(nums2[i]);
                if (firstSet.contains(nums2[i])) {
                    resultSet.add(nums2[i]);
                }
            }
        }

        return new int[2];
    }
}
