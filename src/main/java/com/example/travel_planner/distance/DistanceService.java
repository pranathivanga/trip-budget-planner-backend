package com.example.travel_planner.distance;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class DistanceService {

    private static final String GOOGLE_API =
            "https://maps.googleapis.com/maps/api/distancematrix/json";

    private static final String API_KEY = "YOUR_GOOGLE_API_KEY";

    private final RestTemplate restTemplate = new RestTemplate();

    public double getDistanceKm(String source, String destination) {

        try {

            String url =
                    GOOGLE_API
                            + "?origins=" + source
                            + "&destinations=" + destination
                            + "&key=" + API_KEY;

            Map response = restTemplate.getForObject(url, Map.class);

            Map row = (Map) ((java.util.List) response.get("rows")).get(0);
            Map element = (Map) ((java.util.List) row.get("elements")).get(0);
            Map distance = (Map) element.get("distance");

            int meters = (int) distance.get("value");

            return meters / 1000.0;

        } catch (Exception e) {

            System.out.println("Google Maps failed, using Haversine fallback");

            return haversineDistance(source, destination);
        }
    }

    private double haversineDistance(String city1, String city2) {

        double lat1 = 17.3850;  // Hyderabad fallback
        double lon1 = 78.4867;

        double lat2 = 15.2993;  // Goa fallback
        double lon2 = 74.1240;

        double R = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}