package com.comppractice.august.testpr;

public class Solution {
    public static void main(String[] args) {
        boolean result = execute();
        result &= execute();
    }

    public static boolean execute() {
        System.out.println("Execute is running..");
        return false;
    }
}
