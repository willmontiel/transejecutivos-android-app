package com.development.transejecutivos.deserializers;

import com.development.transejecutivos.adapters.JsonKeys;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Passenger;
import com.development.transejecutivos.models.Service;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class ServiceDeserializer extends DeserializerValidator{

    public JSONArray responseArray;
    public ArrayList<Service> servicesArray = new ArrayList<>();
    public ArrayList<Passenger> passengersArray = new ArrayList<>();
    public ArrayList<Driver> driversArray = new ArrayList<>();

    public ServiceDeserializer(JSONArray array) {
        this.responseArray = array;
    }

    public void deserialize() {
        try {
            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) responseArray.get(i);
                int idService = validateInt(JsonKeys.SERVICE_ID, jsonObject);

                if (idService != 0) {
                    String reference = validateString(JsonKeys.SERVICE_REFERENCE, jsonObject);
                    String createDate = validateString(JsonKeys.SERVICE_CREATE_DATE, jsonObject);
                    String startDate = validateString(JsonKeys.SERVICE_START_DATE, jsonObject);
                    String endDate = validateString(JsonKeys.SERVICE_END_DATE, jsonObject);
                    String fly = validateString(JsonKeys.SERVICE_FLY, jsonObject);
                    String aeroline = validateString(JsonKeys.SERVICE_AEROLINE, jsonObject);
                    String company = validateString(JsonKeys.SERVICE_COMPANY, jsonObject);
                    String passengerType = validateString(JsonKeys.SERVICE_PASSG_TYPE, jsonObject);
                    String paxCant = validateString(JsonKeys.SERVICE_PAX_CANT, jsonObject);
                    String represent = validateString(JsonKeys.SERVICE_REPRESENT, jsonObject);
                    String source = validateString(JsonKeys.SERVICE_SOURCE, jsonObject);
                    String destiny = validateString(JsonKeys.SERVICE_DESTINY, jsonObject);
                    String observations = validateString(JsonKeys.SERVICE_OBSERVATIONS, jsonObject);

                    Service service = new Service(idService, reference, createDate, startDate, endDate, fly, aeroline, company, passengerType, paxCant, represent, source, destiny, observations);

                    int idPassenger = validateInt(JsonKeys.PASSENGER_ID, jsonObject);
                    String pcode = validateString(JsonKeys.PASSENGER_CODE, jsonObject);
                    String pname = validateString(JsonKeys.PASSENGER_NAME, jsonObject);
                    String plastName = validateString(JsonKeys.PASSENGER_LASTNAME, jsonObject);
                    String pcompany = validateString(JsonKeys.PASSENGER_COMPANY, jsonObject);
                    String pphone = validateString(JsonKeys.PASSENGER_PHONE, jsonObject);
                    String pemail = validateString(JsonKeys.PASSENGER_EMAIL, jsonObject);
                    String paddress = validateString(JsonKeys.PASSENGER_ADDRESS, jsonObject);
                    String pcity = validateString(JsonKeys.PASSENGER_CITY, jsonObject);

                    Passenger passenger = new Passenger(idPassenger, pcode, pname, plastName, pcompany, pphone, pemail, paddress, pcity);

                    int idDriver = validateInt(JsonKeys.DRIVER_ID, jsonObject);
                    String dcode = validateString(JsonKeys.DRIVER_CODE, jsonObject);
                    String dname = validateString(JsonKeys.DRIVER_NAME, jsonObject);
                    String dlastname = validateString(JsonKeys.DRIVER_LASTNAME, jsonObject);
                    String dphone = validateString(JsonKeys.DRIVER_PHONE, jsonObject);
                    String daddress = validateString(JsonKeys.DRIVER_ADDRESS, jsonObject);
                    String dcity = validateString(JsonKeys.DRIVER_CITY, jsonObject);
                    String demail = validateString(JsonKeys.DRIVER_EMAIL, jsonObject);
                    String carType = validateString(JsonKeys.DRIVER_CAR_TYPE, jsonObject);
                    String carBrand = validateString(JsonKeys.DRIVER_CAR_BRAND, jsonObject);
                    String carModel = validateString(JsonKeys.DRIVER_CAR_MODEL, jsonObject);
                    String carColor = validateString(JsonKeys.DRIVER_CAR_COLOR, jsonObject);
                    String carriagePlate = validateString(JsonKeys.DRIVER_CARRIAGE_PLATE, jsonObject);
                    String dstatus = validateString(JsonKeys.DRIVER_STATUS, jsonObject);

                    Driver driver = new Driver(idDriver, dcode, dname, dlastname, dphone, daddress, dcity, demail, carType, carBrand, carModel, carColor, carriagePlate, dstatus);

                    this.servicesArray.add(service);
                    this.passengersArray.add(passenger);
                    this.driversArray.add(driver);
                }
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Service> getServices() {
        return this.servicesArray;
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengersArray;
    }

    public ArrayList<Driver> getDrivers() {
        return this.driversArray;
    }
}

