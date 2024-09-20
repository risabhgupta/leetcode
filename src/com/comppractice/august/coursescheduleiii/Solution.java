package com.comppractice.august.coursescheduleiii;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().scheduleCourse(new int[][] { { 3, 2 }, { 4, 3 } }));
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        PriorityQueue<int[]> courseQueueTimeTaken = new PriorityQueue<>(Comparator.comparingInt(c -> c[0]));

        int time = 0;
        for (final int[] course : courses) {
            courseQueueTimeTaken.offer(course);
            time += course[0];
            if (time > course[1]) {
                int[] removedCourse = courseQueueTimeTaken.poll();
                time -= removedCourse[0];
            }
        }
        return courseQueueTimeTaken.size();
    }
}
