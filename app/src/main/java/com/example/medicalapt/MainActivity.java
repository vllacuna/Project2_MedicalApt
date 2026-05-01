package com.example.medicalapt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medicalapt.data.AppDatabase;
import com.example.medicalapt.util.SessionSharedPref;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase.getDatabase(getApplicationContext()).seedDefaultUsers();

        SharedPreferences preferences = getSharedPreferences(SessionSharedPref.PREF_NAME, MODE_PRIVATE);
        if (preferences.getBoolean(SessionSharedPref.KEY_IS_LOGGED_IN, false)) {
            startActivity(new Intent(this, LandingPageActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        Button createButton = findViewById(R.id.createButton);

        loginButton.setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));
        createButton.setOnClickListener(view ->
                Toast.makeText(this, "Create Account not implemented yet", Toast.LENGTH_SHORT).show()
        );
    }
}