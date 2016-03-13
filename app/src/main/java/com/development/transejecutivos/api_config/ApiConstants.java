package com.development.transejecutivos.api_config;

/**
 * Created by william.montiel on 06/03/2016.
 */
public class ApiConstants {
    public static final String URL_BASE = "http://www.transportesejecutivos.com/api";
    public static final String API_VERSION = "v1";
    public static final String LOGIN = "login";
    public static final String SERVICES = "service";

    public static final String URL_LOGIN = URL_BASE + "/" + API_VERSION + "/" + LOGIN;
    public static final String URL_SERVICES = URL_BASE + "/" + API_VERSION + "/" + SERVICES;
}
