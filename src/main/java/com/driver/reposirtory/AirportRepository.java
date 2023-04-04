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
        airportDb.put(airport.getAirportName(),airport);
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

        if(flightTicketBookingDb.get(flightId)!=null &&(flightTicketBookingDb.get(flightId).size()<flightDb.get(flightId).getMaxCapacity())){

            List<Integer> passengers =  flightTicketBookingDb.get(flightId);

            if(passengers.contains(passengerId)){
                return "FAILURE";
            }

            passengers.add(passengerId);
            flightTicketBookingDb.put(flightId,passengers);
            return "SUCCESS";
        }
        else if(flightTicketBookingDb.get(flightId)==null)
        {
            flightTicketBookingDb.put(flightId,new ArrayList<>());
            List<Integer> passengers =  flightTicketBookingDb.get(flightId);

            if(passengers.contains(passengerId)){
                return "FAILURE";
            }

            passengers.add(passengerId);
            flightTicketBookingDb.put(flightId,passengers);
            return "SUCCESS";

        }
        return "FAILURE";

    }
    public String getLargestAirportName() {

        String ans = "";
        int terminals = 0;
        for(Airport airport : airportDb.values()){

            if(airport.getNoOfTerminals()>terminals){
                ans = airport.getAirportName();
                terminals = airport.getNoOfTerminals();
            }else if(airport.getNoOfTerminals()==terminals){
                if(airport.getAirportName().compareTo(ans)<0){
                    ans = airport.getAirportName();
                }
            }
        }
        return ans;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {

        double ans = Integer.MAX_VALUE;
        for(Flight flight : flightDb.values()){
            City from = flight.getFromCity();
            City to = flight.getToCity();
            if(fromCity.equals(from) && toCity.equals(to)){
                ans = Math.min(ans, flight.getDuration());
            }
        }
        if(ans == Integer.MAX_VALUE){
            return -1;
        }
        else{
            return ans;
        }
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        Airport airport = airportDb.get(airportName);
        if(airport==null){
            return 0;
        }
        City city = airport.getCity();
        int count = 0;
        for(Flight flight:flightDb.values()){
            if(date.equals(flight.getFlightDate()))
                if(flight.getToCity().equals(city)||flight.getFromCity().equals(city)){

                    int flightId = flight.getFlightId();
                    count = count + flightTicketBookingDb.get(flightId).size();
                }
        }
        return count;
    }

    public int calculateFlightFare(Integer flightId) {
        int ans = 0;
        List<Integer> passengers = flightTicketBookingDb.get(flightId);
        ans += 3000 + (passengers.size() * 50);
        return ans;
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        List<Integer> passengers = flightTicketBookingDb.get(flightId);
        if(passengers == null){
            return "FAILURE";
        }
        if(passengers.contains(passengerId)){
            passengers.remove(passengerId);
            return "SUCCESS";
        }
        return "FAILURE";
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        HashMap<Integer,List<Integer>> passengerToFlightDb = new HashMap<>();
        int count = 0;
        for(Map.Entry<Integer,List<Integer>> entry: flightTicketBookingDb.entrySet()){
            List<Integer>passengers = entry.getValue();
            for (Integer passenger : passengers){
                if (passenger == passengerId) count++;
            }
        }
        return count;
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        if(flightDb.containsKey(flightId)){
            City city = flightDb.get(flightId).getFromCity();
            for(Airport airport : airportDb.values()){
                if (airport.getCity().equals(city))
                    return airport.getAirportName();
            }
        }
        return null;
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int noOfPeopleBooked = flightTicketBookingDb.get(flightId).size();
        int totalFare = (25 * noOfPeopleBooked * noOfPeopleBooked) + (2975 * noOfPeopleBooked);

        return totalFare;
    }
}
