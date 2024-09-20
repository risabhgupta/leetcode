package com.comppractice.july.sumdivbykcontigous;

public class Main {
}

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().subarraysDivByK(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
    }

    public int subarraysDivByK(int[] nums, int k) {
        int[] positiveBucketAvailable = new int[k];
        int[] negativeBucketAvailable = new int[k];
        int sum = 0;
        int count = 0;

        for (int num : nums) {
            sum += num;
            int number = sum % k;
            if (number < 0) {
                count += negativeBucketAvailable[number * -1];
                count += positiveBucketAvailable[k - (number * -1)];
            } else if (number == 0) {
                positiveBucketAvailable[0]++;
                count += positiveBucketAvailable[0];
            } else {
                count += positiveBucketAvailable[number];
                count += negativeBucketAvailable[k - number];
            }

            int bucketIndex = sum % k;
            if (sum % k < 0) {
                negativeBucketAvailable[-1 * bucketIndex]++;
            } else if (sum % k > 0) {
                positiveBucketAvailable[bucketIndex]++;
            }
        }
        return count;
    }
}