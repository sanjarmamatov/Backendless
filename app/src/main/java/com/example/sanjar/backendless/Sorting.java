package com.example.sanjar.backendless;

/**
 * Created by ThirtySeven on 01.10.2016.
 */
public class Sorting {

    String email;
    String street;
    String apartmentType;
    String flatNumber;
    Integer floorCount;
    Integer roomsCount;

    Sorting() {
    }

    public Sorting(String street, String apartmentType, String flatNumber, String email, int floorCount, int roomsCount) {

        this.street = street;
        this.apartmentType = apartmentType;
        this.flatNumber = flatNumber;
        this.email = email;
        this.floorCount = floorCount;
        this.roomsCount = roomsCount;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

}
