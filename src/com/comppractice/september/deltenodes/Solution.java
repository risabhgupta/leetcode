package com.comppractice.september.deltenodes;

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
    public ListNode modifiedList(int[] nums, ListNode head) {
        boolean[] hash = new boolean[10001];
        for (int num : nums) {
            hash[num] = true;
        }

        ListNode current = head;
        ListNode prev = null;
        ListNode newHead = head;

        while (current != null) {
            if (hash[current.val]) {
                if (prev == null) {
                    newHead = current.next;
                } else {
                    prev.next = current.next;
                }
            } else {
                prev = current;
            }
            current = current.next;
        }
        return newHead;
    }
}
