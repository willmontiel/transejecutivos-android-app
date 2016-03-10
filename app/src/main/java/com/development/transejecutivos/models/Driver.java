package com.development.transejecutivos.models;

/**
 * Created by william.montiel on 10/03/2016.
 */
public class Driver {
    int idDriver;
    String code;
    String name;
    String lastName;
    String phone;
    String address;
    String city;
    String email;
    String carType;
    String carBrand;
    String carModel;
    String carColor;
    String carriagePlate;
    String status;

    /**
     * Creata a driver object
     * @param idDriver
     * @param code
     * @param name
     * @param lastName
     * @param phone
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
    public Driver(int idDriver, String code, String name, String lastName, String phone, String address, String city, String email, String carType, String carBrand, String carModel, String carColor, String carriagePlate, String status) {
        this.idDriver = idDriver;
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.email = email;
        this.carType = carType;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carriagePlate = carriagePlate;
        this.status = status;
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

    public String getPhone() {
        return this.phone;
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
}
