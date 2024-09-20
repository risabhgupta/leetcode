package com.comppractice.july.removesubstringscore;

import java.util.Stack;
import java.util.stream.Collectors;


public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maximumGain(
                "abbmzgaabtaabsbabhaahabnaeabdbaababbbiabaavababtabwbababzbdabbaaabhbyabdvabbaabbquapaaaaqbbblbuaawlnbbaxaubbbbbpbabbbpaaaacbbaabaaaahbbcoyaauabanqaabpbbbgaawbhabbbbaobsaaababbafbababbbbaaaqbabsbsmabbxqylbbbba",
                9421, 8003));
    }

    public int maximumGain(String s, int x, int y) {
        String topStr = "";
        int topScore = 0;
        String bottomStr = "";
        int bottomScore = 0;
        if (x > y) {
            topStr = "ab";
            topScore = x;
            bottomStr = "ba";
            bottomScore = y;
        } else {
            topStr = "ba";
            topScore = y;
            bottomStr = "ab";
            bottomScore = x;
        }

        int score = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char previousChar = 'x';
            if (!stack.isEmpty()) {
                previousChar = stack.peek();
            }
            if (String.format("%c%c", previousChar, s.charAt(i)).equals(topStr)) {
                stack.pop();
                score += topScore;
            } else {
                stack.push(s.charAt(i));
            }
        }

        String remainingString = stack.stream().map(String::valueOf).collect(Collectors.joining());
        stack = new Stack<>();
        for (int i = 0; i < remainingString.length(); i++) {
            char previousChar = 'x';
            if (!stack.isEmpty()) {
                previousChar = stack.peek();
            }
            if (String.format("%c%c", previousChar, remainingString.charAt(i)).equals(bottomStr)) {
                stack.pop();
                score += bottomScore;
            } else {
                stack.push(remainingString.charAt(i));
            }
        }
        return score;
    }
}
