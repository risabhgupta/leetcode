package com.comppractice.september.differentways;

import java.util.*;
import java.util.function.Function;

public class Solution {
    Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        if(memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String exprLeft = expression.substring(0, i);
                String exprRight = expression.substring(i + 1);

                List<Integer> leftSolutions = diffWaysToCompute(exprLeft);
                List<Integer> rightSolutions = diffWaysToCompute(exprRight);

                for (int left : leftSolutions) {
                    for (int right : rightSolutions) {
                        if (c == '+') {
                            result.add(left + right);
                        } else if (c == '-') {
                            result.add(left - right);
                        } else {
                            result.add(left * right);
                        }
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        memo.put(expression, result);
        return result;
    }
}
