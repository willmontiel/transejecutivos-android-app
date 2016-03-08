package com.development.transejecutivos.deserializers;

import com.development.transejecutivos.adapters.JsonKeys;
import com.development.transejecutivos.models.Service;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class ServiceDeserializer extends DeserializerValidator{

    public JSONObject object;

    public ServiceDeserializer(JSONObject object) {
        this.object = object;
    }

    public ArrayList<Service> deserialize() {

        ArrayList<Service> servicesArray = new ArrayList<>();

        try {
            JSONArray responseArray = this.object.getJSONArray(JsonKeys.SERVICES);

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

                    servicesArray.add(service);
                }
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return servicesArray;
    }
}

