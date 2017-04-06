package com.development.transportesejecutivos.deserializers;

import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.models.Date;
import com.development.transportesejecutivos.models.Driver;
import com.development.transportesejecutivos.models.Service;
import com.development.transportesejecutivos.models.ServiceData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class ServiceDeserializer extends DeserializerValidator{
    public JSONObject jsonObject = new JSONObject();
    public ServiceData service = new ServiceData();

    public JSONArray responseJsonArray;
    public ArrayList<Service> servicesArrayList = new ArrayList<>();
    public ArrayList<Driver> driversArrayList = new ArrayList<>();

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setResponseJsonArray(JSONArray responseJsonArray) {
        this.responseJsonArray = responseJsonArray;
    }

    public void deserialize() {
        int idService = validateInt(JsonKeys.SERVICE_ID, jsonObject);

        if (idService != 0) {
            service.setIdService(idService);
            service.setReference(validateString(JsonKeys.SERVICE_REFERENCE, jsonObject));
            service.setCreateDate(validateString(JsonKeys.SERVICE_CREATE_DATE, jsonObject));
            service.setStartDate(validateString(JsonKeys.SERVICE_START_DATE, jsonObject));
            service.setFly(validateString(JsonKeys.SERVICE_FLY, jsonObject));
            service.setAeroline(validateString(JsonKeys.SERVICE_AEROLINE, jsonObject));
            service.setCompany(validateString(JsonKeys.SERVICE_COMPANY, jsonObject));
            service.setPaxCant(validateString(JsonKeys.SERVICE_PAX_CANT, jsonObject));
            service.setPax(validateString(JsonKeys.SERVICE_PAX, jsonObject));
            service.setSource(validateString(JsonKeys.SERVICE_SOURCE, jsonObject));
            service.setDestiny(validateString(JsonKeys.SERVICE_DESTINY, jsonObject));
            service.setObservations(validateString(JsonKeys.SERVICE_OBSERVATIONS, jsonObject));
            service.setShareLocation(validateInt(JsonKeys.SERVICE_SHARE_LOCATION, jsonObject));

            service.setIdDriver(validateInt(JsonKeys.DRIVER_ID, jsonObject));
            service.setCode(validateString(JsonKeys.DRIVER_CODE, jsonObject));
            service.setName(validateString(JsonKeys.DRIVER_NAME, jsonObject));
            service.setLastName(validateString(JsonKeys.DRIVER_LASTNAME, jsonObject));
            service.setPhone1(validateString(JsonKeys.DRIVER_PHONE1, jsonObject));
            service.setPhone2(validateString(JsonKeys.DRIVER_PHONE2, jsonObject));
            service.setAddress(validateString(JsonKeys.DRIVER_ADDRESS, jsonObject));
            service.setCity(validateString(JsonKeys.DRIVER_CITY, jsonObject));
            service.setEmail(validateString(JsonKeys.DRIVER_EMAIL, jsonObject));
            service.setCarType(validateString(JsonKeys.DRIVER_CAR_TYPE, jsonObject));
            service.setCarBrand(validateString(JsonKeys.DRIVER_CAR_BRAND, jsonObject));
            service.setCarModel(validateString(JsonKeys.DRIVER_CAR_MODEL, jsonObject));
            service.setCarColor(validateString(JsonKeys.DRIVER_CAR_COLOR, jsonObject));
            service.setCarriagePlate(validateString(JsonKeys.DRIVER_CARRIAGE_PLATE, jsonObject));
            service.setStatus(validateString(JsonKeys.DRIVER_STATUS, jsonObject));
            service.setLocation(validateInt(JsonKeys.DRIVER_LOC, jsonObject));

            service.setIdPassenger(validateInt(JsonKeys.PASSENGER_ID, jsonObject));
            service.setPassengerName(validateString(JsonKeys.PASSENGER_NAME, jsonObject));
            service.setPassengerLastname(validateString(JsonKeys.PASSENGER_LASTNAME, jsonObject));
            service.setPassengerPhone1(validateString(JsonKeys.PASSENGER_PHONE, jsonObject));
            service.setPassengerEmail1(validateString(JsonKeys.PASSENGER_EMAIL, jsonObject));
        }
    }

    public void deserializeOneService() {
        try {
            for (int i = 0; i < responseJsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) responseJsonArray.get(i);
                int idService = validateInt(JsonKeys.SERVICE_ID, jsonObject);

                if (idService != 0) {
                    Service service = new Service();
                    service.setIdService(idService);
                    service.setReference(validateString(JsonKeys.SERVICE_REFERENCE, jsonObject));
                    service.setCreateDate(validateString(JsonKeys.SERVICE_CREATE_DATE, jsonObject));
                    service.setStartDate(validateString(JsonKeys.SERVICE_START_DATE, jsonObject));
                    service.setFly(validateString(JsonKeys.SERVICE_FLY, jsonObject));
                    service.setAeroline(validateString(JsonKeys.SERVICE_AEROLINE, jsonObject));
                    service.setCompany(validateString(JsonKeys.SERVICE_COMPANY, jsonObject));
                    service.setPaxCant(validateString(JsonKeys.SERVICE_PAX_CANT, jsonObject));
                    service.setPax(validateString(JsonKeys.SERVICE_PAX, jsonObject));
                    service.setSource(validateString(JsonKeys.SERVICE_SOURCE, jsonObject));
                    service.setDestiny(validateString(JsonKeys.SERVICE_DESTINY, jsonObject));
                    service.setObservations(validateString(JsonKeys.SERVICE_OBSERVATIONS, jsonObject));
                    service.setStatus(validateString(JsonKeys.SERVICE_STATUS, jsonObject));
                    service.setShareLocation(validateInt(JsonKeys.SERVICE_SHARE_LOCATION, jsonObject));

                    int idDriver = validateInt(JsonKeys.DRIVER_ID, jsonObject);
                    String dcode = validateString(JsonKeys.DRIVER_CODE, jsonObject);
                    String dname = validateString(JsonKeys.DRIVER_NAME, jsonObject);
                    String dlastname = validateString(JsonKeys.DRIVER_LASTNAME, jsonObject);
                    String dphone1 = validateString(JsonKeys.DRIVER_PHONE1, jsonObject);
                    String dphone2 = validateString(JsonKeys.DRIVER_PHONE2, jsonObject);
                    String daddress = validateString(JsonKeys.DRIVER_ADDRESS, jsonObject);
                    String dcity = validateString(JsonKeys.DRIVER_CITY, jsonObject);
                    String demail = validateString(JsonKeys.DRIVER_EMAIL, jsonObject);
                    String carType = validateString(JsonKeys.DRIVER_CAR_TYPE, jsonObject);
                    String carBrand = validateString(JsonKeys.DRIVER_CAR_BRAND, jsonObject);
                    String carModel = validateString(JsonKeys.DRIVER_CAR_MODEL, jsonObject);
                    String carColor = validateString(JsonKeys.DRIVER_CAR_COLOR, jsonObject);
                    String carriagePlate = validateString(JsonKeys.DRIVER_CARRIAGE_PLATE, jsonObject);
                    String dstatus = validateString(JsonKeys.DRIVER_STATUS, jsonObject);
                    int location = validateInt(JsonKeys.DRIVER_LOC, jsonObject);

                    Driver driver = new Driver(idDriver, dcode, dname, dlastname, dphone1, dphone2, daddress, dcity, demail, carType, carBrand, carModel, carColor, carriagePlate, dstatus, location);

                    this.servicesArrayList.add(service);
                    this.driversArrayList.add(driver);
                }
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
    }

    public ServiceData getService() {
        return this.service;
    }

    public ArrayList<Driver> getDriversArrayList() {
        return driversArrayList;
    }

    public ArrayList<Service> getServicesArrayList() {
        return servicesArrayList;
    }
}

