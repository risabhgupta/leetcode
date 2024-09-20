package com.comppractice.september.sortstringgreatest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


class Solution {
    public static void main(String[] args) {
        int[] arr = { 3, 30, 34, 5, 9 };
        System.out.println(new Solution().largestNumber(arr));
    }

    public String largestNumber(int[] nums) {
        String[] stringArray = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(stringArray, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String s : stringArray) {
            sb.append(s);
        }
        if (sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }
}
