package com.example.servo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String rec = intent.getStringExtra("details");

        TextView orderDetails = (TextView)findViewById(R.id.order_details);
        orderDetails.setText(rec);
    }
}