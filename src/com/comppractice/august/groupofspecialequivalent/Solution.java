package com.comppractice.august.groupofspecialequivalent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(
                new Solution().numSpecialEquivGroups(new String[] { "abc", "acb", "bac", "bca", "cab", "cba" }));
    }

    public int numSpecialEquivGroups(String[] words) {
        Set<String> hashSet = new HashSet<>();
        for (String word : words) {
            hashSet.add(wordSort(word));
        }
        return hashSet.size();
    }

    public String wordSort(String word) {
        int length = word.length();
        char[] charArray = word.toCharArray();
        char[] evenChar = new char[(length + 1) / 2];
        char[] oddChar = new char[(length + 1) / 2];

        int even = 0;
        int odd = 0;
        int i = 0;
        while (i < charArray.length) {
            if (i % 2 == 0) {
                evenChar[even] = charArray[i];
                even++;
            } else {
                oddChar[odd] = charArray[i];
                odd++;
            }
            i++;
        }

        Arrays.sort(evenChar);
        Arrays.sort(oddChar);

        StringBuilder stringBuilder = new StringBuilder();
        i = 0;
        even = 0;
        odd = 0;
        while (i < charArray.length) {
            if (i % 2 == 0) {
                stringBuilder.append(evenChar[even]);
                even++;
            } else {
                stringBuilder.append(oddChar[odd]);
                odd++;
            }
            i++;
        }

        return stringBuilder.toString();
    }
}
