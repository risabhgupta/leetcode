package com.comppractice.august.minimumcommoninteger;

class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int pointerA = 0;
        int pointerB = 0;

        while (pointerA < nums1.length && pointerB < nums2.length) {
            if (nums1[pointerA] < nums2[pointerB]) {
                pointerA++;
            } else if (nums1[pointerA] > nums2[pointerB]) {
                pointerB++;
            } else {
                return nums1[pointerA];
            }
        }
        return -1;
    }
}
