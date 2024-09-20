package com.comppractice.august.sumoftwointegers;

public class Solution {
    public static int targetSum(int a, int b) {
        do {
            int xor = (a ^ b);
            b = ((a & b) << 1);
            a = xor;
        } while (b != 0);

        return a;

    }

}
