package com.comppractice.august.nextwarmerday;

import java.util.Arrays;
import java.util.Stack;


public class Solution {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().dailyTemperatures(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> monotonicStack = new Stack<>();
        int[] result = new int[temperatures.length];
        monotonicStack.push(0);

        for (int i = 0; i < temperatures.length; i++) {
            while (!monotonicStack.isEmpty() && temperatures[monotonicStack.peek()] < temperatures[i]) {
                Integer index = monotonicStack.pop();
                result[index] = i - index;
            }
            monotonicStack.push(i);
        }

        return result;
    }
}
