package com.comppractice.july.coursesechduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findOrder(2, new int[][] { { 1, 0 } })));
    }
}

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < courses.length; i++) {
            courses[i] = new Course(i);
        }

        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[0]].dependentCourse.add(courses[prerequisite[1]]);
        }

        List<Course> orderedCourses = new ArrayList<>();
        boolean[] completedCourse = new boolean[numCourses];
        boolean[] forCourseInPath = new boolean[numCourses];
        for (Course course : courses) {
            traverseAndAddCourse(course, completedCourse, forCourseInPath, orderedCourses);
        }

        if (orderedCourses.size() < numCourses) {
            return new int[0];
        } else {
            int[] orderCourseNo = new int[numCourses];
            int i = 0;
            for (Course orderCourse : orderedCourses) {
                orderCourseNo[i] = orderCourse.courseNo;
                i++;
            }
            return orderCourseNo;
        }
    }

    private boolean traverseAndAddCourse(Course course, boolean[] completedCourse, boolean[] forCourseInPath,
            List<Course> orderedCourses) {
        if (forCourseInPath[course.courseNo]) {
            return false;
        }

        if (completedCourse[course.courseNo]) {
            return true;
        }

        forCourseInPath[course.courseNo] = true;
        for (Course dependentCourse : course.dependentCourse) {
            if (!traverseAndAddCourse(dependentCourse, completedCourse, forCourseInPath, orderedCourses)) {
                return false;
            }
        }
        orderedCourses.add(course);
        completedCourse[course.courseNo] = true;
        forCourseInPath[course.courseNo] = false;

        return true;
    }

    class Course {
        int courseNo;
        List<Course> dependentCourse;

        public Course(int courseNo) {
            this.courseNo = courseNo;
            this.dependentCourse = new ArrayList<>();
        }
    }
}
