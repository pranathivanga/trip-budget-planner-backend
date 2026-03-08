package com.example.travel_planner;

import java.util.HashMap;
import java.util.Map;

public class DistanceCalculator {

    private static final Map<String, Integer> DISTANCE_TABLE = new HashMap<>();

    static {
        DISTANCE_TABLE.put("Hyderabad-Goa", 660);
        DISTANCE_TABLE.put("Hyderabad-Bangalore", 570);
        DISTANCE_TABLE.put("Hyderabad-Delhi", 1550);
        DISTANCE_TABLE.put("Bangalore-Goa", 560);
    }

    public int calculateDistance(String source, String destination) {

        String key = source + "-" + destination;
        String reverseKey = destination + "-" + source;

        if (DISTANCE_TABLE.containsKey(key)) {
            return DISTANCE_TABLE.get(key);
        }

        if (DISTANCE_TABLE.containsKey(reverseKey)) {
            return DISTANCE_TABLE.get(reverseKey);
        }

        return 800; // default mock distance
    }
}