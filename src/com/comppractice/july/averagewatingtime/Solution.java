package com.comppractice.july.averagewatingtime;

public class Solution {
    public static void main(String[] args) {
        new Solution().averageWaitingTime(new int[][] { { 1, 2 }, { 3, 5 }, { 4, 3 }, { 10, 3 } });
    }

    public double averageWaitingTime(int[][] customers) {
        double sumOfWaitingTime = customers[0][1];
        int currentTime = customers[0][0] + customers[0][1];
        for (int i = 1; i < customers.length; i++) {
            int arrivalTime = customers[i][0];
            int preparationTime = customers[i][1];
            int deliveryTime = Math.max(currentTime, arrivalTime) + preparationTime;
            sumOfWaitingTime += (deliveryTime - arrivalTime);
            currentTime = deliveryTime;
        }
        return sumOfWaitingTime / customers.length;
    }
}
