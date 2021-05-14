package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

        RecyclerView recyclerView = findViewById(R.id.ingredients_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Ingredients_RecyclerViewAdapter(this, ingredients);

        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, Ingredient_Details.class);
        intent.putExtra("ingredient_id", adapter.getItem(position).getIngredient_id());
        //intent.putExtra("supplier_id", adapter.getItem(position).getSupplier_id());
        intent.putExtra("order_day", adapter.getItem(position).getOrder_day());
        intent.putExtra("ingredient_name", adapter.getItem(position).getIngredient_name());
        intent.putExtra("ingredient_type", adapter.getItem(position).getIngredient_type());
        startActivity(intent);
    }

    private void readIngredients() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_GET_INGREDIENTS, null, 1024);
        while (!request.getResult().isDone()) {
            try {
                ingredients.clear();

                JSONArray response = JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode());
                for (int i = 0; i < response.length(); i++)
                {
                    //getting each hero object
                    JSONObject obj = response.getJSONObject(i);

                    //adding the hero to the list
                    ingredients.add(new Ingredient(
                            obj.getInt("ingredient_id"),
                            obj.getInt("supplier_id"),
                            Days.valueOf(obj.getString("order_day").toUpperCase()),
                            obj.getString("ingredient_name"),
                            obj.getString("ingredient_type")/*,
                            obj.getInt("stock"),
                            obj.getInt("amount_needed")*/
                    ));
                }

                Log.e("test", ingredients.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}