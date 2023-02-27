package com.oopProjects.model;

public class Slot {
    private int slotNumber  ;
    private boolean isEmpty = true ;
    private Vehicle parkVehicle ;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Vehicle getParkVehicle() {
        return parkVehicle;
    }

    public void setParkVehicle(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
    }
    public void vacateSlot(){
        this.parkVehicle = null ;
        this.isEmpty = true ;
    }
    public void occupySlot(Vehicle parkVehicle){
        this.parkVehicle = parkVehicle ;
        this.isEmpty = false ;
    }


}
