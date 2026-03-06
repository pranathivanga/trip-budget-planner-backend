package com.example.travel_planner.service.distance;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service
public class DistanceService {

    @Value("${planner.distance.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final Map<String, double[]> CITY_COORDINATES = Map.of(
            "Hyderabad", new double[]{78.4867,17.3850},
            "Goa", new double[]{73.8567,15.2993},
            "Bangalore", new double[]{77.5946,12.9716},
            "Delhi", new double[]{77.1025,28.7041}
    );

    @Cacheable("distances")
    public int getDistance(String source, String destination) {

        double[] src = CITY_COORDINATES.get(source);
        double[] dst = CITY_COORDINATES.get(destination);

        if (src == null || dst == null) {
            return 800;
        }

        String url =
                "https://api.openrouteservice.org/v2/directions/driving-car" +
                        "?api_key=" + apiKey +
                        "&start=" + src[0] + "," + src[1] +
                        "&end=" + dst[0] + "," + dst[1];

        try {

            Map response = restTemplate.getForObject(url, Map.class);

            Map feature = ((java.util.List<Map>)response.get("features")).get(0);

            Map properties = (Map) feature.get("properties");

            Map summary = (Map) properties.get("summary");

            double distanceMeters = (double) summary.get("distance");

            return (int)(distanceMeters / 1000);

        } catch (Exception e) {

            return 800;
        }
    }
}