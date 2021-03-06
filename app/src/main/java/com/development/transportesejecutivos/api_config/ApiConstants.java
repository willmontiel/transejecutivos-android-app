package com.development.transportesejecutivos.api_config;

/**
 * Created by william.montiel on 06/03/2016.
 */
public class ApiConstants {
    public static final String URL_BASE = "http://www.transportesejecutivos.com";
    public static final String API = "api";
    public static final String API_VERSION = "v1";
    public static final String LOGIN = "login";
    public static final String SERVICE = "service";
    public static final String SERVICES = "services";
    public static final String SERVICES_GROUPED = "servicesgrouped";
    public static final String RECOVERPASS = "recoverpassword";
    public static final String GET_PRELOCATION = "getprelocation";
    public static final String UPDATE_PROFILE = "updateprofile";
    public static final String RESET_PASSWORD = "resetpassword";
    public static final String DRIVER_PHOTO = "app/conductores/logos/redimensionar_logo.php?imagen=";
    public static final String CAR_PHOTO = "app/conductores/logos/redimensionar_logo.php?imagen=";
    public static final String GET_CAR_TYPES = "getcartypes";
    public static final String GET_AEROLINES = "getaerolines";
    public static final String REQUEST_SERVICE = "requestservice";

    public static final String URL_LOGIN = URL_BASE + "/" + API + "/" + API_VERSION + "/" + LOGIN;
    public static final String URL_SERVICE = URL_BASE + "/" + API + "/" + API_VERSION + "/" + SERVICE;
    public static final String URL_SERVICES = URL_BASE + "/" + API + "/" + API_VERSION + "/" + SERVICES;
    public static final String URL_SERVICES_GROUPED = URL_BASE + "/" + API + "/" + API_VERSION + "/" + SERVICES_GROUPED;
    public static final String URL_RECOVER_PASSWORD = URL_BASE + "/" + API + "/" + API_VERSION + "/" + RECOVERPASS;
    public static final String URL_UPDATE_PROFILE = URL_BASE + "/" + API + "/" + API_VERSION + "/" + UPDATE_PROFILE;
    public static final String URL_GET_PRELOCATION = URL_BASE + "/" + API + "/" + API_VERSION + "/" + GET_PRELOCATION;
    public static final String URL_RESET_PASSWORD = URL_BASE + "/" + API + "/" + API_VERSION + "/" + RESET_PASSWORD;
    public static final String URL_DRIVER_PHOTO = URL_BASE + "/" + DRIVER_PHOTO;
    public static final String URL_CAR_PHOTO = URL_BASE + "/" + CAR_PHOTO;
    public static final String URL_GET_CAR_TYPES = URL_BASE + "/" + API + "/" + API_VERSION + "/" + GET_CAR_TYPES;
    public static final String URL_GET_AEROLINES = URL_BASE + "/" + API + "/" + API_VERSION + "/" + GET_AEROLINES;
    public static final String URL_REQUEST_SERVICE = URL_BASE + "/" + API + "/" + API_VERSION + "/" + REQUEST_SERVICE;



    public static final String URL_GOOGLE_BASE = "https://maps.googleapis.com/maps/api";
    public static final String URL_GOOGLE_KEY = "key";
    public static final String URL_GOOGLE_INPUT = "input";
    public static final String URL_GOOGLE_TYPES = "types";
    public static final String URL_GOOGLE_PLACES_TYPE = "address";
    public static final String URL_GOOGLE_SENSOR = "sensor";
    public static final String URL_GOOGLE_SENSOR_ENABLE = "false";
    public static final String URL_GOOGLE_OUTPUT = "json";
    public static final String URL_GOOGLE_API_TYPE = "place";
    public static final String URL_GOOGLE_API_PLACE_TYPE = "autocomplete";
    public static final String GOOGLE_PLACES_API_KEY = "AIzaSyAa1bDK_oCzAnpDUbYm5vVJ84S4b01LDLc";

    public static final String URL_GOOGLE_PLACES_API = URL_GOOGLE_BASE + "/" + URL_GOOGLE_API_TYPE + "/" + URL_GOOGLE_API_PLACE_TYPE + "/" + URL_GOOGLE_OUTPUT;

}
