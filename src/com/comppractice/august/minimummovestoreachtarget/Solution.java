package com.comppractice.august.minimummovestoreachtarget;

public class Solution {
    public int minMoves(int target, int maxDoubles) {
        int numberOfOperation = 0;
        while (maxDoubles > 0 && target > 1) {
            if (target % 2 == 1) {
                target--;
                numberOfOperation++;
            }
            target /= 2;
            numberOfOperation++;
            maxDoubles--;
        }
        numberOfOperation += target;
        return numberOfOperation - 1;
    }
}
