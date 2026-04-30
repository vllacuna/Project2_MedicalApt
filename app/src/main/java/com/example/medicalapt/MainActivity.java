package com.example.medicalapt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase.getDatabase(getApplicationContext()).seedDefaultUsers();

        SharedPreferences preferences = getSharedPreferences(SessionManager.PREF_NAME, MODE_PRIVATE);
        if (preferences.getBoolean(SessionManager.KEY_IS_LOGGED_IN, false)) {
            startActivity(new Intent(this, LandingPageActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        Button createButton = findViewById(R.id.createButton);

        loginButton.setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));
        createButton.setOnClickListener(view -> startActivity(new Intent(this, CreateAccountActivity.class)));
    }
}