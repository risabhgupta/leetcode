package com.comppractice.september.lexicalnumbers;

import java.util.LinkedList;
import java.util.List;

/**
 * Code
 * Testcase
 * Test Result
 * Test Result
 * 386. Lexicographical Numbers
 * Medium
 * Topics
 * Companies
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * <p>
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lexicalOrder(13));
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            generateNext(i, n, result);
        }
        return result;
    }

    private void generateNext(int num, int n, List<Integer> result) {
        if (num <= n) {
            result.add(num);
            for (int i = 0; i <= 9; i++) {
                int nextNumber = 10 * num + i;
                if (nextNumber <= n) {
                    generateNext(nextNumber, n, result);
                } else {
                    break;
                }
            }
        }
    }
}
