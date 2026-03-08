package com.example.travel_planner;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;

public class StayCostCalculator {

    public Money calculate(Trip trip) {

        double costPerNight;

        StayPreference preference = trip.getStayPreference();

        if(preference == StayPreference.BUDGET) {
            costPerNight = 1200;
        }
        else if(preference == StayPreference.STANDARD) {
            costPerNight = 2500;
        }
        else {
            costPerNight = 4500;
        }

        double total =
                costPerNight *
                        trip.getNumberOfDays() *
                        trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}