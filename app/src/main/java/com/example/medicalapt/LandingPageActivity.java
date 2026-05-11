package com.example.medicalapt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapt.data.AppDatabase;
import com.example.medicalapt.data.Appointment;
import com.example.medicalapt.util.SessionSharedPref;

import java.util.List;

public class LandingPageActivity extends AppCompatActivity {

    private TextView appointmentsTextView;
    private EditText deleteAppointmentIdEditText;

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
        Button createAppointmentButton = findViewById(R.id.createAppointmentButton);
        Button adminAreaButton = findViewById(R.id.adminAreaButton);
        Button deleteAppointmentButton = findViewById(R.id.deleteAppointmentButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        appointmentsTextView = findViewById(R.id.appointmentsTextView);
        deleteAppointmentIdEditText = findViewById(R.id.deleteAppointmentIdEditText);

        String username = SessionSharedPref.getUsername(this);
        boolean isAdmin = SessionSharedPref.isAdmin(this);

        welcomeTextView.setText(getString(R.string.logged_in_as, username));
        accessLevelTextView.setText(isAdmin ? R.string.admin_user : R.string.standard_user);
        adminAreaButton.setVisibility(isAdmin ? View.VISIBLE : View.INVISIBLE);

        createAppointmentButton.setOnClickListener(view -> startActivity(new Intent(this, CreateAppointmentActivity.class)));
        adminAreaButton.setOnClickListener(view -> {
                    Intent intent = new Intent(this, AdminActivity.class);
                    startActivity(intent);
                });
        deleteAppointmentButton.setOnClickListener(view -> deleteAppointment());
        logoutButton.setOnClickListener(view -> {
            SessionSharedPref.logOut(this);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showAppointments();
    }

    private void showAppointments() {
        String username = SessionSharedPref.getUsername(this);
        List<Appointment> appointments = AppDatabase.getDatabase(getApplicationContext()).appointmentDao().getAppointmentsForUser(username);

        if (appointments.isEmpty()) {
            appointmentsTextView.setText(R.string.no_appointments_yet);
            return;
        }

        StringBuilder builder = new StringBuilder(getString(R.string.your_appointments));
        for (Appointment appointment : appointments) {
            builder.append("\n\nID: ")
                    .append(appointment.getAppointmentId())
                    .append("\n")
                    .append(appointment.getPatientName())
                    .append("\n")
                    .append(appointment.getAppointmentDate())
                    .append(" at ")
                    .append(appointment.getAppointmentTime());
            if (appointment.getNotes() != null && !appointment.getNotes().trim().isEmpty()) {
                builder.append("\n").append(appointment.getNotes());
            }
        }
        appointmentsTextView.setText(builder.toString());
    }

    private void deleteAppointment() {
        String appointmentIdText = deleteAppointmentIdEditText.getText().toString().trim();

        if (TextUtils.isEmpty(appointmentIdText)) {
            Toast.makeText(this, "Enter an appointment ID to delete.", Toast.LENGTH_SHORT).show();
            return;
        }

        int appointmentId;
        try {
            appointmentId = Integer.parseInt(appointmentIdText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Appointment ID must be a number.", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = SessionSharedPref.getUsername(this);
        int deletedRows = AppDatabase.getDatabase(getApplicationContext()).appointmentDao().deleteAppointmentForUser(appointmentId, username);

        if (deletedRows > 0) {
            Toast.makeText(this, "Appointment deleted.", Toast.LENGTH_SHORT).show();
            deleteAppointmentIdEditText.setText("");
            showAppointments();
        } else {
            Toast.makeText(this, "Appointment not found.", Toast.LENGTH_SHORT).show();
        }
    }
}
