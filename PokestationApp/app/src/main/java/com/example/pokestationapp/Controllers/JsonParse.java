package com.example.pokestationapp.Controllers;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParse {

    public static void processResponse(String jsonResponse) throws JSONException {
        Gson g = new Gson();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray response = jsonObject.getJSONArray("response");
        for (int i = 0; i < response.length(); i++) {
            System.out.println(response.get(i));
        }
    }

}
