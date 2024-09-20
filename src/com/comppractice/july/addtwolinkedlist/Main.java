package com.comppractice.july.addtwolinkedlist;

import java.util.Deque;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        ListNode firstNumber = new ListNode(1);
        firstNumber.next = new ListNode(8);
        firstNumber.next.next = new ListNode(3);

        ListNode secondNumber = new ListNode(8);
        secondNumber.next = new ListNode(5);
        secondNumber.next.next = new ListNode(8);

        System.out.println(new Solution().addTwoNumbers(firstNumber, secondNumber));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> firstNumberDigits = new LinkedList<>();
        Deque<Integer> secondNumberDigits = new LinkedList<>();

        ListNode firstNumberDigit = l1;
        ListNode secondNumberDigit = l2;

        while (firstNumberDigit != null || secondNumberDigit != null) {
            if (firstNumberDigit != null) {
                firstNumberDigits.push(firstNumberDigit.val);
                firstNumberDigit = firstNumberDigit.next;
            }

            if (secondNumberDigit != null) {
                secondNumberDigits.push(secondNumberDigit.val);
                secondNumberDigit = secondNumberDigit.next;
            }
        }

        boolean carry = false;
        ListNode currentNode = null;
        while (!(firstNumberDigits.isEmpty() && secondNumberDigits.isEmpty()) || carry) {
            ListNode nextNode = currentNode;
            int result = 0;
            if (!firstNumberDigits.isEmpty()) {
                result += firstNumberDigits.pop();
            }
            if (!secondNumberDigits.isEmpty()) {
                result += secondNumberDigits.pop();
            }
            if (carry) {
                result++;
            }

            currentNode = new ListNode(result % 10);
            carry = result > 9;

            currentNode.next = nextNode;
        }

        return currentNode;
    }
}
