package com.example.pokestationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompaniesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);
    }

    public void addCompany(View view) {
        Intent intent = new Intent(this, Suppliers_Add.class);
        startActivity(intent);
    }

    public void companyDisplay(View view) {
        Intent intent = new Intent(this, Suppliers_Display.class);
        startActivity(intent);
    }
}