package com.comppractice.september.splitlinkedlist;

import java.util.Arrays;


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

    @Override public String toString() {
        return next == null ? val + "" : val + "->" + next;
    }
}

class Solution {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3 };
        ListNode head = new ListNode(array[0]);
        ListNode temp = head;
        for (int i = 1; i < array.length; i++) {
            temp.next = new ListNode(array[i]);
            temp = temp.next;
        }

        System.out.println(Arrays.toString(new Solution().splitListToParts(head, 5)));
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        int length = 0;
        ListNode current = head;
        ListNode[] result = new ListNode[k];

        while (current != null) {
            current = current.next;
            length++;
        }

        int equalPart = length / k;
        int remainingPart = length % k;
        current = head;
        int i = 0;

        while (i < remainingPart && current!=null) {
            result[i++] = current;
            current = getSegmentAndClipLinkedList(current, equalPart + 1);
        }
        while (i < k && current!=null) {
            result[i++] = current;
            current = getSegmentAndClipLinkedList(current, equalPart);
        }

        return result;
    }

    public static ListNode getSegmentAndClipLinkedList(ListNode head, int k) {
        int pointer = 0;
        ListNode temp = head;
        while (temp.next != null && pointer < k - 1) {
            temp = temp.next;
            pointer++;
        }
        ListNode newHead = temp.next;
        temp.next = null;
        return newHead;
    }
}