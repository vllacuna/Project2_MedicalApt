package com.example.medicalapt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapt.data.AppDatabase;
import com.example.medicalapt.data.Appointment;
import com.example.medicalapt.util.SessionSharedPref;

public class CreateAppointmentActivity extends AppCompatActivity {
    private EditText patientNameEditText;
    private EditText appointmentDateEditText;
    private EditText appointmentTimeEditText;
    private EditText notesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!SessionSharedPref.isLoggedIn(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_create_appointment);

        patientNameEditText = findViewById(R.id.appointmentPatientNameEditText);
        appointmentDateEditText = findViewById(R.id.appointmentDateEditText);
        appointmentTimeEditText = findViewById(R.id.appointmentTimeEditText);
        notesEditText = findViewById(R.id.appointmentNotesEditText);
        Button saveAppointmentButton = findViewById(R.id.saveAppointmentButton);
        Button cancelAppointmentButton = findViewById(R.id.cancelAppointmentButton);

        saveAppointmentButton.setOnClickListener(view -> saveAppointment());
        cancelAppointmentButton.setOnClickListener(view -> finish());
    }

    private void saveAppointment() {
        String patientName = patientNameEditText.getText().toString().trim();
        String appointmentDate = appointmentDateEditText.getText().toString().trim();
        String appointmentTime = appointmentTimeEditText.getText().toString().trim();
        String notes = notesEditText.getText().toString().trim();

        if (TextUtils.isEmpty(patientName) || TextUtils.isEmpty(appointmentDate) || TextUtils.isEmpty(appointmentTime)) {
            Toast.makeText(this, "Enter a patient name, date and time.", Toast.LENGTH_SHORT).show();
            return;
        }

        Appointment appointment = new Appointment(
                SessionSharedPref.getUsername(this),
                patientName,
                appointmentDate,
                appointmentTime,
                notes
        );
        AppDatabase.getDatabase(getApplicationContext()).appointmentDao().insert(appointment);
        Toast.makeText(this, "Appointment saved.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
