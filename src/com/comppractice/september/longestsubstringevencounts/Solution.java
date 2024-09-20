package com.comppractice.september.longestsubstringevencounts;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findTheLongestSubstring("eleetminicoworoep"));
    }

    public int findTheLongestSubstring(String s) {
        int firstIndex = 0;
        int lastIndex = 0;
        int[] array = new int[26];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            array[c - 'a'] ^= c;
            if ((array[0] | array['e' - 'a'] | array['i' - 'a'] | array['o' - 'a'] | array['u' - 'a']) == 0) {
                lastIndex = i;
            }
        }
        return lastIndex - firstIndex + 1;
    }
}
