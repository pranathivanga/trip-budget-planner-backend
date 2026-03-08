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

        String source = trip.getSource().getCity();
        String destination = trip.getDestination().getCity();

       double distance = distanceService.getDistanceKm(source, destination);

        double costPerKm;

        switch (trip.getStayPreference()) {
            case PREMIUM:
                costPerKm = 10; // flight/cab
                break;
            case STANDARD:
                costPerKm = 6; // train AC
                break;
            default:
                costPerKm = 3; // bus/sleeper
        }

        double travelCost = distance * costPerKm * trip.getNumberOfTravelers();

        return new Money(travelCost, "INR");
    }
}