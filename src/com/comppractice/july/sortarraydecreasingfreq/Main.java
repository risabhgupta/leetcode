package com.comppractice.july.sortarraydecreasingfreq;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().frequencySort(new int[] { 1, 1, 2, 2, 2, 3, 3, 3 })));
    }
}

class Solution {
    public static void quicksort(int[] arr, int low, int high, int[] frequency) {
        if (low < high) {
            int pi = partition(arr, low, high, frequency);
            quicksort(arr, low, pi - 1, frequency);
            quicksort(arr, pi + 1, high, frequency);
        }
    }

    private static int partition(int[] arr, int low, int high, final int[] frequency) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator(frequency, pivot, arr[j])) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static boolean comparator(final int[] frequency, final int pivot, final int arr) {
        if (frequency[arr + 100] > frequency[pivot + 100]) {
            return true;
        } else if (frequency[arr + 100] == frequency[pivot + 100]) {
            return arr < pivot;
        } else {
            return false;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] frequencySort(int[] nums) {
        int[] frequencyOfNumbers = new int[200];
        Integer[] numsArray = new Integer[nums.length];
        for (int num : nums) {
            frequencyOfNumbers[num + 100]++;
        }
        quicksort(nums, 0, nums.length - 1, frequencyOfNumbers);
        return nums;
    }
}
