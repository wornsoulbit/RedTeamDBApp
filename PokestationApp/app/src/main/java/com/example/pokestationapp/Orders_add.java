package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Models.Days;
import com.example.pokestationapp.Models.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Orders_add extends AppCompatActivity {
    ArrayList<Ingredient> ingredients = new ArrayList<>();

    EditText addOrder_Iname;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_add);
        addOrder_Iname = findViewById(R.id.createOrder_editName);
        intent = getIntent();

        readIngredients();
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
                            Days.valueOf(obj.getString("order_day").toUpperCase()),
                            obj.getString("ingredient_name"),
                            obj.getString("ingredient_type"),
                            obj.getInt("stock"),
                            obj.getInt("amount_needed")
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

    public void addOrder(View view)
    {
        String search = addOrder_Iname.getText().toString();
        for(int i = 0; i < ingredients.size(); i++)
        {
            if(ingredients.get(i).getIngredient_name().toUpperCase().equals(search.toUpperCase()))
            {
                HashMap<String, String> params = new HashMap<>();
                params.put("supplier_id", intent.getIntExtra("supplier_id",0)+"");
                params.put("ingredient_id", ingredients.get(i).getIngredient_id()+"");
                params.put("stock_id", "1");
                params.put("order_day", intent.getStringExtra("order_day"));

                PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_ORDER, params, 1025);
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
                    Toast.makeText(this, "Order Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void goBack7(View view)
    {
        Intent intent = getIntent();

        Intent previous = new Intent(this, Orders_Display.class);
        previous.putExtra("supplier_name", intent.getStringExtra("supplier_name"));
        previous.putExtra("supplier_id", intent.getIntExtra("supplier_id", 0));
        previous.putExtra("order_day", intent.getStringExtra("order_day"));
        startActivity(previous);
    }
}