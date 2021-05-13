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
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Controllers.RequestHandler;
import com.example.pokestationapp.Models.Days;
import com.example.pokestationapp.Models.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
        while (!request.getResult().isDone()) {
            try {
                JSONArray response = JsonParse.getResponseArr(request.getResult().get());

                for (int i = 0; i < response.length(); i++) {
                    response.getJSONObject(i).get("ingredient_name");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
    }
}