package com.comppractice.august.combinationsumiii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for(int i=0; i<10; i++) {
            System.out.println(solution.executeAndPrintTime(() -> solution.combinationSum2(
                    new int[] { 1, 11, 111, 1111, 111111, 111111, 11111111, 2, 222, 2, 22, 2, 2, 2, 2, 2, 2222, 2222,
                            222,
                            3, 3, 3, 3, 3, 3, 3, 3,
                            333, 33, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 33333, 3, 333, 33, 3333, 3, 33, 3333, 4444, 5, 5,
                            55,
                            56, 6, 66, 6, 6, 67, 7, 7,
                            7777, 77, 7, 7, 7, 7, 77 }, 214)));
        }
    }

    public double executeAndPrintTime(Runnable function) {
        long startTime = System.nanoTime();
        function.run();
        long timeToExecute = System.nanoTime() - startTime;
        return (double) timeToExecute / 1000000;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.parallelSort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> tempList,
            List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > target) {
                break; // Stop if the current candidate exceeds the target
            }
            tempList.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, tempList, results);
            tempList.remove(tempList.size() - 1);
        }
    }
}
