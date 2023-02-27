package com.oopProjects.service;
import com.oopProjects.exception.InvalidVehicleNumberException;
import com.oopProjects.exception.ParkingFullException;
import com.oopProjects.model.Ticket;
import com.oopProjects.model.Vehicle;
import com.oopProjects.strategy.ParkingChargeStrategy;

//import strategy.ParkingChargeStrategy;
public interface Parking {
    public Ticket park(Vehicle vehicle)throws ParkingFullException ;
    public int unPark(Ticket ticket, ParkingChargeStrategy parkingChargeStrategy)throws InvalidVehicleNumberException;


}
