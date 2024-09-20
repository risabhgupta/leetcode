package com.comppractice.august.waytoexpressintegerassum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().numberOfWays(300, 5));
    }

    public int numberOfWays(int n, int x) {
        List<Integer> poweredList = new ArrayList<>();
        for (int i = 1; Math.pow(i, x) <= n; i++) {
            poweredList.add((int) Math.pow(i, x));
        }

        return (int) (traverse(poweredList, 0, n, new HashMap<>()) % 1000000007);
    }

    private long traverse(List<Integer> list, int index, int target, Map<String, Long> memo) {
        long possibility = 0;
        if (index < list.size()) {
            String key = index + "," + target;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            int remainingTarget = target - list.get(index);

            possibility += traverse(list, index + 1, target, memo);
            if (remainingTarget == 0) {
                possibility = 1;
            } else if (remainingTarget > 0) {
                possibility += traverse(list, index + 1, remainingTarget, memo);
            }
            memo.put(key, possibility);
        }
        return possibility;
    }

}
