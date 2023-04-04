package com.driver.reposirtory;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AirportRepository {
    HashMap<String,Airport> airportDb = new HashMap<String,Airport>();

    HashMap<Integer,Flight> flightDb = new HashMap<Integer,Flight>();

    HashMap<Integer,Passenger> passengerDb = new HashMap<Integer,Passenger>();

    HashMap<Integer,List<Integer>> flightTicketBookingDb = new HashMap<Integer, List<Integer>>();
    public String addAirport(Airport airport) {
        String key = airport.getAirportName();
        airportDb.put(key, airport);
        return "SUCCESS";
    }

    public String addFlight(Flight flight) {
        flightDb.put(flight.getFlightId(), flight);
        return "SUCCESS";
    }

    public String addPassenger(Passenger passenger) {
        passengerDb.put(passenger.getPassengerId(), passenger);
        return "SUCCESS";
    }

    public String bookATicket(Integer flightId, Integer passengerId) {

        Flight flight = flightDb.get(flightId);
        List<Integer> passengers = flightTicketBookingDb.get(flightId);
        int noOfPassengers = passengers.size();
        if(noOfPassengers < flight.getMaxCapacity() && !passengers.contains(passengerId)){
            passengers.add(passengerId);
            flightTicketBookingDb.put(flightId, passengers);
            return "SUCCESS";
        }
        else
            return "FAILURE";

    }
    public String getLargestAirportName() {
        return null;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        return 0;
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        return 0;
    }

    public int calculateFlightFare(Integer flightId) {
        return 0;
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        return null;
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        return 0;
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        return null;
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        return 0;
    }
}