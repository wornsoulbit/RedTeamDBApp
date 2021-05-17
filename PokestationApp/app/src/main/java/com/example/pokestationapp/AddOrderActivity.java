package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddOrderActivity extends AppCompatActivity {
    TextView dayText, company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        dayText = findViewById(R.id.textView2);

        if(getIntent().getStringExtra("order_day") != null){
            String day = getIntent().getStringExtra("order_day");
            dayText.setText(day);
        }

        company =  findViewById(R.id.textView3);
        if(getIntent().getStringExtra("supplier_name") != null) {
            company.setText(getIntent().getStringExtra("supplier_name"));
        }

    }

    public void chooseCompany(View view) {
        Intent intent = new Intent(this, Suppliers_Display.class);
        startActivity(intent);
    }
}