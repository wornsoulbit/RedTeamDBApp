package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ArrayList<Orders> orders = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_display);

        intent = getIntent();

        readOrders();
        readIngredients();

        RecyclerView recyclerView = findViewById(R.id.orders_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Orders_RecyclerViewAdapter(this, ingredients, orders);

        recyclerView.setAdapter(adapter);
    }

    private void readIngredients() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_GET_INGREDIENTS, null, 1024);
        while (!request.getResult().isDone()) {
            try {
                ingredients.clear();

                JSONArray response = JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode());
                for (int i = 0; i < response.length(); i++)
                {
                    //getting each order object
                    JSONObject obj = response.getJSONObject(i);

                    Ingredient ingredient = new Ingredient(obj.getInt("ingredient_id"),
                            Days.valueOf(intent.getStringExtra("order_day").toString().toUpperCase()),
                            obj.getString("ingredient_name"),
                            obj.getString("ingredient_type"),
                            obj.getInt("stock"),
                            obj.getInt("amount_needed"));

                    for(int j = 0; j < orders.size(); j++)
                    {
                        //Log.e("test1", ingredient.getIngredient_id()+"");
                        if(ingredient.getIngredient_id() == orders.get(j).getIngredient_id())
                        {
                            ingredients.add(ingredient);
                        }
                    }

                            //adding the order to the list
                            /*ingredients.add(new Ingredient(
                                    obj.getInt("ingredient_id"),
                                    Days.valueOf(obj.getString("order_day").toUpperCase()),
                                    obj.getString("ingredient_name"),
                                    obj.getString("ingredient_type"),
                                    obj.getInt("stock"),
                                    obj.getInt("amount_needed")
                            ));*/
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

    public void addToOrders(View view) {
        Intent sending = new Intent(this, Orders_add.class);
        sending.putExtra("supplier_name", intent.getStringExtra("supplier_name"));
        sending.putExtra("supplier_id", intent.getIntExtra("supplier_id", 0));
        sending.putExtra("order_day", intent.getStringExtra("order_day"));
        startActivity(sending);
    }

    public void readOrders()
    {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_GET_ORDERS, null, 1024);
        while (!request.getResult().isDone()) {
            try {
                orders.clear();

                JSONArray response = JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode());
                for (int i = 0; i < response.length(); i++)
                {
                    //getting each hero object
                    JSONObject obj = response.getJSONObject(i);

                    Orders order = new Orders(
                            obj.getInt("order_id"),
                            obj.getInt("supplier_id"),
                            obj.getInt("ingredient_id"),
                            obj.getInt("stock_id"),
                            Days.valueOf(obj.getString("order_day").toUpperCase()));

                    if(order.getOrder_day().toString().toUpperCase().equals(intent.getStringExtra("order_day")) &&
                        order.getSupplier_id() == intent.getIntExtra("supplier_id", 0))
                    {
                        orders.add(order);
                    }
                    //adding the hero to the list
                    /*orders.add(new Orders(
                            obj.getInt("order_id"),
                            obj.getInt("supplier_id"),
                            obj.getInt("ingredient_id"),
                            obj.getInt("stock_id"),
                            Days.valueOf(obj.getString("order_day").toUpperCase())
                    ));*/
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

    public void copyToClipboard(View view)
    {
        Toast.makeText(this, "Order Copied", Toast.LENGTH_SHORT).show();
        String result = intent.getStringExtra("supplier_name") + "\n" + intent.getStringExtra("order_day") + "'s Delivery \n";
        for(int i = 0; i < ingredients.size(); i++)
        {
            result += ingredients.get(i).getIngredient_name() + ": " + (ingredients.get(i).getAmount_needed()- ingredients.get(i).getStock()) + " crates\n";
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(intent.getStringExtra("order_day") + "'s order", result);
        clipboard.setPrimaryClip(clip);
    }
}