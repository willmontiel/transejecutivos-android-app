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

    /**
     * Crate a Passenger object
     * @param idPassenger
     * @param code
     * @param name
     * @param lastName
     * @param company
     * @param phone
     * @param email
     * @param address
     * @param city
     */
    public Passenger(int idPassenger, String code, String name, String lastName, String company, String phone, String email, String address, String city) {
        this.idPassenger = idPassenger;
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.address = address;
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
