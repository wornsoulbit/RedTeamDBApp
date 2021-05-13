package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.RequestHandler;
import com.example.pokestationapp.Models.Days;
import com.example.pokestationapp.Models.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ingredients_Display extends AppCompatActivity implements Ingredients_RecyclerViewAdapter.ItemClickListener
{
    Ingredients_RecyclerViewAdapter adapter;
    ArrayList<Ingredient> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_display);

        readIngredients();

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"you clicked " +
                adapter.getItem(position) + " on row number" + position, Toast.LENGTH_LONG).show();

    }

    private void readIngredients() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_GET_INGREDIENTS, null, 1024);
        request.execute();
    }

    private void refreshIngredientList(JSONArray list) throws JSONException {
        //clearing previous heroes
        ingredients.clear();

        //traversing through all the items in the json array
        //the json we got from the response
        for (int i = 0; i < list.length(); i++) {
            //getting each hero object
            JSONObject obj = list.getJSONObject(i);

            //adding the hero to the list
            ingredients.add(new Ingredient(
                    obj.getInt("ingredient_id"),
                    obj.getInt("supplier_id"),
                    Days.valueOf(obj.getString("order_day").toUpperCase()),
                    obj.getString("ingredient_name"),
                    obj.getString("ingredient_type")
            ));
        }

        ingredients.add(new Ingredient(
                1,
                4,
                Days.MONDAY,
                "StrawBerry",
                "fruit"));

        Log.e("test", ingredients.toString());

        /*RecyclerView recyclerView = findViewById(R.id.ingredients_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Ingredients_RecyclerViewAdapter(this, ingredients);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);*/

        //creating the adapter and setting it to the listview
        //HeroAdapter adapter = new HeroAdapter(heroList);
        //listView.setAdapter(adapter);
    }

    /*
    private class PerformNetworkRequest {

        // Url of the request.
        String url;
        // Params of the request.
        HashMap< String, String > params;
        // Request code for if its a POST or GET request.
        int requestCode;

        PerformNetworkRequest(String url, HashMap < String, String > params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future< String > result = pool.submit(new Callable< String >() {
            @Override
            public String call() throws Exception {
                RequestHandler requestHandler = new RequestHandler();
                if (requestCode == 1025) {
                    return requestHandler.sendPostRequest(url, params);
                }

                if (requestCode == 1024) {
                    return requestHandler.sendGetRequests(url);
                }

                return null;
            }
        });
    }
    */

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, String> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        //when the task started displaying a progressbar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar.setVisibility(View.VISIBLE);
        }


        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    //refreshing the herolist after every operation
                    //so we get an updated list
                    //we will create this method right now it is commented
                    //because we haven't created it yet
                    refreshIngredientList(object.getJSONArray("ingredients"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == 1025)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == 1024)
                return requestHandler.sendGetRequests(url);

            return null;
        }
    }
}