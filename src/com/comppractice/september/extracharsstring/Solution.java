package com.comppractice.september.extracharsstring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minExtraChar("sayhelloworld", new String[]{"hello", "world"}));
    }

    public int minExtraChar(String s, String[] dictionary) {
        Set<String> dict = new HashSet<>();
        dict.addAll(Arrays.asList(dictionary));
        return getEscapedChars(s, dict);
    }

    private int getEscapedChars(String s, Set<String> dict) {
        for (int i = 0; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i + 1);
            if (dict.contains(left) && dict.contains(right)) {
                return 1 + getEscapedChars(left, dict) + getEscapedChars(right, dict);
            }
        }
        return 0;
    }
}
