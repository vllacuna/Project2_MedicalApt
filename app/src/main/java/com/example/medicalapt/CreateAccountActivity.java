package com.example.medicalapt;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapt.data.AppDatabase;
import com.example.medicalapt.data.User;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText newUsernameEditText;
    private EditText newPasswordEditText;
    private CheckBox adminCheckBox;
    private TextView createAccountMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        newUsernameEditText = findViewById(R.id.newUsernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        adminCheckBox = findViewById(R.id.adminCheckBox);
        createAccountMessageTextView = findViewById(R.id.createAccountMessageTextView);
        Button saveAccountButton = findViewById(R.id.saveAccountButton);
        Button cancelCreateAccountButton = findViewById(R.id.cancelCreateAccountButton);

        saveAccountButton.setOnClickListener(view -> createAccount());
        cancelCreateAccountButton.setOnClickListener(view -> finish());
    }

    private void createAccount() {
        String username = newUsernameEditText.getText().toString().trim();
        String password = newPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            createAccountMessageTextView.setText(R.string.enter_username_password);
            return;
        }

        AppDatabase database = AppDatabase.getDatabase(getApplicationContext());
        if (database.userDao().getUserByUsername(username) != null) {
            createAccountMessageTextView.setText(R.string.that_username_already_exists);
            return;
        }

        User newUser = new User(username, password, adminCheckBox.isChecked());
        database.userDao().insert(newUser);
        Toast.makeText(this, "Account created successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
