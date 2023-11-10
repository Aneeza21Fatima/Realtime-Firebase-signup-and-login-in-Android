package com.example.signuprealtimelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    EditText signup_name, signup_username , signup_password, signup_email;
    TextView loginRedirected;
    Button signup_button;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_email=findViewById(R.id.signup_email);
        signup_button=findViewById(R.id.sign_up_button);
        signup_name=findViewById(R.id.signup_name);
        signup_username=findViewById(R.id.signup_username);
        signup_password=findViewById(R.id.signup_password);
        loginRedirected=findViewById(R.id.loginRedirectedText);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference= database.getReference("users");

                String name = signup_name.getText().toString();
                String email = signup_email.getText().toString();
                String password = signup_password.getText().toString();
                String username = signup_username.getText().toString();

                HelperClass helperClass = new HelperClass(name, username, password, email);
                reference.child(name).setValue(helperClass);

                Toast.makeText(signup.this, "You have Signed up Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signup.this , login.class);
                startActivity(intent);
            }
        });

        loginRedirected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this , login.class);
                startActivity(intent);
            }
        });
    }
}