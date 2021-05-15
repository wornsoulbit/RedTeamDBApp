package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Models.Days;

import org.json.JSONException;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Ingredient_Add extends AppCompatActivity {
    EditText i_name;
    EditText i_stock;
    EditText i_needed;
    Spinner type_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_add);

        i_name = findViewById(R.id.addIngredient_editName);
        i_stock = findViewById(R.id.addIngredient_editstock);
        i_needed = findViewById(R.id.addIngredient_editneeded);

        type_spinner = findViewById(R.id.addIngredient_spinnerType);
        ArrayAdapter<CharSequence> type_adapter = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(type_adapter);
    }

    public void createIngredient(View view) {
        String ingredientName = i_name.getText().toString();
        String stock = i_stock.getText().toString();
        String needed = i_needed.getText().toString();

        if (ingredientName.matches("")) {
            Toast.makeText(Ingredient_Add.this, "Please enter an Ingredient Name", Toast.LENGTH_LONG).show();
        } else if (stock.matches("")) {
            Toast.makeText(Ingredient_Add.this, "Please enter an Ingredient Stock", Toast.LENGTH_LONG).show();
        } else if (needed.matches("")) {
            Toast.makeText(Ingredient_Add.this, "Please enter the amount needed", Toast.LENGTH_LONG).show();
        } else {
            HashMap<String, String> params = new HashMap<>();
            params.put("order_day", Days.MONDAY.toString());
            params.put("ingredient_name", ingredientName);
            params.put("ingredient_type", type_spinner.getSelectedItem().toString());
            params.put("stock", stock);
            params.put("amount_needed", needed);

            PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_INGREDIENT, params, 1025);
            while (!request.getResult().isDone()) {
                try {
                    Log.e("test", JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode()).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

}