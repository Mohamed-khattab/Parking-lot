package com.oopProjects.service;

import com.oopProjects.exception.InvalidVehicleNumberException;
import com.oopProjects.exception.ParkingFullException;
import com.oopProjects.model.Slot;
import com.oopProjects.model.Ticket;
import com.oopProjects.model.Vehicle;
import com.oopProjects.model.VehicleSize;
import com.oopProjects.strategy.ParkingChargeStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingLot implements Parking {

    private static ParkingLot parkingLot ;
    private final List<Slot> twoWheelsSlots ;
    private final List<Slot> fourWheelsSlots ;

    public ParkingLot() {
        this.twoWheelsSlots = new ArrayList<>();
        this.fourWheelsSlots = new ArrayList<>();
    }

    public static ParkingLot getParkingLot(){
        if(parkingLot == null )
            parkingLot = new ParkingLot() ;
        return parkingLot ;
    }
    public boolean initializeParkingSlots(int numberOfTwoWheelerParkingSlots, int numberOfFourWheelerParkingSlots) {

        for (int i = 1; i <= numberOfTwoWheelerParkingSlots; i++) {
            twoWheelsSlots.add(new Slot(i));
        }

        System.out.printf("Created a two wheeler parking lot with %s slots %n", numberOfTwoWheelerParkingSlots);

        for (int i = 1; i <= numberOfFourWheelerParkingSlots; i++) {
            fourWheelsSlots.add(new Slot(i));
        }
        System.out.printf("Created a four wheeler parking lot with %s slots %n \n \n ", numberOfFourWheelerParkingSlots);
        return true;
    }
    @Override
    public Ticket park(Vehicle vehicle) throws ParkingFullException {
        Slot nextAvailableSlot ;
        if( vehicle.getVehicleSize().equals(VehicleSize.FourWheels)){
            nextAvailableSlot = getNextAvailableFourWheelerSlot() ;
        }
        else {
            nextAvailableSlot = getNextAvailableTwoWheelerSlot();
        }
        nextAvailableSlot.occupySlot(vehicle);
        System.out.printf("Allocated slot number : %d \n ", nextAvailableSlot.getSlotNumber());
        Ticket ticket = new Ticket(nextAvailableSlot.getSlotNumber() ,vehicle.getVehicleNumber() ,new Date(), vehicle.getVehicleSize());
        return ticket;
    }
    private Slot getNextAvailableFourWheelerSlot() throws ParkingFullException {
        for(Slot slot: fourWheelsSlots){
            if(slot.isEmpty()) {
                return slot;
            }
        }
        throw new ParkingFullException("No Empty Slot available");
    }
    private Slot getNextAvailableTwoWheelerSlot() throws ParkingFullException {
        for (Slot slot :twoWheelsSlots){
            if(slot.isEmpty()){
                return slot ;
            }
        }
        throw new ParkingFullException("No Empty Slot available") ;
    }

    @Override
    public int unPark( Ticket ticket, ParkingChargeStrategy parkingChargeStrategy) throws InvalidVehicleNumberException {
      int costByHours =0 ;
      Slot slot = null;
      try{
          if(ticket.getVehicleSize().equals(VehicleSize.FourWheels)){
            slot = getFourWheelerSlotByVehicleNumber(ticket.getVehicleNumber());
          }
          if(ticket.getVehicleSize().equals(VehicleSize.TwoWheels)){
            slot = getTwoWheelerSlotByVehicleNumber(ticket.getVehicleNumber());
          }
          slot.vacateSlot();
           int hours = getHoursParked(ticket.getDate() , new Date()) ;
           costByHours = getCostByHours(hours , parkingChargeStrategy) ;
          System.out.println(
                  "Vehicle with registration " + ticket.getVehicleNumber() + " at slot number " + slot.getSlotNumber()
                          + " was parked for " + hours + " hours and the total charge is " + costByHours);
      }catch (InvalidVehicleNumberException invalidVehicleNumberException){
          System.out.println(invalidVehicleNumberException);
          throw invalidVehicleNumberException;

      }
        return costByHours;
    }

    private int getCostByHours(int hours, ParkingChargeStrategy parkingChargeStrategy) {
        return parkingChargeStrategy.getCharge(hours);
    }
    private int getHoursParked(Date startDate, Date endDate) {
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        return hours;
    }
    private Slot getTwoWheelerSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumberException {
        for (Slot slot : fourWheelsSlots) {
            Vehicle vehicle = slot.getParkVehicle() ;
            if(vehicle !=null && vehicle.getVehicleNumber().equals(vehicleNumber)){
                return slot ;
            }
        }throw new InvalidVehicleNumberException("Four wheeler with registration number " + vehicleNumber + " not found");
    }

    private Slot getFourWheelerSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumberException {
        for(Slot slot : twoWheelsSlots){
            Vehicle vehicle = slot.getParkVehicle() ; 
            if(vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)){
                return slot; 
            }
        } throw new InvalidVehicleNumberException("Two wheeler with registration number " + vehicleNumber + " not found");
    }


}
