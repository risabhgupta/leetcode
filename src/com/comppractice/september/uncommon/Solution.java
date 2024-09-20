package com.comppractice.september.uncommon;

import java.util.HashSet;
import java.util.Set;


class Solution {

    public String[] uncommonFromSentences(String s1, String s2) {
        // Split both sentences into words
        String[] s1Array = s1.split(" ");
        String[] s2Array = s2.split(" ");

        // Set to store words that are seen only once
        Set<String> result = new HashSet<>();

        // Set to track all words encountered
        Set<String> set = new HashSet<>();

        // Get the maximum length of the two arrays
        int len = Math.max(s1Array.length, s2Array.length);

        // Loop through both arrays simultaneously
        for (int i = 0; i < len; i++) {
            // Check words from s1Array, if i is within bounds
            if (i < s1Array.length) {
                if (set.contains(s1Array[i])) {
                    // If word already seen, remove it from result (it's no longer uncommon)
                    result.remove(s1Array[i]);
                } else {
                    // Add word to set and result for now (assuming it's uncommon)
                    set.add(s1Array[i]);
                    result.add(s1Array[i]);
                }
            }

            // Check words from s2Array, if i is within bounds
            if (i < s2Array.length) {
                if (set.contains(s2Array[i])) {
                    // If word already seen, remove it from result (it's no longer uncommon)
                    result.remove(s2Array[i]);
                } else {
                    // Add word to set and result for now (assuming it's uncommon)
                    set.add(s2Array[i]);
                    result.add(s2Array[i]);
                }
            }
        }

        // Convert result set to an array and return
        return result.toArray(new String[0]);
    }
}
