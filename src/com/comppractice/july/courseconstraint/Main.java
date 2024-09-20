package com.comppractice.july.courseconstraint;

import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().canFinish(3, new int[][] { { 1, 0 }, { 1, 2 }, { 0, 1 } }));
    }
}

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean canFinish = true;
        Course[] courses = new Course[numCourses];
        Set<Course> courseComplete = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course();
        }

        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[0]].dependentCourses.add(courses[prerequisite[1]]);
        }

        for (Course course : courses) {
            canFinish = canFinish && canFinishCourse(course, courseComplete, new HashSet<>());
        }
        return canFinish;
    }

    private boolean canFinishCourse(Course course, Set<Course> courseComplete, Set<Course> courseCompleteInContext) {
        if (courseCompleteInContext.contains(course)) {
            return false;
        }

        if (courseComplete.contains(course)) {
            return true;
        }

        courseCompleteInContext.add(course);
        for (Course dependentCourse : course.dependentCourses) {
            return canFinishCourse(dependentCourse, courseComplete, courseCompleteInContext);
        }
        courseCompleteInContext.remove(course);
        courseComplete.add(course);
        return true;
    }

    static class Course {
        public Set<Course> dependentCourses;

        public Course() {
            this.dependentCourses = new HashSet<>();
        }
    }
}