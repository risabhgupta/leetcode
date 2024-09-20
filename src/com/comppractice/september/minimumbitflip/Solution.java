package com.comppractice.september.minimumbitflip;

public class Solution {
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        int flipReq = 0;
        while (xor != 0) {
            flipReq += (xor & 1);
            xor = xor >> 1;
        }
        return flipReq;
    }
}
