package com.comppractice.august.interviewq;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.runTests();
    }

    public void runTests() {
        int[][] arrs = {
                {8, 3, 15, 10, 1},   // Test case 1
                {10, 6, 4},           // Test case 2
                {10, 6, 4},           // Test case 3
                {15, 1, 10, 8, 3},    // Test case 4
                {15, 1, 10, 8, 3},    // Test case 5
                {25, 7, 19, 5, 14},   // Test case 6
                {9, 2, 12, 4}         // Test case 7
        };

        int[] targets = {12, 7, 10, 16, 0, 6, 11};

        int[][] expectedOutputs = {
                {10, 15},   // Expected output for Test case 1
                {6, 10},    // Expected output for Test case 2
                {10, 10},   // Expected output for Test case 3
                {15, -1},   // Expected output for Test case 4
                {-1, 1},    // Expected output for Test case 5
                {5, 7},     // Expected output for Test case 6
                {9, 12}     // Expected output for Test case 7
        };

        boolean allTestsPassed = true;

        for (int i = 0; i < arrs.length; i++) {
            int[] result = findNearestSmallestAndLargest(arrs[i], targets[i]);
            if (result[0] == expectedOutputs[i][0] && result[1] == expectedOutputs[i][1]) {
                System.out.println("Test case " + (i + 1) + " PASSED.");
            } else {
                System.out.println("Test case " + (i + 1) + " FAILED. Expected: ["
                        + expectedOutputs[i][0] + ", " + expectedOutputs[i][1]
                        + "] but got: [" + result[0] + ", " + result[1] + "]");
                allTestsPassed = false;
            }
        }

        if (allTestsPassed) {
            System.out.println("All test cases PASSED.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }

    // Implementation of the method
    public int[] findNearestSmallestAndLargest(int[] arr, int target) {
        int smallest = -1;
        int largest = -1;

        for (int num : arr) {
            if (num == target) {
                return new int[] {target, target};
            }
            if (num < target) {
                if (smallest == -1 || num > smallest) {
                    smallest = num;
                }
            }
            if (num > target) {
                if (largest == -1 || num < largest) {
                    largest = num;
                }
            }
        }

        return new int[] {smallest, largest};
    }
}

