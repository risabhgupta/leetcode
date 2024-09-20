package com.comppractice.august.complement;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findComplement(1));
    }

    public int findComplement(int num) {
        int nearestSquare = 0;
        int temp = num;
        while (temp != 0) {
            nearestSquare = nearestSquare << 1;
            nearestSquare = nearestSquare | 1;
            temp = temp >> 1;
        }

        return num ^ nearestSquare;
    }
}
