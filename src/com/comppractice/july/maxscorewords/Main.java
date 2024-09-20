package com.comppractice.july.maxscorewords;

import java.util.Arrays;


public class Main {
}

class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] totalScore = new int[26];
        Arrays.fill(totalScore, -1);
        for (char letter : letters) {
            totalScore[letter - 'a']++;
            totalScore[letter - 'a'] += score[letter - 'a'];
        }

        int maxScore = 0;
        for (String word : words) {
            char[] wordArray = word.toCharArray();
            maxScore = Math.max(getScore(wordArray, 0, 0, totalScore, score), maxScore);
        }
        return maxScore;
    }

    private int getScore(char[] word, int index, int scoreAchieved, int[] totalScore, int[] score) {
        if (index < word.length) {
            int scoreAch = 0;
            if (totalScore[word[index] - 'a'] > 0) {
                totalScore[word[index]] -= score[word[index] - 'a'];
                scoreAch = getScore(word, index + 1, scoreAchieved + totalScore[word[index] - 'a'], totalScore, score);
                totalScore[word[index]] += score[word[index] - 'a'];

            } else {
                scoreAch = -1;
            }
            return scoreAch;

        } else {
            return scoreAchieved;
        }
    }
}