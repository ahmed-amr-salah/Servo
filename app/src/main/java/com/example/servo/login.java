package com.example.servo;

import com.example.servo.User;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login extends AppCompatActivity {

    EditText UserName,Password;
    TextView toSignUp;
    Button Login;
    FirebaseAuth auth;
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        UserName = findViewById(R.id.editTextTextPersonName);
        Password = findViewById(R.id.editTextTextPassword);
        toSignUp = findViewById(R.id.textView3);
        Login = findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getEmail().isEmpty() || user.getPassword().isEmpty())
                {
                    Toast.makeText(login.this, " Enter Your Credentials ",Toast.LENGTH_SHORT).show();
                }
                else {
                    user.setEmail(UserName.getText().toString().trim());
                    user.setPassword(Password.getText().toString().trim());
                    loginUser(user.getEmail(), user.getPassword());
                }
            }
        });


        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, SignUpOptions.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }


    private void loginUser(String txt_email, String txt_password) {

        auth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this, Profile.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    // To prevent coming to this activity when the user presses back!
                    finish();
                }else{
                    Toast.makeText(login.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}