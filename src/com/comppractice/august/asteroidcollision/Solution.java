package com.comppractice.august.asteroidcollision;

import java.util.Arrays;
import java.util.Stack;


public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().asteroidCollision(new int[] { -2,-1,1,2 })));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (final int asteroid : asteroids) {
            while (true) {
                if (stack.isEmpty()) {
                    stack.push(asteroid);
                    break;
                } else if (stack.peek() > 0 && asteroid < 0) {
                    if (Math.abs(stack.peek()) < Math.abs(asteroid)) {
                        stack.pop();
                    } else if (Math.abs(stack.peek()) == Math.abs(asteroid)) {
                        stack.pop();
                        break;
                    } else {
                        break;
                    }
                } else {
                    stack.push(asteroid);
                    break;
                }
            }
        }

        int[] result = new int[stack.size()];
        int index = 0;
        for (Integer integer : stack) {
            result[index++] = integer;
        }
        return result;
    }
}
