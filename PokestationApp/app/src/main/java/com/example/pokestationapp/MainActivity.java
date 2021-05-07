package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.pokestationapp.Controllers.Api;
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

        PerformNetworkRequest networkRequest = new PerformNetworkRequest(Api.URL_GET_INGREDIENTS, null, CODE_GET_REQUEST);

        while (!networkRequest.result.isDone()) {
            try {
                System.out.println("Response: " + networkRequest.result.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private class PerformNetworkRequest {

        // Url of the request.
        String url;
        // Params of the request.
        HashMap < String, String > params;
        // Request code for if its a POST or GET request.
        int requestCode;

        PerformNetworkRequest(String url, HashMap < String, String > params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future < String > result = pool.submit(new Callable < String > () {
            @Override
            public String call() throws Exception {
                RequestHandler requestHandler = new RequestHandler();
                if (requestCode == CODE_POST_REQUEST) {
                    return requestHandler.sendPostRequest(url, params);
                }

                if (requestCode == CODE_GET_REQUEST) {
                    return requestHandler.sendGetRequests(url);
                }

                return null;
            }
        });
    }
}