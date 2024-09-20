package com.comppractice.july.magicmatrixgeneration;

public class Main {
    public static void main(String[] args) {

    }

    class Solution {
        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            int[][] resultMatrix = new int[rowSum.length][colSum.length];

            int rowIndex = 0;
            int colIndex = 0;

            while (rowIndex < rowSum.length && colIndex < colSum.length) {
                resultMatrix[rowIndex][colIndex] = Math.min(rowSum[rowIndex], colSum[colIndex]);
                rowSum[rowIndex] -= resultMatrix[rowIndex][colIndex];
                colSum[colIndex] -= resultMatrix[rowIndex][colIndex];

                if (rowSum[rowIndex] == 0) {
                    rowIndex++;
                }
                if (colSum[colIndex] == 0) {
                    colIndex++;
                }
            }

            return resultMatrix;
        }
    }
}
