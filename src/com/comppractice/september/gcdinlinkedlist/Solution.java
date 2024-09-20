package com.comppractice.september.gcdinlinkedlist;

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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode temp = head;

        while (temp.next != null) {
            ListNode listNode = new ListNode(gcd(temp.val, temp.next.val));
            listNode.next = temp.next;
            temp.next = listNode;
            temp = listNode.next;
        }

        return head;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        } else {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }
    }
}
