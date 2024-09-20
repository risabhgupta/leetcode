package com.comppractice.september.consistentstring;

class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] hash = new boolean[26];
        for (int i = 0; i < allowed.length(); i++) {
            hash[allowed.charAt(i) - 'a'] = true;
        }

        int count = 0;

        for (int i = 0; i < words.length; i++) {
            if (isConsistent(hash, words[i])) {
                count++;
            }
        }
        return count;
    }

    private boolean isConsistent(boolean[] hash, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!hash[word.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}