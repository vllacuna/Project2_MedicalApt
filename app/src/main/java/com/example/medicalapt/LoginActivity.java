package com.example.medicalapt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapt.data.AppDatabase;
import com.example.medicalapt.data.User;
import com.example.medicalapt.util.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        errorTextView = findViewById(R.id.errorTextView);
        Button loginSubmitButton = findViewById(R.id.loginSubmitButton);
        Button backButton = findViewById(R.id.backButton);

        loginSubmitButton.setOnClickListener(view -> attemptLogin());
        backButton.setOnClickListener(view -> finish());
    }

    private void attemptLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            errorTextView.setText(R.string.enter_username_password);
            return;
        }

        User user = AppDatabase.getDatabase(getApplicationContext()).userDao().getUserByUsername(username);

        if (user == null || !password.equals(user.getPassword())) {
            errorTextView.setText(R.string.invalid_credentials);
            return;
        }

        SessionManager.logIn(this, user);
        Toast.makeText(this, getString(R.string.welcome_user, user.getUsername()), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LandingPageActivity.class));
        finish();
    }
}
