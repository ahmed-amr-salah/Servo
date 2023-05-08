package com.example.servo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    private RecyclerView restaurantsRecyclerView;
    private RestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d("Ahmed","Ahmed is here");
        //Toast.makeText(Profile.this, "I am here in profile ", Toast.LENGTH_SHORT).show();
        // Initialize the RecyclerView
        restaurantsRecyclerView = findViewById(R.id.restaurantsRecyclerView);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the restaurant list
        restaurantList = new ArrayList<>();
        // Add restaurants to the list
        restaurantList.add(new Restaurant(R.drawable.relish, "Relish"));
        restaurantList.add(new Restaurant(R.drawable.tabali , "Tabali"));
        restaurantList.add(new Restaurant(R.drawable.cilantro, "Cilantro"));




        // Initialize the RestaurantAdapter
        restaurantAdapter = new RestaurantAdapter(restaurantList);

        // Set the adapter on the RecyclerView
        restaurantsRecyclerView.setAdapter(restaurantAdapter);

        restaurantAdapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Restaurant restaurant) {
                // Handle the item click event
                // For example, you can open a new activity or show a dialog
                startActivity(new Intent(Profile.this, Menu.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }
}
