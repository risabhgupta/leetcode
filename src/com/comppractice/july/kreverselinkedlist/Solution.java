package com.comppractice.july.kreverselinkedlist;

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
        return this.val + "";
    }
}

public class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head = new Solution().reverseKGroup(head, 4);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tempHead = head;
        ListNode tempTail = getTailPointer(tempHead, k);
        ListNode resultHead = tempTail != null ? tempTail : head;
        while (tempTail != null) {
            ListNode newHead = tempTail.next;
            ListNode newTail = getTailPointer(newHead, k);
            reverseLinkedList(tempHead, tempTail);
            tempHead = newHead;
            tempTail = newTail;
        }
        return resultHead;
    }

    private void reverseLinkedList(ListNode head, ListNode tail) {
        ListNode currentNode = head;
        ListNode previousNode = tail;
        ListNode secondListHead = tail.next;

        while (currentNode != secondListHead) {
            ListNode nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
    }

    public ListNode getTailPointer(ListNode head, int k) {
        int i = 1;
        ListNode currentNode = head;
        while (currentNode != null && i < k) {
            currentNode = currentNode.next;
            i++;
        }
        return currentNode;
    }
}
