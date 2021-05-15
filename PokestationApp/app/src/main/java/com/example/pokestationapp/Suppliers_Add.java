package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;

import org.json.JSONException;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Suppliers_Add extends AppCompatActivity {

    EditText supplier_name;
    Spinner type_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_add);

        supplier_name = findViewById(R.id.addSuppliers_name);

        type_spinner = findViewById(R.id.addIngredient_spinnerType);
        ArrayAdapter<CharSequence> type_adapter = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(type_adapter);
    }

    public void createSupplier(View view)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("supplier_name", supplier_name.getText().toString());

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_INGREDIENT, params, 1025);
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
            };

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}