package com.comppractice.september.shortestpalindrome;

/**
 * 214. Shortest Palindrome
 * Hard
 * Topics
 * Companies
 * You are given a string s. You can convert s to a
 * palindrome
 * by adding characters in front of it.
 * <p>
 * Return the shortest palindrome you can find by performing this transformation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: "dcbabcd"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of lowercase English letters only.
 */
public class Solution {
    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(new Solution().shortestPalindrome(s));
    }

    public String shortestPalindrome(String s) {
        String toBeAppended = "";
        for (int i = s.length(); i >= 0; i--) {
            if (isPalindrome(s.substring(0, i))) {
                toBeAppended = new StringBuilder(s.substring(i)).reverse().append(s).toString();
                break;
            }
        }
        return toBeAppended;
    }

    public boolean isPalindrome(String string) {
        int firstPointer = 0;
        int lastPointer = string.length() - 1;

        while (firstPointer < lastPointer) {
            if (string.charAt(firstPointer) == string.charAt(lastPointer)) {
                firstPointer++;
                lastPointer--;
            } else {
                return false;
            }
        }
        return true;
    }
}
