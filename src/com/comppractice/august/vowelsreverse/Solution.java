package com.comppractice.august.vowelsreverse;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseVowels("my name Is Risabh Gupta"));
    }

    public String reverseVowels(String s) {
        char[] stringArray = s.toCharArray();
        int firstPointer = 0;
        int lastPointer = stringArray.length - 1;

        while (lastPointer > firstPointer) {
            boolean firstPointerIsVowel = "AEIOUaeiou".indexOf(stringArray[firstPointer]) >= 0;
            boolean lastPointerIsVowel = "AEIOUaeiou".indexOf(stringArray[lastPointer]) >= 0;

            if (!firstPointerIsVowel) {
                firstPointer++;
            }

            if (!lastPointerIsVowel) {
                lastPointer--;
            }

            if (firstPointerIsVowel && lastPointerIsVowel) {
                char temp = stringArray[firstPointer];
                stringArray[firstPointer] = stringArray[lastPointer];
                stringArray[lastPointer] = temp;
                firstPointer++;
                lastPointer--;
            }
        } return String.valueOf(stringArray);
    }
}