package com.comppractice.august.numberofweeks;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().numberOfWeeks(new int[] { 5, 2, 1 }));
    }

    public long numberOfWeeks(int[] milestones) {
        PriorityQueue<Integer> projectOrder = new PriorityQueue<>(Comparator.comparing(i -> -1 * milestones[i]));
        for (int i = 0; i < milestones.length; i++) {
            projectOrder.offer(i);
        }

        Set<Integer> pendingProjects = new HashSet<>();
        int previousProjectIndex = -1;
        long numberOfWeeks = 0;
        while (!projectOrder.isEmpty()) {
            Integer projectIdx = projectOrder.poll();
            if (previousProjectIndex != projectIdx) {
                milestones[projectIdx]--;
                previousProjectIndex = projectIdx;
                projectOrder.addAll(pendingProjects);
                if (milestones[projectIdx] > 0) {
                    projectOrder.offer(projectIdx);
                }
                pendingProjects = new HashSet<>();
                numberOfWeeks++;
            } else {
                pendingProjects.add(projectIdx);
            }
        }
        return numberOfWeeks;
    }
}
