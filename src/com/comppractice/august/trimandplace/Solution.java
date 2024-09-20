package com.comppractice.august.trimandplace;

import java.util.Arrays;
import java.util.Comparator;


class Solution {
    public static void main(String[] args) {
        new Solution().smallestTrimmedNumbers(new String[] { "102", "473", "251", "814" },
                new int[][] { { 1, 1 }, { 2, 3 }, { 4, 2 }, { 1, 2 } });
    }

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[][] qResults = new int[nums[0].length() + 1][nums.length];

        for (int numIndex = 0; numIndex < nums.length; numIndex++) {
            int number = Integer.parseInt(nums[numIndex]);
            int factor = 1;
            for (int trimUpto = 0; trimUpto <= nums[0].length(); trimUpto++) {
                qResults[trimUpto][numIndex] = number % factor;
                factor *= 10;
            }
        }

        // Sort each row
        for (int[] qResult : qResults) {
            Integer[] invertedIndexes = new Integer[qResult.length];
            for (int i = 0; i < invertedIndexes.length; i++) {
                invertedIndexes[i] = i;
            }

            Arrays.sort(invertedIndexes, Comparator.comparingInt(index -> qResult[index]));

            for (int i = 0; i < qResult.length; i++) {
                qResult[i] = invertedIndexes[i];
            }
        }

        int[] finalResult = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            finalResult[index] = qResults[query[1]][query[0] - 1];
            index++;
        }
        return finalResult;
    }
}