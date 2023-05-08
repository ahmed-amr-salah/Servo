package com.example.servo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText Name,PhoneNumber,SID,Email,Password,ConfirmPassword;
    Button SignUp;String email;String password;String name;String phone_number;String ID;String Confirm;String UserID;
    DocumentReference documentReference1;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        //Declarations
        SignUp = findViewById(R.id.button); Name =findViewById(R.id.Name);PhoneNumber = findViewById(R.id.Phone);
        SID = findViewById(R.id.SID);Email = findViewById(R.id.UserName);Password = findViewById(R.id.Password);
        ConfirmPassword = findViewById(R.id.ConfirmPass);
        //SignUp.setBackgroundColor(getResources().getColor(R.color.buttons));


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email.getText().toString().trim();
                password = Password.getText().toString().trim();
                name = Name.getText().toString().trim();
                phone_number= PhoneNumber.getText().toString().trim();
                ID = SID.getText().toString().trim();
                Confirm = ConfirmPassword.getText().toString().trim();

                if(!password.equals(Confirm)){
                    Toast.makeText(Register.this, "Password is not the same!", Toast.LENGTH_SHORT).show();

                }else {
                    // Check if name or password are not entered
                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(phone_number)|| TextUtils.isEmpty(name)||TextUtils.isEmpty(ID)) {
                       Toast.makeText(Register.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                    }
                    // Check if password is too short!
                    else if (password.length() < 6) {
                        Toast.makeText(Register.this, "Password too short", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser(email,password);
                        db = FirebaseFirestore.getInstance();
                    }
                }



            }
        });


    }

    private void registerUser(String txt_email, String txt_password) {
        mAuth.createUserWithEmailAndPassword(txt_email, txt_password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registering user successful!", Toast.LENGTH_SHORT).show();
                            createUser();
                            startActivity(new Intent(Register.this,login.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createUser() {
        // Check that db and mAuth are initialized
        if (db == null || mAuth == null) {
            System.out.println("db or mAuth is null");
            return;
        }

        // Check that name, phone_number, and ID are initialized
        if (name == null || phone_number == null || ID == null) {
            System.out.println("name, phone_number, or ID is null");
            return;
        }

        // Create a map with the user data
        Map<String, Object> user = new HashMap<>();
        user.put("Name", name);
        user.put("Phone Number", phone_number);
        user.put("SID", ID);
        user.put("Customer", "Yes");

        // Get the current user's ID
        String userID = mAuth.getCurrentUser().getUid();

        // Get a reference to the user document
        DocumentReference userRef = db.collection("Users").document(userID);

        // Set the user data
        userRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
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