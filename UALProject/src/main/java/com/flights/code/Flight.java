package com.flights.code;

public class Flight {
    private String flightNumber;
    private String carrier;
    private String origin;
    private String departure;
    private String destination;
    private String arrival;
    private String aircraft;
    private String distance;
    private String travelTime;
    private String status;

    public Flight() {
    }

    public Flight(String flightNumber, String carrier, String origin, String departure, String destination,
                  String arrival, String aircraft, String distance, String travelTime, String status) {
        this.flightNumber = flightNumber;
        this.carrier = carrier;
        this.origin = origin;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
        this.aircraft = aircraft;
        this.distance = distance;
        this.travelTime = travelTime;
        this.status = status;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
