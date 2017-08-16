package com.development.transportesejecutivos.deserializers;

import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.models.CarType;
import com.development.transportesejecutivos.models.Date;
import com.development.transportesejecutivos.models.ServiceData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Will Montiel on 08/15/2017.
 */

public class CarTypeDeserializer {
    public ArrayList<CarType> carTypeArrayList = new ArrayList<>();
    public JSONArray jsonArray;

    public void setJson(JSONArray array) {
        this.jsonArray = array;
    }

    public void deserialize() {
        try {
            for (int i = 0; i < this.jsonArray.length(); i++) {
                JSONObject jsonServiceObject = (JSONObject) this.jsonArray.get(i);
                CarType carType = new CarType();
                carType.setId(jsonServiceObject.getInt(JsonKeys.CAR_TYPE_ID));
                carType.setName(jsonServiceObject.getString(JsonKeys.CAR_TYPE_NAME));

                this.carTypeArrayList.add(carType);
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<CarType> getCarTypeArrayList() {
        return this.carTypeArrayList;
    }
}
