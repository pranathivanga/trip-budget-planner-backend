package com.example.travel_planner.domain.trip;

public class Trip {
    private final Location source;
    private final Location destination;
    private final int numberOfDays;
    private final int numberOfTravelers;
    private final StayPreference stayPreference;

    public Trip(Location source,
                Location destination,
                int numberOfDays,
                int numberOfTravelers,
                StayPreference stayPreference) {

        this.source = source;
        this.destination = destination;
        this.numberOfDays = numberOfDays;
        this.numberOfTravelers = numberOfTravelers;
        this.stayPreference = stayPreference;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }
    public StayPreference getStayPreference() {
        return stayPreference;
    }
}
