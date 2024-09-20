package com.comppractice.september.relativerank;

import java.util.Arrays;
import java.util.Comparator;


class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findRelativeRanks(new int[] { 10, 3, 8, 9, 4 })));
    }

    public String[] findRelativeRanks(int[] score) {
        Integer[] candidate = new Integer[score.length];
        for (int i = 0; i < score.length; i++) {
            candidate[i] = i;
        }

        Arrays.sort(candidate, Comparator.comparingInt(i -> -score[i]));
        String[] relativeRank = new String[score.length];
        for (int i = 0; i < relativeRank.length; i++) {
            if (i == 0) {
                relativeRank[candidate[i]] = "Gold Medal";
            } else if (i == 1) {
                relativeRank[candidate[i]] = "Silver Medal";
            } else if (i == 2) {
                relativeRank[candidate[i]] = "Bronze Medal";
            } else {
                relativeRank[candidate[i]] = (i + 1) + "";
            }
        }
        return relativeRank;
    }
}