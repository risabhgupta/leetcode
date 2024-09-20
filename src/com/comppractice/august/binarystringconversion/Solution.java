package com.comppractice.august.binarystringconversion;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("111"));
    }

    public int solution(String S) {
        char[] array = S.toCharArray();
        int countOfOne = 0;
        int mostSignificantByte = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '1') {
                if (mostSignificantByte < 0) {
                    mostSignificantByte = i;
                }
                countOfOne++;
            }
        }
        if (mostSignificantByte >= 0) {
            return countOfOne + array.length - mostSignificantByte - 1;
        } else {
            return 0;
        }
    }
}
