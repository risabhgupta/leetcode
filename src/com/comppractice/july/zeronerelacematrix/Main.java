package com.comppractice.july.zeronerelacematrix;

import java.util.HashSet;
import java.util.Set;


public class Main {
}

class Solution {
    public void setZeroes(int[][] matrix) {
        int[] rowReplace = new int[matrix[0].length];

        Set<Integer> rowToBeReplaced = new HashSet<>();
        Set<Integer> columnToBeReplaced = new HashSet<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == 0) {
                    rowToBeReplaced.add(row);
                    columnToBeReplaced.add(column);
                }
            }
        }

        for (Integer row : rowToBeReplaced) {
            matrix[row] = rowReplace;
        }

        for (Integer column : columnToBeReplaced) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][column] = 0;
            }
        }
    }
}