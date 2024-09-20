package com.comppractice.july.minimumcosttoconverststring;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().minimumCost("abcde", "ebcda", new char[] { 'a', 'b', 'c', 'd', 'e' },
                new char[] { 'e', 'd', 'b', 'e', 'c' }, new int[] { 1, 2, 3, 4, 5 }));
    }
}

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] changeCostMatrix = new long[26][26];
        for (int i = 0; i < changeCostMatrix.length; i++) {
            Arrays.fill(changeCostMatrix[i], Long.MAX_VALUE);
            changeCostMatrix[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            changeCostMatrix[original[i] - 97][changed[i] - 97] = Math.min(cost[i],
                    changeCostMatrix[original[i] - 97][changed[i] - 97]);
        }

        //Apply floyd-warshall
        shortestPathConversion(changeCostMatrix);

        long price = 0;
        for (int i = 0; i < source.length(); i++) {
            long costOfLetterChange = changeCostMatrix[source.charAt(i) - 97][target.charAt(i) - 97];
            if (costOfLetterChange != Long.MAX_VALUE) {
                price += costOfLetterChange;
            } else {
                price = -1;
                break;
            }
        }
        return price;
    }

    private void shortestPathConversion(long[][] changeCostMatrix) {
        for (int via = 0; via < 26; via++) {
            for (int startChar = 0; startChar < 26; startChar++) {
                for (int destinationChar = 0; destinationChar < 26; destinationChar++) {
                    if (changeCostMatrix[startChar][via] != Long.MAX_VALUE
                            && changeCostMatrix[via][destinationChar] != Long.MAX_VALUE) {
                        changeCostMatrix[startChar][destinationChar] = Math.min(
                                changeCostMatrix[startChar][destinationChar],
                                changeCostMatrix[startChar][via] + changeCostMatrix[via][destinationChar]);
                    }
                }
            }
        }
    }
}
