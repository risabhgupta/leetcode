package com.comppractice.august.kthdistinctstring;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class Solution {
    public String kthDistinct(String[] arr, int k) {
        Set<String> stringSet = new HashSet<>();
        Set<String> distinctStringList = new LinkedHashSet<>();
        for (String string : arr) {
            if (!stringSet.contains(string)) {
                distinctStringList.add(string);
                stringSet.add(string);
            } else {
                distinctStringList.remove(string);
            }
        }

        if (distinctStringList.size() < k) {
            return "";
        } else {
            int count = 0;
            for (String string : distinctStringList) {
                if (count == k - 1) {
                    return string;
                }
                count++;
            }
        }
        return "";
    }
}
