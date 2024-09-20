package com.comppractice.august.bullsandcows;

import java.util.HashMap;
import java.util.Map;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().getHint("2962", "7236"));
    }

    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        char[] guessArray = guess.toCharArray();
        char[] secretArray = secret.toCharArray();
        Map<Character, Integer> lettersInSecret = new HashMap<>();
        Map<Character, Integer> lettersInGuess = new HashMap<>();

        for (int i = 0; i < guessArray.length; i++) {
            if (guessArray[i] == secretArray[i]) {
                bulls++;
            } else {
                if (lettersInSecret.getOrDefault(guessArray[i], 0) > 0) {
                    cows++;
                    lettersInSecret.put(guessArray[i], lettersInSecret.get(guessArray[i]) - 1);
                }

                if (lettersInGuess.getOrDefault(secretArray[i], 0) > 0) {
                    cows++;
                    lettersInGuess.put(secretArray[i], lettersInGuess.get(secretArray[i]) - 1);
                }

                lettersInSecret.put(secretArray[i], lettersInSecret.getOrDefault(secretArray[i], 0) + 1);
                lettersInGuess.put(guessArray[i], lettersInGuess.getOrDefault(guessArray[i], 0) + 1);
            }
        }

        return bulls + "A" + cows + "B";
    }
}