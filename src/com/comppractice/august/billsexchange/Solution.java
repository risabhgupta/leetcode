package com.comppractice.august.billsexchange;

import java.util.ArrayDeque;
import java.util.Deque;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lemonadeChange(new int[] { 5, 5, 10, 10, 20 }));
    }

    public boolean lemonadeChange(int[] bills) {
        Deque<Integer> dequeue = new ArrayDeque<>();
        for (int bill : bills) {
            if (bill == 5) {
                dequeue.addFirst(bill);
            } else if (bill == 10) {
                dequeue.addLast(bill);
            }
            int remainingBill = bill - 5;
            while (remainingBill > 0) {
                int currentBill = 0;
                if (!dequeue.isEmpty()) {
                    if (remainingBill > 5) {
                        currentBill = dequeue.removeLast();
                    } else {
                        currentBill = dequeue.removeFirst();
                    }
                    remainingBill -= currentBill;
                }
            }
            if (remainingBill != 0) {
                return false;
            }
        }
        return true;
    }
}