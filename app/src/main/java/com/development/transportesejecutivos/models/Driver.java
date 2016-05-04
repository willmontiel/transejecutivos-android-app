package com.development.transportesejecutivos.models;

/**
 * Created by william.montiel on 10/03/2016.
 */
public class Driver {
    int idDriver;
    String code;
    String name;
    String lastName;
    String phone1;
    String phone2;
    String address;
    String city;
    String email;
    String carType;
    String carBrand;
    String carModel;
    String carColor;
    String carriagePlate;
    String status;
    int location;

    /**
     *
     * @param idDriver
     * @param code
     * @param name
     * @param lastName
     * @param phone1
     * @param phone2
     * @param address
     * @param city
     * @param email
     * @param carType
     * @param carBrand
     * @param carModel
     * @param carColor
     * @param carriagePlate
     * @param status
     */
    public Driver(int idDriver, String code, String name, String lastName, String phone1, String phone2, String address, String city, String email, String carType, String carBrand, String carModel, String carColor, String carriagePlate, String status, int location) {
        this.idDriver = idDriver;
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
        this.city = city;
        this.email = email;
        this.carType = carType;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carriagePlate = carriagePlate;
        this.status = status;
        this.location = location;
    }

    public int getIdDriver() {
        return this.idDriver;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public String getPhone2() {
        return this.phone2;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public String getCarColor() {
        return this.carColor;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public String getCarriagePlate() {
        return this.carriagePlate;
    }

    public String getCarType() {
        return this.carType;
    }

    public String getStatus() {
        return this.status;
    }

    public int getLocation() {
        return this.location;
    }
}
