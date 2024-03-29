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
import com.example.pokestationapp.Models.Supplier;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Suppliers_Display extends AppCompatActivity implements Suppliers_RecyclerViewAdapter.ItemClickListener {

    Suppliers_RecyclerViewAdapter adapter;
    ArrayList<Supplier> suppliers = new ArrayList<>();
    String order_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_display);

        readSuppliers();

        RecyclerView recyclerView = findViewById(R.id.suppliers_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Suppliers_RecyclerViewAdapter(this, suppliers);

        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        order_day = intent.getStringExtra("order_day");
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, Orders_Display.class);
        intent.putExtra("supplier_name", adapter.getItem(position).getSupplier_name());
        intent.putExtra("supplier_id", adapter.getItem(position).getSupplier_id());
        intent.putExtra("order_day", order_day);
        startActivity(intent);
    }

    private void readSuppliers() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_GET_SUPPLIERS, null, 1024);
        while (!request.getResult().isDone()) {
            try {
                suppliers.clear();

                JSONArray response = JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode());
                for (int i = 0; i < response.length(); i++)
                {
                    //getting each hero object
                    JSONObject obj = response.getJSONObject(i);

                    //adding the hero to the list
                    suppliers.add(new Supplier(
                            obj.getInt("supplier_id"),
                            obj.getString("supplier_name")
                    ));
                }

                //Log.e("test", suppliers.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void toAddSupplier(View view)
    {
        Intent intent = new Intent(this, Suppliers_Add.class);
        intent.putExtra("order_day", order_day);
        startActivity(intent);
    }

    public void goBack4(View view)
    {
        Intent intent = new Intent(this, Orders_Main.class);
        startActivity(intent);
    }
}