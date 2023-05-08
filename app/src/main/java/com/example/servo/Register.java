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
    Button SignUp;String Confirm;
    FirebaseFirestore db;
    User user = new User();
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
                user.setEmail(Email.getText().toString().trim());
                user.setPassword(Password.getText().toString().trim());
                user.setName(Name.getText().toString().trim());
                user.setPhoneNumber(PhoneNumber.getText().toString().trim());
                user.setUID(SID.getText().toString().trim());
                Confirm = ConfirmPassword.getText().toString().trim();

                if(!user.getPassword().equals(Confirm)){
                    Toast.makeText(Register.this, "Password is not the same!", Toast.LENGTH_SHORT).show();

                }else {
                    // Check if name or user.getpassword() are not entered
                    if (TextUtils.isEmpty(user.getEmail()) || TextUtils.isEmpty(user.getPassword())|| TextUtils.isEmpty(user.getPhoneNumber())|| TextUtils.isEmpty(user.getName())||TextUtils.isEmpty(user.getUID())) {
                       Toast.makeText(Register.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                    }
                    // Check if password is too short!
                    else if (user.getPassword().length() < 6) {
                        Toast.makeText(Register.this, "Password too short", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser(user.getEmail(),user.getPassword());
                        //db = FirebaseFirestore.getInstance();
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
                            user.storeUser();
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


}