package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Orders_Main extends AppCompatActivity {
    Button monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_main);
        monday = findViewById(R.id.monday_button);
        tuesday = findViewById(R.id.tuesday_button);
        wednesday = findViewById(R.id.wednesday_button);
        thursday = findViewById(R.id.thursday_button);
        friday = findViewById(R.id.friday_button);
        saturday = findViewById(R.id.saturday_button);
        sunday = findViewById(R.id.sunday_button);

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orders_Main.this, AddOrderActivity.class);
                intent.putExtra("day", "MONDAY");
                startActivity(intent);
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orders_Main.this, AddOrderActivity.class);
                intent.putExtra("day", "TUESDAY");
                startActivity(intent);
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orders_Main.this, AddOrderActivity.class);
                intent.putExtra("day", "WEDNESDAY");
                startActivity(intent);
            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orders_Main.this, AddOrderActivity.class);
                intent.putExtra("day", "THURSDAY");
                startActivity(intent);
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orders_Main.this, AddOrderActivity.class);
                intent.putExtra("day", "FRIDAY");
                startActivity(intent);
            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orders_Main.this, AddOrderActivity.class);
                intent.putExtra("day", "SATURDAY");
                startActivity(intent);
            }
        });
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orders_Main.this, AddOrderActivity.class);
                intent.putExtra("day", "SUNDAY");
                startActivity(intent);
            }
        });
    }

}
