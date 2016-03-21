package com.development.transejecutivos.deserializers;

import android.util.Log;

import com.development.transejecutivos.adapters.JsonKeys;
import com.development.transejecutivos.models.Date;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Passenger;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.ServiceData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class ServiceDeserializer extends DeserializerValidator{

    public JSONArray servicesArray;
    public JSONArray datesArray;
    public ArrayList<ArrayList<ServiceData>> services = new ArrayList<ArrayList<ServiceData>>();
    public ArrayList<Date> datesList = new ArrayList<>();

    public ServiceDeserializer(JSONArray services, JSONArray dates) {
        this.servicesArray = services;
        this.datesArray = dates;
    }

    public void deserialize() {
        try {
            for (int i = 0; i < datesArray.length(); i++) {
                String d = (String) datesArray.get(i);
                Date date = new Date(d);
                this.datesList.add(date);

                JSONArray services = servicesArray.getJSONArray(i);

                ArrayList<ServiceData> currentServicesArray = new ArrayList<>();

                for (int j = 0; j < services.length(); j++) {
                    JSONObject jsonServiceObject = (JSONObject) services.get(j);

                    int idService = validateInt(JsonKeys.SERVICE_ID, jsonServiceObject);

                    if (idService != 0) {
                        ServiceData serviceData = new ServiceData();

                        String reference = validateString(JsonKeys.SERVICE_REFERENCE, jsonServiceObject);
                        String createDate = validateString(JsonKeys.SERVICE_CREATE_DATE, jsonServiceObject);
                        String startDate = validateString(JsonKeys.SERVICE_START_DATE, jsonServiceObject);
                        String fly = validateString(JsonKeys.SERVICE_FLY, jsonServiceObject);
                        String aeroline = validateString(JsonKeys.SERVICE_AEROLINE, jsonServiceObject);
                        String company = validateString(JsonKeys.SERVICE_COMPANY, jsonServiceObject);
                        String paxCant = validateString(JsonKeys.SERVICE_PAX_CANT, jsonServiceObject);
                        String pax = validateString(JsonKeys.SERVICE_PAX, jsonServiceObject);
                        String source = validateString(JsonKeys.SERVICE_SOURCE, jsonServiceObject);
                        String destiny = validateString(JsonKeys.SERVICE_DESTINY, jsonServiceObject);
                        String observations = validateString(JsonKeys.SERVICE_OBSERVATIONS, jsonServiceObject);

                        serviceData.setIdService(idService);
                        serviceData.setReference(reference);
                        serviceData.setCreateDate(createDate);
                        serviceData.setStartDate(startDate);
                        serviceData.setFly(fly);
                        serviceData.setAeroline(aeroline);
                        serviceData.setCompany(company);
                        serviceData.setPaxCant(paxCant);
                        serviceData.setPax(pax);
                        serviceData.setSource(source);
                        serviceData.setDestiny(destiny);
                        serviceData.setObservations(observations);

                        int idDriver = validateInt(JsonKeys.DRIVER_ID, jsonServiceObject);
                        String dcode = validateString(JsonKeys.DRIVER_CODE, jsonServiceObject);
                        String dname = validateString(JsonKeys.DRIVER_NAME, jsonServiceObject);
                        String dlastname = validateString(JsonKeys.DRIVER_LASTNAME, jsonServiceObject);
                        String dphone1 = validateString(JsonKeys.DRIVER_PHONE1, jsonServiceObject);
                        String dphone2 = validateString(JsonKeys.DRIVER_PHONE2, jsonServiceObject);
                        String daddress = validateString(JsonKeys.DRIVER_ADDRESS, jsonServiceObject);
                        String dcity = validateString(JsonKeys.DRIVER_CITY, jsonServiceObject);
                        String demail = validateString(JsonKeys.DRIVER_EMAIL, jsonServiceObject);
                        String carType = validateString(JsonKeys.DRIVER_CAR_TYPE, jsonServiceObject);
                        String carBrand = validateString(JsonKeys.DRIVER_CAR_BRAND, jsonServiceObject);
                        String carModel = validateString(JsonKeys.DRIVER_CAR_MODEL, jsonServiceObject);
                        String carColor = validateString(JsonKeys.DRIVER_CAR_COLOR, jsonServiceObject);
                        String carriagePlate = validateString(JsonKeys.DRIVER_CARRIAGE_PLATE, jsonServiceObject);
                        String dstatus = validateString(JsonKeys.DRIVER_STATUS, jsonServiceObject);

                        serviceData.setIdDriver(idDriver);
                        serviceData.setCode(dcode);
                        serviceData.setName(dname);
                        serviceData.setLastName(dlastname);
                        serviceData.setPhone1(dphone1);
                        serviceData.setPhone2(dphone2);
                        serviceData.setAddress(daddress);
                        serviceData.setCity(dcity);
                        serviceData.setEmail(demail);
                        serviceData.setCarType(carType);
                        serviceData.setCarBrand(carBrand);
                        serviceData.setCarModel(carModel);
                        serviceData.setCarColor(carColor);
                        serviceData.setCarriagePlate(carriagePlate);
                        serviceData.setStatus(dstatus);


                        currentServicesArray.add(serviceData);
                    }

                    this.services.add(currentServicesArray);
                }
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<ArrayList<ServiceData>> getServices() {
        return this.services;
    }

    public ArrayList<Date> getDatesArray() {
        return datesList;
    }
}

