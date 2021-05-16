package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Models.Days;
import com.example.pokestationapp.Models.Ingredient;
import com.example.pokestationapp.Models.Orders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Orders_Display extends AppCompatActivity {

    Orders_RecyclerViewAdapter adapter;
    ArrayList<Ingredient> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_display);

        readOrders();

        RecyclerView recyclerView = findViewById(R.id.orders_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Orders_RecyclerViewAdapter(this, orders);

        recyclerView.setAdapter(adapter);

    }

    private void readOrders() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_GET_INGREDIENTS, null, 1024);
        while (!request.getResult().isDone()) {
            try {
                orders.clear();

                JSONArray response = JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode());
                for (int i = 0; i < response.length(); i++)
                {
                    //getting each order object
                    JSONObject obj = response.getJSONObject(i);

                    //adding the order to the list
                    orders.add(new Ingredient(
                            obj.getInt("ingredient_id"),
                            Days.valueOf(obj.getString("order_day").toUpperCase()),
                            obj.getString("ingredient_name"),
                            obj.getString("ingredient_type"),
                            obj.getInt("stock"),
                            obj.getInt("amount_needed")
                    ));
                }

                Log.e("test", orders.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addToOrders(View view) {
        Intent intent = new Intent(this, Orders_add.class);
        startActivity(intent);
    }
}