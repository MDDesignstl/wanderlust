package com.example.wanderlust.models;

public class GeoLocationResponse {
    private Location location;
    private double accuracy;

    public GeoLocationResponse() {
    }

    public GeoLocationResponse(Location location, double accuracy) {
        this.location = location;
        this.accuracy = accuracy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
