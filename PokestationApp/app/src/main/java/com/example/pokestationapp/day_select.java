package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class day_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_select);
    }

    public void monday(View view) {
        Intent intent = new Intent(this, AddOrderActivity.class);
        intent.putExtra("order_day", "MONDAY");
        startActivity(intent);
    }

    public void wednesday(View view) {
        Intent intent = new Intent(this, AddOrderActivity.class);
        intent.putExtra("order_day", "WEDNESDAY");
        startActivity(intent);
    }

    public void friday(View view) {
        Intent intent = new Intent(this, AddOrderActivity.class);
        intent.putExtra("order_day", "FRIDAY");
        startActivity(intent);
    }

    public void thursday(View view) {
        Intent intent = new Intent(this, AddOrderActivity.class);
        intent.putExtra("order_day", "THURSDAY");
        startActivity(intent);
    }

    public void sunday(View view) {
        Intent intent = new Intent(this, AddOrderActivity.class);
        intent.putExtra("order_day", "SUNDAY");
        startActivity(intent);
    }

    public void saturday(View view) {
        Intent intent = new Intent(this, AddOrderActivity.class);
        intent.putExtra("order_day", "SATURDAY");
        startActivity(intent);
    }

    public void tuesday(View view) {
        Intent intent = new Intent(this, AddOrderActivity.class);
        intent.putExtra("order_day", "TUESDAY");
        startActivity(intent);
    }
}