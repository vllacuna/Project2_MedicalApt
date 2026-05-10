package com.example.medicalapt.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "patients")
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private int patientId;
    @ColumnInfo (name= "patient_name")
    private String patientName;
    @ColumnInfo (name= "dob")
    private String dob;
    @ColumnInfo (name= "sex")
    private String sex;
    @ColumnInfo (name= "weight")
    private String weight;
    @ColumnInfo (name= "height")
    private String height;

    public Patient(String patientName, String dob, String sex, String weight, String height) {
        this.patientName = patientName;
        this.dob = dob;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
