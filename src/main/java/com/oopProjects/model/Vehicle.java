package com.oopProjects.model;

public class Vehicle {

    private VehicleSize vehicleSize  ;
    private String vehicleNumber ;

    public Vehicle(String vehicleNumber , VehicleSize vehicleSize){
        this.vehicleNumber = vehicleNumber ;
        this.vehicleSize= vehicleSize ;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }
    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

}
