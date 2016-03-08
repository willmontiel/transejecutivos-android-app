package com.development.transejecutivos.models;

import java.io.Serializable;

/**
 * Created by william.montiel on 25/09/2015.
 */
public class User implements Serializable {
    String idUser;
    String username;
    String name;
    String lastName;
    String email;
    String role;
    String company;
    String apikey;
    String code;

    /**
     * Create an user object
     * @param idUser
     * @param username
     * @param name
     * @param lastName
     * @param email
     * @param role
     * @param company
     * @param apikey
     * @param code
     */
    public User(String idUser, String username, String name, String lastName, String email, String role, String company, String apikey, String code) {
        this.idUser = idUser;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.company = company;
        this.apikey = apikey;
        this.code = code;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getCompany() {
        return company;
    }

    public String getApikey() {
        return apikey;
    }

    public String getCode() {
        return code;
    }
}

