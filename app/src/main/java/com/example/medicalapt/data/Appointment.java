package com.example.medicalapt.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "appointment_id")
    private int appointmentId;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "patient_name")
    private String patientName;

    @ColumnInfo(name = "appointment_date")
    private String appointmentDate;

    @ColumnInfo(name = "appointment_time")
    private String appointmentTime;

    @ColumnInfo(name = "notes")
    private String notes;

    public Appointment(String username, String patientName, String appointmentDate, String appointmentTime, String notes) {
        this.username = username;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.notes = notes;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}