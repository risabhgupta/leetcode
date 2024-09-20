package com.comppractice.august.customset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class CustomSet {
    public static void main(String[] args) {
        CustomSet customSet = new CustomSet();
        System.out.println(customSet.getRandomAnInt());
        customSet.add(1);
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        customSet.add(2);
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        customSet.add(3);
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        customSet.remove(3);
        System.out.println("------------");
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        customSet.remove(1);
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        customSet.remove(2);
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());
        System.out.println(customSet.getRandomAnInt());

    }


    List<Integer> array = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    public void add(int value) {
        if (!map.containsKey(value)) {
            map.put(value, array.size());
            array.add(value);
        }
    }

    public void remove(int value) {
        if (map.containsKey(value)) {
            int index = map.get(value);
            final Integer element = array.get(array.size() - 1);
            array.set(index, element);
            array.remove(array.size() - 1);
            map.put(element, index);
            map.remove(value);
        }
    }

    public int getRandomAnInt() {
        if(array.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int randomIndex = new Random().nextInt(array.size());
        return array.get(randomIndex);
    }
}
