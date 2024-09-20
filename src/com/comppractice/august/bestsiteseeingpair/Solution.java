package com.comppractice.august.bestsiteseeingpair;

class Solution {
    public static void main(String... arg) {
        System.out.println(new Solution().maxScoreSightseeingPair(new int[] { 8, 1, 5, 2, 6 }));
    }

    public int maxScoreSightseeingPair(int[] values) {
        int[] jIndexMaxFromRight = new int[values.length];
        int[] iIndexMaxFromLeft = new int[values.length];
        iIndexMaxFromLeft[0] = values[0];
        jIndexMaxFromRight[values.length - 1] = values[values.length - 1] - (values.length - 1);

        for (int i = 1; i < values.length; i++) {
            iIndexMaxFromLeft[i] = Math.max(iIndexMaxFromLeft[i - 1], values[i] + i);
            jIndexMaxFromRight[values.length - 1 - i] = Math.max(jIndexMaxFromRight[values.length - 1 - i + 1],
                    values[values.length - 1 - i] - (values.length - 1 - i));
        }

        int scoreOfSiteSeeingPair = iIndexMaxFromLeft[0] + jIndexMaxFromRight[1];
        for (int i = 0; i < values.length - 1; i++) {
            scoreOfSiteSeeingPair = Math.max(scoreOfSiteSeeingPair, iIndexMaxFromLeft[i] + jIndexMaxFromRight[i + 1]);
        }
        return scoreOfSiteSeeingPair;
    }

    public int maxScoreSightseeingPair2(int[] values) {
        int maxSoFar = 0;
        int bestSoFar = values[0];

        for(int j = 1; j < values.length; j++){
            maxSoFar = Math.max(maxSoFar, bestSoFar + values[j] - j);
            bestSoFar = Math.max(bestSoFar, values[j] + j);
        }

        return maxSoFar;
    }
}
