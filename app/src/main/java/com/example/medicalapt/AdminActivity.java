package com.example.medicalapt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.medicalapt.data.AppDatabase;
import com.example.medicalapt.data.Patient;
import com.example.medicalapt.util.SessionSharedPref;

public class AdminActivity extends AppCompatActivity {
    private EditText patientInputEditText;
    private EditText dOBInputEditText;
    private EditText sexInputEditText;
    private EditText weightInputEditText;
    private EditText heightInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!SessionSharedPref.isLoggedIn(this)||!SessionSharedPref.isAdmin(this)){
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_admin);

        patientInputEditText = findViewById(R.id.patientInputEditText);
        dOBInputEditText = findViewById(R.id.dOBInputEditText);
        sexInputEditText = findViewById(R.id.sexInputEditText);
        weightInputEditText = findViewById(R.id.weightInputEditText);
        heightInputEditText = findViewById(R.id.heightInputEditText);
        Button saveButton = findViewById(R.id.saveButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        saveButton.setOnClickListener(view -> savePatient());
        logoutButton.setOnClickListener(view -> {
            SessionSharedPref.logOut(this);
            Intent intent =new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
        });

    }

    private void savePatient() {
        String name = patientInputEditText.getText().toString().trim();
        String dob= dOBInputEditText.getText().toString().trim();
        String sex= sexInputEditText.getText().toString().trim();
        String weight= weightInputEditText.getText().toString().trim();
        String height= heightInputEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(dob)||TextUtils.isEmpty(sex) || TextUtils.isEmpty(weight)||TextUtils.isEmpty(height) ){
            Toast.makeText(this, "fill it out",Toast.LENGTH_SHORT).show();
            return;
        }
        Patient patient= new Patient(name,dob, sex, weight, height);

        AppDatabase.getDatabase(getApplicationContext()).patientDao().insert(patient);
        Toast.makeText(this, "Its saved!",Toast.LENGTH_SHORT).show();
        patientInputEditText.setText("");
        dOBInputEditText.setText("");
        sexInputEditText.setText("");
        weightInputEditText.setText("");
        heightInputEditText.setText("");

    }
}