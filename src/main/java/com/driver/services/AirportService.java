package com.driver.services;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.reposirtory.AirportRepository;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class AirportService {
    AirportRepository airportRepository = new AirportRepository();

    public String addAirport(Airport airport){
        String add = airportRepository.addAirport(airport);
        return add;
    }

    public String addFlight(Flight flight) {
        String add = airportRepository.addFlight(flight);
        return add;
    }

    public String addPassenger(Passenger passenger) {
        String added = airportRepository.addPassenger(passenger);
        return added;
    }

    public String getLargestAirportName() {
        String ans = airportRepository.getLargestAirportName();
        return ans;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        double time = airportRepository.getShortestDurationOfPossibleBetweenTwoCities(fromCity, toCity);
        return time;
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int number = airportRepository.getNumberOfPeopleOn(date, airportName);
        return number;
    }

    public int calculateFlightFare(Integer flightId) {
        int flightFare = airportRepository.calculateFlightFare(flightId);
        return flightFare;
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        String ticket = airportRepository.bookATicket(flightId, passengerId);
        return ticket;
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        String cancel = airportRepository.cancelATicket(flightId, passengerId);
        return cancel;
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        int numberOfBooking = airportRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);
        return numberOfBooking;
    }



    public String getAirportNameFromFlightId(Integer flightId) {
        String airPortName = airportRepository.getAirportNameFromFlightId(flightId);
        return airPortName;
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int revenue = airportRepository.calculateRevenueOfAFlight(flightId);
        return revenue;
    }


}
