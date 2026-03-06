package com.example.travel_planner.service.distance;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DistanceService {

    private static final Map<String, Integer> DISTANCES = new HashMap<>();

    static {
        DISTANCES.put("Hyderabad-Goa", 660);
        DISTANCES.put("Hyderabad-Bangalore", 570);
        DISTANCES.put("Hyderabad-Delhi", 1550);
        DISTANCES.put("Bangalore-Goa", 560);
    }

    @Cacheable("distances")
    public int getDistance(String source, String destination) {

        String key = source + "-" + destination;
        String reverse = destination + "-" + source;

        if (DISTANCES.containsKey(key)) {
            return DISTANCES.get(key);
        }

        if (DISTANCES.containsKey(reverse)) {
            return DISTANCES.get(reverse);
        }

        return 800;
    }
}