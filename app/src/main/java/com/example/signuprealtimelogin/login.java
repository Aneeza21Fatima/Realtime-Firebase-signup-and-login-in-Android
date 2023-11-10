package com.example.signuprealtimelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {
    EditText login_username, login_password;

    TextView signupRedirected;

    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);

        signupRedirected = findViewById(R.id.loginRedirectedText);
        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ValidateUsername() | !ValidatePassword()){

                }
                else{
                    CheckUser();
                }
            }
        });

        signupRedirected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });

    }

    public boolean ValidateUsername() {
        String val = login_username.getText().toString();
        if (val.isEmpty()) {
            login_username.setError("User Name cannot be Empty");
            return false;
        } else {
            login_username.setError(null); // Clear error
            return true;
        }
    }

    public void CheckUser() {
        final String username = login_username.getText().toString().trim();
        final String password = login_password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    DataSnapshot userSnapshot = snapshot.getChildren().iterator().next(); // Get the user node

                    String passwordFromDb = userSnapshot.child("password").getValue(String.class);

                    if (passwordFromDb != null && passwordFromDb.equals(password)) {
                        // Login successful
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        login_password.setError("Invalid Password");
                    }
                } else {
                    login_username.setError("Invalid Username");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error in Firebase Database: " + error.getMessage());
                // Handle any database error if needed
            }
        });
    }

    public boolean ValidatePassword() {
        String val2 = login_password.getText().toString();
        if (val2.isEmpty()) {
            login_password.setError("Password Name cannot be Empty");
            return false;
        } else {
            login_password.setError(null); // Clear error
            return true;
        }
    }


}
