package com.example.medicalapt.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao {
    @Insert
    void insert(Patient patient);

    @Query("SELECT * FROM patients")
    List<Patient> getAllPatients();

}
