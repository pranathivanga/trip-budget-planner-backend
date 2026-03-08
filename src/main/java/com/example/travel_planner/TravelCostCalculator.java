package com.example.travel_planner;

import com.example.travel_planner.config.PlannerConfig;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.distance.DistanceService;

public class TravelCostCalculator {

    private final PlannerConfig config;
    private final DistanceService distanceService;

    public TravelCostCalculator(PlannerConfig config, DistanceService distanceService) {
        this.config = config;
        this.distanceService = distanceService;
    }

    public Money calculate(Trip trip) {

        double distance = distanceService.getDistance(
                trip.getSource().getCity(),
                trip.getDestination().getCity()
        );

        double costPerKm = config.getTravel().getCostPerKm();

        double total =
                distance *
                        costPerKm *
                        trip.getNumberOfTravelers();
System.out.println(distance);
        return new Money(total, "INR");
    }
}