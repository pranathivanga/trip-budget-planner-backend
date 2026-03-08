package com.example.travel_planner.distance;

import java.util.HashMap;
import java.util.Map;

public class DistanceService {

    private static final Map<String, Double> DISTANCES = new HashMap<>();

    static {

        DISTANCES.put("Hyderabad-Bangalore", 570.0);
        DISTANCES.put("Hyderabad-Goa", 660.0);
        DISTANCES.put("Hyderabad-Delhi", 1550.0);

        DISTANCES.put("Bangalore-Goa", 560.0);
        DISTANCES.put("Bangalore-Delhi", 2150.0);

        DISTANCES.put("Delhi-Goa", 1900.0);
    }

    public double getDistance(String from, String to) {

        String key = from + "-" + to;
        String reverseKey = to + "-" + from;

        if (DISTANCES.containsKey(key)) {
            return DISTANCES.get(key);
        }

        if (DISTANCES.containsKey(reverseKey)) {
            return DISTANCES.get(reverseKey);
        }

        // fallback distance if city pair not found
        return 800;
    }
}