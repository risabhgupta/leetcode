package com.comppractice.trusttrace;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Trustrace {
    public static void main(String[] args) {
        System.out.println(new Trustrace().groupOfAnagrams(new String[] {"act","god","cat","dog","tac"}));
    }

    public Collection<Set<String>> groupOfAnagrams(String[] words) {
        Map<String , Set<String>> resultGroup = new HashMap<>();
        for(String word : words) {
            char [] stringArray = word.toCharArray();
            Arrays.sort(stringArray);
            String key = String.valueOf(stringArray);
            resultGroup.computeIfAbsent(key, k -> new TreeSet<>()).add(word);
        }

        return resultGroup.values();
    }
}
