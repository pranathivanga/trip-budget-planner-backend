package com.example.travel_planner;

import com.example.travel_planner.distance.DistanceService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceServiceTest {

    @Test
    void shouldReturnDistanceBetweenCities() {

        DistanceService service = new DistanceService();

        double distance = service.getDistance("Hyderabad", "Goa");

        assertTrue(distance > 0);
    }
}