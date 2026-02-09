package com.example.travel_planner.domain.trip;

import java.util.Objects;

public class Location {
    private final String city;

    public Location(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }
}
