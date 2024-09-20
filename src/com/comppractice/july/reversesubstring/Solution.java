package com.comppractice.july.reversesubstring;

import java.util.Stack;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseParentheses("(A(BC)(DE)FG)"));
    }

    // (ed(et(oc))el)
    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        stack.push("");
        String closedString = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push("");
            } else if (s.charAt(i) == ')') {
                closedString = stack.pop();
                stack.push(stack.pop() + new StringBuilder(closedString).reverse());
            } else {
                closedString = stack.pop();
                stack.push(closedString + s.charAt(i));
            }
        }
        return stack.peek();
    }
}

