package com.comppractice.august.gcdstring;

import java.util.LinkedList;
import java.util.Queue;


class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        } else {
            int gcd = gcd(str1.length(), str2.length());
            return str1.substring(0, gcd - 1);
        }

    }

    private int gcd(int num1, int num2) {
        int dividend = Math.max(num1, num2);
        int divisor = Math.min(num1, num2);

        while (dividend % divisor == 0) {
            int tempDivisor = divisor;
            divisor = dividend % divisor;
            dividend = tempDivisor;
        }

        return divisor;
    }
}
