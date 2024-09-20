package com.comppractice.july.sortcharacterstring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().frequencySort("fivejumpingwizardsjumpsquicklyoveralazydog"));
    }
}

class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> characterCount = new HashMap<>();

        final char[] charArray = s.toCharArray();
        for (char c : charArray) {
            characterCount.putIfAbsent(c, 0);
            characterCount.put(c, characterCount.get(c) + 1);
        }

        List<Map.Entry<Character, Integer>> orderedEntries = characterCount.entrySet().stream().parallel()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue()).collect(Collectors.toList());

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : orderedEntries) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        }

        return result.toString();
    }
}