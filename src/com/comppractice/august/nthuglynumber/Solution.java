package com.comppractice.august.nthuglynumber;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(1407));
    }

    public int nthUglyNumber(int n) {
        PriorityQueue<Long> numberQueue = new PriorityQueue<>();
        numberQueue.offer(1L);

        int count = 0;
        Set<Long> repeatedUglyNum = new HashSet<>();
        while (count < n - 1) {
            long number = numberQueue.poll();
            if (!repeatedUglyNum.contains(2 * number) && numberQueue.size() < n) {
                numberQueue.offer(2 * number);
                repeatedUglyNum.add(2 * number);
            }
            if (!repeatedUglyNum.contains(3 * number) && numberQueue.size() < n) {
                numberQueue.offer(3 * number);
                repeatedUglyNum.add(3 * number);
            }
            if (!repeatedUglyNum.contains(5 * number) && numberQueue.size() < n) {
                numberQueue.offer(5 * number);
                repeatedUglyNum.add(5 * number);
            }

            count++;
        }

        return Math.toIntExact(numberQueue.peek());
    }
}
