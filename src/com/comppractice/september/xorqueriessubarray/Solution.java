package com.comppractice.september.xorqueriessubarray;

public class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] ^ arr[i];
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 0) {
                res[i] = arr[queries[i][1]];
            } else {
                res[i] = arr[queries[i][0] - 1] ^ arr[queries[i][1] - 1];
            }
        }
        return res;
    }
}
