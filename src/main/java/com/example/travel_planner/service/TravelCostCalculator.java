package com.example.travel_planner.service;

import com.example.travel_planner.config.PlannerConfig;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.service.distance.DistanceService;

public class TravelCostCalculator {

    private final PlannerConfig config;
    private final DistanceService distanceService;

    public TravelCostCalculator(PlannerConfig config, DistanceService distanceService) {
        this.config = config;
        this.distanceService = distanceService;
    }

    public Money calculate(Trip trip) {

        String source = trip.getSource().getCity();
        String destination = trip.getDestination().getCity();

        int distance = distanceService.getDistance(source, destination);

        double costPerKm = 5;

        double travelCost =
                distance *
                        costPerKm *
                        trip.getNumberOfTravelers();

        return new Money(travelCost, "INR");
    }
}