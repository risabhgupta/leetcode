package com.comppractice.july.minimumneighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        int n = 6;
        int distanceThreshold = 20;
        int[][] edges = {
                { 0, 1, 10 },
                { 0, 2, 1 },
                { 2, 3, 1 },
                { 1, 3, 1 },
                { 1, 4, 1 },
                { 4, 5, 10 }
        };
        System.out.println(new Solution().findTheCity(n, edges, distanceThreshold));
    }
}

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        City[] cities = new City[n];
        for (int i = 0; i < n; i++) {
            cities[i] = new City(i);
        }

        for (int[] edge : edges) {
            cities[edge[0]].connectingCities.add(cities[edge[1]]);
            cities[edge[0]].connectingCitiesDistance.add(edge[2]);

            cities[edge[1]].connectingCities.add(cities[edge[0]]);
            cities[edge[1]].connectingCitiesDistance.add(edge[2]);
        }

        int minimumCityReached = n + 1;
        int isolatedCityIndex = 0;
        for (int i = 0; i < n; i++) {
            int reachableCities = numberOfReachableCities(cities[i], distanceThreshold, n);
            if (reachableCities <= minimumCityReached) {
                minimumCityReached = reachableCities;
                isolatedCityIndex = i;
            }
        }
        return isolatedCityIndex;
    }

    private int numberOfReachableCities(City city, int distanceThreshold, int n) {
        Set<City> citiesInPath = new HashSet<>();
        Set<City> reachableCities = new HashSet<>();

        int[] onThreshold = new int[n];
        Arrays.fill(onThreshold, distanceThreshold + 1);

        traverse(city, citiesInPath, distanceThreshold, reachableCities, onThreshold);

        return reachableCities.size();
    }

    private void traverse(City city, Set<City> citiesInPath, int distanceThreshold, final Set<City> reachableCities,
            int[] onThreshold) {
        if (distanceThreshold < 0 || citiesInPath.contains(city)) {
            return;
        }

        if (reachableCities.contains(city) && onThreshold[city.cityIndex] >= distanceThreshold) {
            return;
        }

        citiesInPath.add(city);
        reachableCities.add(city);
        onThreshold[city.cityIndex] = distanceThreshold;

        for (int i = 0; i < city.connectingCities.size(); i++) {
            int newDistanceThreshold = distanceThreshold - city.connectingCitiesDistance.get(i);
            traverse(city.connectingCities.get(i), citiesInPath, newDistanceThreshold, reachableCities, onThreshold);
        }
        citiesInPath.remove(city);
    }

    static class City {
        int cityIndex;
        List<City> connectingCities = new ArrayList<>();
        List<Integer> connectingCitiesDistance = new ArrayList<>();

        public City(int cityIndex) {
            this.cityIndex = cityIndex;
        }
    }
}