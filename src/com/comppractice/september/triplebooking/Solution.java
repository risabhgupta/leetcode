package com.comppractice.september.triplebooking;

import java.util.*;

class MyCalendarTwo {
    List<int[]> booking;
    List<int[]> doubleBooking;

    public MyCalendarTwo() {
        doubleBooking = new LinkedList<>();
        booking = new LinkedList<>();
    }

    public boolean book(int start, int end) {
        for (int[] existingEvent : doubleBooking) {
            if (start < existingEvent[1] && existingEvent[0] < end) {
                return false;
            }
        }

        for (int[] existingEvent : booking) {
            if (start < existingEvent[1] && existingEvent[0] < end) {
                int overlapStart = Math.max(start, existingEvent[0]);
                int overlapEnd = Math.min(end, existingEvent[1]);

                doubleBooking.add(new int[]{overlapStart, overlapEnd});
            }
        }

        booking.add(new int[]{start, end});
        return true;
    }
}
