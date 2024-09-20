package com.comppractice.september.charsum;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().getLucky("ffdbumaxtfhkxclnpmbualsf", 2));
    }

    public int getLucky(String s, int k) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum += sumOfDigit(c - 'a' + 1, 1);
        }
        return sumOfDigit(sum, k - 1);
    }

    private int sumOfDigit(int sum, int k) {
        if (sum <= 9 || k == 0) {
            return sum;
        } else {
            int newSum = 0;
            while (sum != 0) {
                newSum += (sum % 10);
                sum /= 10;
            }
            return sumOfDigit(newSum, k - 1);
        }
    }
}