package com.comppractice.august.stringoccurence;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().strStr("mississippi", "issip"));
    }

    public int strStr(String haystack, String needle) {
        char[] haystackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();
        int haystackPointer = 0;
        int needlePointer = 0;

        while (haystackPointer < haystackArray.length) {
            if (haystackArray[haystackPointer] == needleArray[needlePointer]) {
                needlePointer++;
                if (needlePointer == needleArray.length) {
                    return haystackPointer + 1 - needleArray.length;
                }
            } else {
                haystackPointer -= needlePointer;
                needlePointer = 0;
            }
            haystackPointer++;
        }
        return -1;
    }
}
