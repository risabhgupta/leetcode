package com.comppractice.august.coursescheduleiv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().checkIfPrerequisite(3, new int[][] { { 1, 2 }, { 1, 0 }, { 2, 0 } },
                new int[][] { { 1, 0 }, { 1, 2 } }));
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[][] matrix = new int[numCourses][numCourses];
        Map<Integer, Set<Integer>> dependentCourses = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            matrix[prerequisite[1]][prerequisite[0]] = 1;
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] visitedInContext = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                dfs(matrix, i, visited, dependentCourses, visitedInContext);
            }
        }

        List<Boolean> result = new ArrayList<>();
        for (final int[] query : queries) {
            result.add(dependentCourses.get(query[1]).contains(query[0]));
        }

        return result;
    }

    private Set<Integer> dfs(int[][] matrix, int i, boolean[] visited, Map<Integer, Set<Integer>> dependentCourses,
            boolean[] visitedInContext) {
        if (!visitedInContext[i] && !visited[i]) {
            visitedInContext[i] = true;
            Set<Integer> dependentCourseP = new HashSet<>();
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    dependentCourseP.add(j);
                    dependentCourseP.addAll(dfs(matrix, j, visited, dependentCourses, visitedInContext));
                }
            }
            visitedInContext[i] = false;
            visited[i] = true;
            dependentCourses.put(i, dependentCourseP);
            return dependentCourseP;
        } else {
            return dependentCourses.get(i);
        }

    }
}
