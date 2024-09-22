package com.comppractice.september.kthlexicographicalnumber;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 440. K-th Smallest in Lexicographical Order
 * Hard
 * Topics
 * Companies
 * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 13, k = 2
 * Output: 10
 * Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= n <= 109
 */
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findKthNumber(1, 1));
    }

    public int findKthNumber(int n, int k) {
        Stack<Integer> stack = new Stack<>();
        k--;
        for (int i = 9; i >= 1; i--) {
            stack.push(i);
        }

        while (k > 0){
            int current = stack.pop();
            if (current <= n) {
                for (int i = 9; i >= 0; i--) {
                    int nextNumber = current * 10 + i;
                    if (nextNumber <= n) {
                        stack.push(nextNumber);
                    }
                }
            }
            k--;
        }
        return stack.peek();
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