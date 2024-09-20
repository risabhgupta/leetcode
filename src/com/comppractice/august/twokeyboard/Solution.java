package com.comppractice.august.twokeyboard;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minSteps(70000));
    }

    static class Element {
        int value;
        int operation;
        int clipBoard;

        Element(final int value, final int operation, final int clipBoard) {
            this.value = value;
            this.operation = operation;
            this.clipBoard = clipBoard;
        }
    }


    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }

        PriorityQueue<Element> queue = new PriorityQueue<>(Comparator.comparingInt(element -> element.operation));
        queue.offer(new Element(2, 2, 1));

        while (queue.peek().value != n) {
            Element element = queue.poll();
            if (element.value + element.clipBoard <= n) {
                queue.offer(new Element(element.value + element.clipBoard, element.operation + 1, element.clipBoard));
            }
            if (element.value + element.value <= n) {
                queue.offer(new Element(element.value + element.value, element.operation + 2, element.value));
            }

        }
        return queue.peek().operation;
    }
}
