package com.development.transportesejecutivos.deserializers;

import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.models.Date;
import com.development.transportesejecutivos.models.Driver;
import com.development.transportesejecutivos.models.Passenger;
import com.development.transportesejecutivos.models.Service;
import com.development.transportesejecutivos.models.ServiceData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by william.montiel on 29/03/2016.
 */
public class Deserializer extends DeserializerValidator {
    public Service service;
    public Passenger passenger;
    public JSONArray servicesJsonArray;
    public JSONArray datesJsonArray;
    public ArrayList<ArrayList<ServiceData>> services = new ArrayList<ArrayList<ServiceData>>();
    public ArrayList<Date> datesArrayList = new ArrayList<>();


    public void setDatesJsonArray(JSONArray dates) {
        this.datesJsonArray = dates;
    }

    public void setServicesJsonArray(JSONArray services) {
        this.servicesJsonArray = services;
    }

    public void deserializeGroupedServices() {
        try {
            for (int i = 0; i < this.datesJsonArray.length(); i++) {
                String d = (String) this.datesJsonArray.get(i);
                Date date = new Date(d);
                this.datesArrayList.add(date);

                JSONArray services = this.servicesJsonArray.getJSONArray(i);

                ArrayList<ServiceData> currentServicesArray = new ArrayList<>();

                for (int j = 0; j < services.length(); j++) {
                    JSONObject jsonServiceObject = (JSONObject) services.get(j);

                    ServiceDeserializer serviceDeserializer = new ServiceDeserializer();
                    serviceDeserializer.setJsonObject(jsonServiceObject);
                    serviceDeserializer.deserialize();
                    currentServicesArray.add(serviceDeserializer.getService());

                }

                this.services.add(currentServicesArray);
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<ArrayList<ServiceData>> getServicesArray() {
        return this.services;
    }

    public ArrayList<Date> getDatesArray() {
        return datesArrayList;
    }
}
