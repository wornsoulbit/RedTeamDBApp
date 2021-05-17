package com.example.pokestationapp.Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParse {

    public static JSONArray getResponseArr(String jsonResponse, int requestCode) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        if (requestCode == 1024) {
            return jsonObject.getJSONArray("response");
        } else if(requestCode == 1025){
            return new JSONArray();
        } else {
            return jsonObject.getJSONArray("error");
        }
    }

}
