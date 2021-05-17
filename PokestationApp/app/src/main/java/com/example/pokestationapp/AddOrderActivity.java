package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddOrderActivity extends AppCompatActivity {
    TextView dayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        dayText = findViewById(R.id.textView2);

        if(getIntent().getStringExtra("day") != null){
            String day = getIntent().getStringExtra("day");
            dayText.setText(day);
        }


    }

    public void chooseDate(View view) {
        Intent intent = new Intent(this, Orders_Main.class);
        startActivity(intent);
    }
}