package com.comppractice.august.numberofballons;

class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] frequency = new int[5];
        for (char character : text.toCharArray()) {
            switch (character) {
                case 'b':
                    frequency[0]++;
                    break;
                case 'a':
                    frequency[1]++;
                    break;
                case 'l':
                    frequency[2]++;
                    break;
                case 'o':
                    frequency[3]++;
                    break;
                case 'n':
                    frequency[4]++;
                    break;
                default:
                    break;
            }
        }
        int i = 0;
        int total = Integer.MAX_VALUE;
        while (i < 5) {
            if (i == 2 || i == 3) {
                total = Math.min(total, frequency[i] / 2);
            } else {
                total = Math.min(total, frequency[i]);
            }
            i++;
        }
        return total;
    }
}