package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Controllers.RequestHandler;
import com.example.pokestationapp.Models.Days;
import com.example.pokestationapp.Models.Ingredient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    // Testing The Github Repository
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, String> params = new HashMap<>();
        params.put("supplier_id", "2");
        params.put("order_day", "Friday");
        params.put("ingredient_name", "Cherry Tomato");
        params.put("ingredient_type", "Fruit");

        PerformNetworkRequest networkRequest = new PerformNetworkRequest(Api.URL_GET_STOCKS, null, CODE_GET_REQUEST);

        while (!networkRequest.getResult().isDone()) {
            try {
//                System.out.println("Response: " + networkRequest.result.get());
                try {
                    Log.e("test", JsonParse.getResponseArr(networkRequest.getResult().get()).toString());
                    System.out.println(JsonParse.getResponseArr(networkRequest.getResult().get()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewIngredients(View view)
    {
        Intent intent = new Intent(this, Ingredients_Display.class);
        startActivity(intent);
    }
}