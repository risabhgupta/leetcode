package com.comppractice.august.wildcardcost;

import java.util.Comparator;
import java.util.PriorityQueue;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minimizeStringValue("ab?b???"));
    }

    public String minimizeStringValue(String s) {
        char[] stringArray = s.toCharArray();
        int[] frequency = new int[26];
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((character1, character2) -> {
            if (frequency[character1 - 'a'] == frequency[character2 - 'a']) {
                return character1 - character2;
            } else {
                return frequency[character1 - 'a'] - frequency[character2 - 'a'];
            }
        });
        for (int i = 0; i < 26; i++) {
            priorityQueue.offer((char) (i + 'a'));
        }

        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i] == '?') {
                char replacedChar = priorityQueue.poll();
                frequency[replacedChar - 'a']++;
                stringArray[i] = replacedChar;
                priorityQueue.offer(replacedChar);
            } else {
                priorityQueue.remove(stringArray[i]);
                frequency[stringArray[i] - 'a']++;
                priorityQueue.offer(stringArray[i]);
            }
        }
        return String.valueOf(stringArray);
    }
}
