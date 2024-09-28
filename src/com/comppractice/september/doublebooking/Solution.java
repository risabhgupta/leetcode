package com.comppractice.september.doublebooking;

import java.util.Comparator;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        MyCalendar cal = new MyCalendar();
        cal.book(10, 20);
        cal.book(15, 25);
        cal.book(20 ,30);
    }
}

class MyCalendar {
    TreeSet<int[]> calendar;
    public MyCalendar() {
        calendar = new TreeSet<>(Comparator.comparingInt(time -> time[1]));
    }

    public boolean book(int start, int end) {
        int[] ceil = calendar.ceiling(new int[]{0, start + 1});
        if(ceil == null || ceil[0] >= end) {
            calendar.add(new int[]{start, end});
            return true;
        } else {
            return false;
        }
    }
}
