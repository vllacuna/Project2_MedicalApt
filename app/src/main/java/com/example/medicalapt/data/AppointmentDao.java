package com.example.medicalapt.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentDao {
    @Insert
    void insert(Appointment appointment);

    @Query("SELECT * FROM appointments WHERE username = :username ORDER BY appointment_date, appointment_time")
    List<Appointment> getAppointmentsForUser(String username);

    @Query("SELECT * FROM appointments ORDER BY appointment_date, appointment_time")
    List<Appointment> getAllAppointments();

    @Query("DELETE FROM appointments WHERE appointment_id = :appointmentId AND username = :username")
    int deleteAppointmentForUser(int appointmentId, String username);
}
