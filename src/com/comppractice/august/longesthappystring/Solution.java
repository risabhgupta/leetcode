package com.comppractice.august.longesthappystring;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().longestDiverseString(1, 1, 7));
    }

    public String longestDiverseString(int a, int b, int c) {
        int[] countRemaining = { a, b, c };
        PriorityQueue<Character> characterQueue = new PriorityQueue<>(
                Comparator.comparingInt(character -> -1 * countRemaining[character - 'a']));
        characterQueue.offer('a');
        characterQueue.offer('b');
        characterQueue.offer('c');
        return longestDiverseString(countRemaining, characterQueue);
    }

    private String longestDiverseString(int[] countRemaining, PriorityQueue<Character> characterQueue) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Character> deadCharacter = new HashSet<>();
        while (!characterQueue.isEmpty()) {
            char charToBeAppend = characterQueue.poll();
            if (countRemaining[charToBeAppend - 'a'] > 0 && (stringBuilder.length() < 2 || !(
                    stringBuilder.charAt(stringBuilder.length() - 1) == charToBeAppend
                            && stringBuilder.charAt(stringBuilder.length() - 2) == charToBeAppend))) {
                stringBuilder.append(charToBeAppend);
                countRemaining[charToBeAppend - 'a']--;
                if (countRemaining[charToBeAppend - 'a'] > 0) {
                    characterQueue.offer(charToBeAppend);
                }
                characterQueue.addAll(deadCharacter);
                deadCharacter = new HashSet<>();
            } else {
                deadCharacter.add(charToBeAppend);
            }
        }
        return stringBuilder.toString();
    }
}
