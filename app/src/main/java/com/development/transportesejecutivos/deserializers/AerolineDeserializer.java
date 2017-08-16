package com.development.transportesejecutivos.deserializers;

import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.models.Aeroline;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Will Montiel on 08/15/2017.
 */

public class AerolineDeserializer {
    public ArrayList<Aeroline> aerolineArrayList = new ArrayList<>();
    public JSONArray jsonArray;

    public void setJson(JSONArray array) {
        this.jsonArray = array;
    }

    public void deserialize() {
        Aeroline ae = new Aeroline();
        ae.setId(0);
        ae.setName("Sin aerolínea");
        this.aerolineArrayList.add(ae);

        try {
            for (int i = 0; i < this.jsonArray.length(); i++) {
                JSONObject jsonServiceObject = (JSONObject) this.jsonArray.get(i);
                Aeroline aeroline = new Aeroline();
                aeroline.setId(jsonServiceObject.getInt(JsonKeys.AEROLINE_ID));
                aeroline.setName(jsonServiceObject.getString(JsonKeys.AEROLINE_NAME));

                this.aerolineArrayList.add(aeroline);
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Aeroline> getAerolineArrayList() {
        return this.aerolineArrayList;
    }
}
