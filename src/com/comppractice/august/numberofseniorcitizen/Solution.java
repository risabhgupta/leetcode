package com.comppractice.august.numberofseniorcitizen;

class Solution {
    public int countSeniors(String[] details) {
        int count = 0;
        for (String detail : details) {
            char ageTens = detail.charAt(11);
            char ageOnes = detail.charAt(12);
            if ((ageTens == '6' && ageOnes != '0') || ageTens == '7' || ageTens == '8' || ageTens == '9') {
                count++;
            }
        }
        return count;
    }
}
