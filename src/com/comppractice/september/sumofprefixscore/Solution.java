package com.comppractice.september.sumofprefixscore;

import java.util.Arrays;

/**
 * 2416. Sum of Prefix Scores of Strings
 * Hard
 * Topics
 * Companies
 * Hint
 * You are given an array words of size n consisting of non-empty strings.
 * <p>
 * We define the score of a string term as the number of strings words[i] such that term is a prefix of words[i].
 * <p>
 * For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".
 * Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
 * <p>
 * Note that a string is considered as a prefix of itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["abc","ab","bc","b"]
 * Output: [5,4,3,2]
 * Explanation: The answer for each string is the following:
 * - "abc" has 3 prefixes: "a", "ab", and "abc".
 * - There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
 * The total is answer[0] = 2 + 2 + 1 = 5.
 * - "ab" has 2 prefixes: "a" and "ab".
 * - There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
 * The total is answer[1] = 2 + 2 = 4.
 * - "bc" has 2 prefixes: "b" and "bc".
 * - There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
 * The total is answer[2] = 2 + 1 = 3.
 * - "b" has 1 prefix: "b".
 * - There are 2 strings with the prefix "b".
 * The total is answer[3] = 2.
 * Example 2:
 * <p>
 * Input: words = ["abcd"]
 * Output: [4]
 * Explanation:
 * "abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
 * Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists of lowercase English letters.
 */
public class Solution {
    public static void main(String[] args) {
        String[] arr = {"abc", "ab", "bc", "b"};
        System.out.println(Arrays.toString(new Solution().sumPrefixScores(arr)));
    }

    public int[] sumPrefixScores(String[] words) {
        TrieNode root = new TrieNode();
        TrieNode temp;
        for (String word : words) {
            temp = root;
            for (char c : word.toCharArray()) {
                if (temp.child[c - 'a'] == null) {
                    temp.child[c - 'a'] = new TrieNode();
                }
                temp.child[c - 'a'].score++;
                temp = temp.child[c - 'a'];
            }
        }

        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            temp = root;
            int score = 0;
            for (char c : words[i].toCharArray()) {
                score += temp.child[c - 'a'].score;
                temp = temp.child[c - 'a'];
            }
            result[i] = score;
        }

        return result;
    }

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int score = 0;
    }
}
