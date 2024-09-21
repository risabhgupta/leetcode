package com.comppractice.september.stoneandobstacle;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        char[][] box = {
                {'#', '#', '*', '.', '*', '.'},
                {'#', '#', '#', '*', '.', '.'},
                {'#', '#', '#', '.', '#', '.'}
        };


        char[][] theBox = new Solution().rotateTheBox(box);
        System.out.println(Arrays.deepToString(theBox));
    }

    public char[][] rotateTheBox(char[][] box) {
        for (char[] row : box) {
            solve(row);
        }
        char[][] box2 = new char[box[0].length][box.length];
        for (int row = 0; row < box2.length; row++) {
            for (int col = 0; col < box2[0].length; col++) {
                box2[row][col] = box[box.length - 1 - col][row];
            }
        }
        return box2;
    }

    public void solve(char[] box) {
        int leastVacantPlaceAvailable = -1;
        for (int index = box.length - 1; index >= 0; index--) {
            switch (box[index]) {
                case '.':
                    if (leastVacantPlaceAvailable == -1) {
                        leastVacantPlaceAvailable = index;
                    }
                    break;
                case '*':
                    leastVacantPlaceAvailable = -1;
                    break;
                case '#':
                    if (leastVacantPlaceAvailable != -1) {
                        box[leastVacantPlaceAvailable] = '#';
                        box[index] = '.';
                        leastVacantPlaceAvailable--;
                    }
                    break;
            }
        }
    }
}
