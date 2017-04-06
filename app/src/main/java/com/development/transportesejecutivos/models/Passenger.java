package com.development.transportesejecutivos.models;

/**
 * Created by william.montiel on 09/03/2016.
 */
public class Passenger {
    int idPassenger;
    String code;
    String name;
    String lastName;
    String company;
    String phone;
    String email;
    String address;
    String city;

    public Passenger() {

    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getIdPassenger() {
        return this.idPassenger;
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

    public String getCompany() {
        return this.company;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }
}
