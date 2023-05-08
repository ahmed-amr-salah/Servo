package com.example.servo;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class User {


        private String email;
        private String password;
        private String name;
        private String UID;
        private String phoneNumber;

        // Constructor
        public User(String email, String password, String name, String UID, String phoneNumber) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.UID = UID;
            this.phoneNumber = phoneNumber;
        }
        public User() {
        }
        // Setters
        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUID(String UID) {
            this.UID = UID;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        // Getters
        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public String getUID() {
            return UID;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        //store_User_to_the_database
        public void storeUser(){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseFirestore db = FirebaseFirestore.getInstance();;
            if (mAuth == null) {
                System.out.println("db or mAuth is null");

            }

            // Check that name, phone_number, and ID are initialized
            if (this.getName() == null || this.getPhoneNumber()== null || this.getUID() == null) {
                System.out.println("name, phone_number, or ID is null");
            }
            // Create a map with the user data
            Map<String, Object> newUser = new HashMap<>();
            newUser.put("Name", this.getName());
            newUser.put("Phone Number", this.getPhoneNumber());
            newUser.put("SID", this.getUID());
            newUser.put("Customer", "Yes");
            // Get the current user's ID
            String userID = mAuth.getCurrentUser().getUid();
            // Get a reference to the user document
            DocumentReference userRef = db.collection("Users").document(userID);

            // Set the user data
            userRef.set(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    System.out.println("User is stored successfully");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            });

        }

  }
