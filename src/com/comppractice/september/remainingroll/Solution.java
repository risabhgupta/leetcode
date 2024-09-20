package com.comppractice.september.remainingroll;

import java.util.Arrays;
import java.util.stream.IntStream;


class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = 0;
        for (int roll : rolls) {
            sum += roll;
        }
        int[] remainingElement;
        int totalRemainingRoll = ((rolls.length + n) * mean) - sum;
        if (totalRemainingRoll <= 6 * n && totalRemainingRoll >= n) {
            remainingElement = new int[n];
            int minimumRoll = totalRemainingRoll / n;
            int rollRemainder = totalRemainingRoll % n;

            int index = 0;
            while (index < rollRemainder) {
                remainingElement[index++] = minimumRoll + 1;
            }
            while (index < n) {
                remainingElement[index++] = minimumRoll;
            }

        } else {
            remainingElement = new int[0];
        }
        return remainingElement;
    }
}