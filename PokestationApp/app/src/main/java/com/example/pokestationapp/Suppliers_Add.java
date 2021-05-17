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

import org.json.JSONException;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Suppliers_Add extends AppCompatActivity {

    EditText supplier_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_add);

        supplier_name = findViewById(R.id.addSuppliers_name);
    }

    public void createSupplier(View view) {

        if (supplier_name.getText().toString().matches("")) {
            Toast.makeText(Suppliers_Add.this, "Please enter the company Name", Toast.LENGTH_LONG).show();
        } else {
            HashMap<String, String> params = new HashMap<>();
            params.put("supplier_name", supplier_name.getText().toString());

            PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_SUPPLIER, params, 1025);
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
                Toast.makeText(Suppliers_Add.this, "Supplier Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Orders_Main.class);
                startActivity(intent);
            }
        }
    }

    public void goBack6(View view)
    {
        Intent intent = getIntent();

        Intent previous = new Intent(this, Suppliers_Display.class);
        previous.putExtra("order_day", intent.getStringExtra("order_day"));
        startActivity(previous);
    }
}