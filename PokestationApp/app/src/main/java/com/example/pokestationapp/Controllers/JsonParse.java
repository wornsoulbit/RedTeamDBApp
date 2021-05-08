package com.example.pokestationapp.Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParse {

    public static ArrayList<String> getResponseArr(String jsonResponse) throws JSONException {
        ArrayList<String> responseArr = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray response = jsonObject.getJSONArray("response");

        for (int i = 0; i < response.length(); i++) {
            responseArr.add(response.get(i).toString());
        }

        return responseArr;
    }

}
