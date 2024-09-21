package com.comppractice.september.twooutofthree;

import java.util.*;

/**
 * 2032. Two Out of Three
 * Solved
 * Easy
 * Topics
 * Companies
 * Hint
 * Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values that are present in at least two out of the three arrays. You may return the values in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * Output: [3,2]
 * Explanation: The values that are present in at least two arrays are:
 * - 3, in all three arrays.
 * - 2, in nums1 and nums2.
 * Example 2:
 * <p>
 * Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * Output: [2,3,1]
 * Explanation: The values that are present in at least two arrays are:
 * - 2, in nums2 and nums3.
 * - 3, in nums1 and nums2.
 * - 1, in nums1 and nums3.
 * Example 3:
 * <p>
 * Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * Output: []
 * Explanation: No value is present in at least two arrays.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length, nums3.length <= 100
 * 1 <= nums1[i], nums2[j], nums3[k] <= 100
 */
class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        int index = 0;
        while (index < nums1.length || index < nums2.length || index < nums3.length) {
            if (index < nums1.length) {
                if (set2.contains(nums1[index]) || set3.contains(nums1[index])) {
                    result.add(nums1[index]);
                }
                set1.add(nums1[index]);
            }

            if (index < nums2.length) {
                if (set3.contains(nums2[index]) || set1.contains(nums2[index])) {
                    result.add(nums2[index]);
                }
                set2.add(nums2[index]);
            }

            if (index < nums3.length) {
                if (set1.contains(nums3[index]) || set2.contains(nums3[index])) {
                    result.add(nums3[index]);
                }
                set3.add(nums3[index]);
            }

            index++;
        }
        return new ArrayList<>(result);
    }

    public List<Integer> twoOutOfThreeAlt(int[] nums1, int[] nums2, int[] nums3) {
        int[] array = new int[101];
        Set<Integer> result = new HashSet<>();

        int index = 0;
        while (index < nums1.length || index < nums2.length || index < nums3.length) {
            if (index < nums1.length) {
                array[nums1[index]]|=0b100;
                if (array[nums1[index]] != 0b100) {
                    result.add(nums1[index]);
                }
            }

            if (index < nums2.length) {
                array[nums2[index]]|=0b010;
                if (array[nums2[index]] != 0b010) {
                    result.add(nums2[index]);
                }

            }

            if (index < nums3.length) {
                array[nums3[index]]|=0b001;
                if (array[nums3[index]] != 0b001) {
                    result.add(nums3[index]);
                }
            }

            index++;
        }


        return new LinkedList<>(result);
    }
}