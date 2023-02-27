package com.oopProjects.testParking;

import com.oopProjects.exception.InvalidVehicleNumberException;
import com.oopProjects.exception.ParkingFullException;
import com.oopProjects.model.Ticket;
import com.oopProjects.model.Vehicle;
import com.oopProjects.model.VehicleSize;
import com.oopProjects.service.ParkingLot;
import com.oopProjects.strategy.FourWheelerWeekendChargeStrategy;
import com.oopProjects.strategy.TwoWheelerWeekDayChargeStrategy;

public class TestParking {
        public static void main(String[] args) throws ParkingFullException, InvalidVehicleNumberException {

            ParkingLot parkingLot = ParkingLot.getParkingLot(); // create instance ;
            parkingLot.initializeParkingSlots(10,10);

            Vehicle vehicle1 = new Vehicle("ABC11" , VehicleSize.FourWheels) ;
            Ticket ticket1 = parkingLot.park(vehicle1) ;
            System.out.println(ticket1);


            Vehicle vehicle2 = new Vehicle("ABC11" , VehicleSize.TwoWheels) ;
            Ticket ticket2 = parkingLot.park(vehicle2) ;
            System.out.println(ticket2);

            int cost1 = parkingLot.unPark(ticket1, new FourWheelerWeekendChargeStrategy());
            System.out.println(cost1);

            int cost2 = parkingLot.unPark(ticket2, new TwoWheelerWeekDayChargeStrategy());
            System.out.println(cost2);

        }
}

