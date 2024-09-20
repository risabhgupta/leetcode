package com.comppractice.august.minnumberofpushes;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minimumPushes("aabbccddeeffgghhiiiiii"));
    }

    public int minimumPushes(String word) {
        Set<Character> characterArray = new HashSet<>();
        int[] frequencyArray = new int[26];
        for (Character s : word.toCharArray()) {
            characterArray.add(s);
            frequencyArray[s - 'a']++;
        }

        final List<Character> sortedFrequencyCharacterList = characterArray.stream()
                .sorted(Comparator.comparingInt(c -> -1 * frequencyArray[c - 'a'])).collect(Collectors.toList());

        int minPush = 0;
        for (int i = 0; i < characterArray.size(); i++) {
            minPush += (int) (frequencyArray[sortedFrequencyCharacterList.get(i) - 'a'] * Math.ceil(
                    (double) (i + 1) / 8));
        }
        return minPush;
    }
}
