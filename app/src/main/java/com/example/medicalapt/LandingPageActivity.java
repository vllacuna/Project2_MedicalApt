package com.example.medicalapt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapt.util.SessionManager;

public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!SessionSharedPref.isLoggedIn(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_landing_page);

        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        TextView accessLevelTextView = findViewById(R.id.accessLevelTextView);
        Button adminAreaButton = findViewById(R.id.adminAreaButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        String username = SessionSharedPref.getUsername(this);
        boolean isAdmin = SessionSharedPref.isAdmin(this);

        welcomeTextView.setText(getString(R.string.logged_in_as, username));
        accessLevelTextView.setText(isAdmin ? R.string.admin_user : R.string.standard_user);
        adminAreaButton.setVisibility(isAdmin ? View.VISIBLE : View.INVISIBLE);

        adminAreaButton.setOnClickListener(view ->
                Toast.makeText(this, R.string.admin_feature_message, Toast.LENGTH_SHORT).show());

        logoutButton.setOnClickListener(view -> {
            SessionSharedPref.logOut(this);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
