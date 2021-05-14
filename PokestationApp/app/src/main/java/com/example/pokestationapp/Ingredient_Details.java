package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Models.Days;
import com.example.pokestationapp.Models.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Ingredient_Details extends AppCompatActivity {
    EditText editname;
    EditText edittype;
    EditText editstock;
    EditText editneeded;

    private int ingredient_id;
    private Days order_day;
    private String ingredient_name;
    private String ingredient_type;
    private int stock;
    private int amount_needed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        Intent intent = getIntent();
        ingredient_id = intent.getIntExtra("ingredient_id", 0);
        //order_day = Days.valueOf(intent.getStringExtra("order_day"));
        ingredient_name = intent.getStringExtra("ingredient_name");
        ingredient_type = intent.getStringExtra("ingredient_type");
        stock = intent.getIntExtra("stock", 0);
        amount_needed = intent.getIntExtra("amount_needed", 0);

        editname = findViewById(R.id.ingredientname_edit);
        edittype = findViewById(R.id.ingredienttype_edit);
        editstock = findViewById(R.id.ingredientstock_edit);
        editneeded = findViewById(R.id.ingredientneeded_edit);

        editname.setText(ingredient_name);
        edittype.setText(ingredient_type);
        editstock.setText(stock+"");
        editneeded.setText(amount_needed+"");

        //Log.e("test", ingredient_id + " | " + ingredient_name + " | " + ingredient_type);
    }

    public void updateIngredient(View view)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("ingredient_id", ingredient_id+"");
        params.put("order_day", Days.MONDAY.toString());
        params.put("ingredient_name", editname.getText().toString());
        params.put("ingredient_type", edittype.getText().toString());
        params.put("stock", editstock.getText().toString());
        params.put("amount_needed", editneeded.getText().toString());


        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_UPDATE_INGREDIENT, params, 1025);
        while (!request.getResult().isDone())
        {
            try
            {
                Log.e("test", JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode()).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(this, Ingredients_Display.class);
            startActivity(intent);
        }
    }

    public void deleteIngredient(View view)
    {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_DELETE_INGREDIENT + ingredient_id, null, 1024);
        while (!request.getResult().isDone())
        {
            Intent intent = new Intent(this, Ingredients_Display.class);
            startActivity(intent);
        }
    }
}