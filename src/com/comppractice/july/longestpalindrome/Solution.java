package com.comppractice.july.longestpalindrome;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        int startIndexOfMaxPalindrome = 0;
        int endIndexOfMaxPalindrome = 0;
        int lengthOfMaxPalindrome = 0;

        //Checking only odd length of palindromes
        for (int i = 0; i < s.length(); i++) {
            int pointerToGoLeft = i - 1;
            int pointerToGoRight = i + 1;
            while (pointerToGoLeft >= 0 && pointerToGoRight < s.length() && (s.charAt(pointerToGoLeft) == s.charAt(
                    pointerToGoRight))) {

                if (lengthOfMaxPalindrome < pointerToGoRight - pointerToGoLeft + 1) {
                    startIndexOfMaxPalindrome = pointerToGoLeft;
                    endIndexOfMaxPalindrome = pointerToGoRight;
                    lengthOfMaxPalindrome = pointerToGoRight - pointerToGoLeft + 1;
                }
                pointerToGoLeft--;
                pointerToGoRight++;
            }
        }

        //Checking only odd length of palindromes
        for (int i = 0; i < s.length(); i++) {
            int pointerToGoLeft = i;
            int pointerToGoRight = i + 1;
            //Checking only odd length of palindromes
            while (pointerToGoLeft >= 0 && pointerToGoRight < s.length() && (s.charAt(pointerToGoLeft) == s.charAt(
                    pointerToGoRight))) {

                if (lengthOfMaxPalindrome < pointerToGoRight - pointerToGoLeft + 1) {
                    startIndexOfMaxPalindrome = pointerToGoLeft;
                    endIndexOfMaxPalindrome = pointerToGoRight;
                    lengthOfMaxPalindrome = pointerToGoRight - pointerToGoLeft + 1;
                }

                pointerToGoLeft--;
                pointerToGoRight++;
            }
        }

        return s.substring(startIndexOfMaxPalindrome, endIndexOfMaxPalindrome + 1);
    }
}
