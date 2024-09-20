package com.comppractice.september.chalkreplacer;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().chalkReplacer(
                new int[] {3,4,1,2}, 25));
    }

    public int chalkReplacer(int[] chalk, int k) {
        long[] prefSum = new long[chalk.length];
        prefSum[0] = chalk[0];
        for (int i = 1; i < chalk.length; i++) {
            prefSum[i] = prefSum[i - 1] + chalk[i];
        }

        long remainingChalks = k % prefSum[chalk.length - 1];
        return binarySearch(prefSum, remainingChalks);
    }

    public int binarySearch(long[] arr, long num) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > num) {
                r = mid - 1;
            } else if (arr[mid] < num) {
                l = mid + 1;
            } else {
                return mid + 1;
            }
        }

        return r;

    }
}
