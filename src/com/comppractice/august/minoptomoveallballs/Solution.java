package com.comppractice.august.minoptomoveallballs;

import java.util.Arrays;


class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().minOperations("110101011101010111101010101011")));
    }
    public int[] minOperations(String boxes) {
        int totalBallsAtRight = 0;
        int totalOpNeedAtRight = 0;
        int totalBallsAtLeft = 0;
        int totalOpNeedFromLeft = 0;
        char[] boxArray = boxes.toCharArray();
        for (int i = 0; i < boxArray.length; i++) {
            if (boxArray[i] == '1') {
                totalBallsAtRight++;
                totalOpNeedAtRight += i;
            }
        }

        int[] operationNeeded = new int[boxes.length()];

        for (int pointer = 0; pointer < boxArray.length; pointer++) {
            operationNeeded[pointer] = totalOpNeedAtRight + totalOpNeedFromLeft;
            totalOpNeedFromLeft += totalBallsAtLeft;
            if (boxArray[pointer] == '1') {
                totalBallsAtRight--;
                totalBallsAtLeft++;
                totalOpNeedFromLeft++;
            }
            totalOpNeedAtRight -= totalBallsAtRight;
        }
        return operationNeeded;
    }
}
