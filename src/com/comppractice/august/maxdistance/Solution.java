package com.comppractice.august.maxdistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Solution {

    public static void main(String[] args) {
        List<List<Integer>> arrays = new ArrayList<>();
        arrays.add(Collections.singletonList(1));
        arrays.add(Collections.singletonList(1));
        arrays.add(Collections.singletonList(1));

        System.out.println(new Solution().maxDistance(arrays));

    }

    public int maxDistance(List<List<Integer>> arrays) {
        int maxDistance = 0;

        int[] minFromLeft = new int[arrays.size()];
        int[] maxFromLeft = new int[arrays.size()];
        int[] minFromRight = new int[arrays.size()];
        int[] maxFromRight = new int[arrays.size()];

        minFromLeft[0] = arrays.get(0).get(0);
        maxFromLeft[0] = arrays.get(0).get(arrays.get(0).size() - 1);
        minFromRight[minFromRight.length - 1] = arrays.get(arrays.size() - 1).get(0);
        maxFromRight[maxFromRight.length - 1] = arrays.get(arrays.size() - 1)
                .get(arrays.get(arrays.size() - 1).size() - 1);

        int n = arrays.size();
        for (int i = 1, j = n - 2; i < n; i++, j--) {
            minFromLeft[i] = Math.min(minFromLeft[i - 1], arrays.get(i).get(0));
            maxFromLeft[i] = Math.max(maxFromLeft[i - 1], arrays.get(i).get(arrays.get(i).size() - 1));

            minFromRight[j] = Math.min(minFromRight[j + 1], arrays.get(j).get(0));
            maxFromRight[j] = Math.max(maxFromRight[j + 1], arrays.get(j).get(arrays.get(j).size() - 1));
        }

        for (int i = 0; i < arrays.size(); i++) {
            int maxChoosingPivotAsMax = arrays.get(i).get(arrays.get(i).size() - 1);
            int minChoosingPivotAsMax = Integer.MAX_VALUE;
            if (i > 0) {
                minChoosingPivotAsMax = Math.min(minChoosingPivotAsMax, minFromLeft[i - 1]);
            }
            if (i < arrays.size() - 1) {
                minChoosingPivotAsMax = Math.min(minChoosingPivotAsMax, minFromRight[i + 1]);
            }

            int minChoosingPivotAsMin = arrays.get(i).get(0);
            int maxChoosingPivotAsMin = Integer.MIN_VALUE;
            if (i > 0) {
                maxChoosingPivotAsMin = Math.max(maxChoosingPivotAsMin, maxFromLeft[i - 1]);
            }
            if (i < arrays.size() - 1) {
                maxChoosingPivotAsMin = Math.max(maxChoosingPivotAsMin, maxFromRight[i + 1]);
            }

            maxDistance = Math.max(maxChoosingPivotAsMax - minChoosingPivotAsMax, maxDistance);
            maxDistance = Math.max(maxChoosingPivotAsMin - minChoosingPivotAsMin, maxDistance);
        }


        return maxDistance;
    }
}