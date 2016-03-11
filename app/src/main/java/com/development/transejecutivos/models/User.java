package com.development.transejecutivos.models;

import java.io.Serializable;

/**
 * Created by william.montiel on 25/09/2015.
 */
public class User implements Serializable {
    int idUser;
    String username;
    String name;
    String lastName;
    String email;
    String role;
    String company;
    String apikey;
    String code;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public int getIdUser() {
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

