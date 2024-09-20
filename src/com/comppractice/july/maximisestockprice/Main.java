package com.comppractice.july.maximisestockprice;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[] { 1, 2, 4, 3, 2, 10, 12, 3 }));
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int buyIndex = 0;
        int sellIndex = 0;
        int profit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[buyIndex]) {
                buyIndex = i;
                sellIndex = i;
            }

            if (prices[sellIndex] < prices[i]) {
                profit += (prices[i] - prices[buyIndex]);
                buyIndex = i;
                sellIndex = i;
            }
        }
        return profit;
    }
}
