/*
package com.comppractice.august.kbooking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class MyCalendarThree {
    Map<int[], Integer> calendar;
    public MyCalendarThree() {
         calendar = new HashMap<>();
    }

    public int book(int startTime, int endTime) {
        Set<Map.Entry<int[], Integer>> overLap = new HashSet<>();
        for(Map.Entry<int[], Integer> booking : calendar.entrySet()) {
            if(booking.getKey()[1] > startTime && booking.getKey()[0] < endTime) {
                overLap.add(booking);
            }
        }
        for(Map.Entry<int[], Integer> overLaps : overLap) {
            int[] intersect = {Math.max(startTime, overLaps.getKey()[0]), Math.min(endTime, overLaps.getKey()[1])};
            calendar.remove(overLaps.getKey());
            calendar.put(intersect, overLaps.getValue() + 1);

        }
    }
}

*/
