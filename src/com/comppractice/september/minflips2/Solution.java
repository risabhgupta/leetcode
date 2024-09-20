package com.comppractice.september.minflips2;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minFlips(2,6,5));
    }
    public int minFlips(int a, int b, int c) {
        int or = a | b;
        int xor = c ^ or;
        int flipReq = 0;
        while (xor != 0) {
            flipReq += (xor & 1);
            if ((xor & 1) == 1 && (or & 1) == 1 && ((a & 1) & (b & 1)) == 1) {
                flipReq++;
            }
            a = a >> 1;
            b = b >> 1;
            or = or >> 1;
            xor = xor >> 1;
        }
        return flipReq;
    }
}
