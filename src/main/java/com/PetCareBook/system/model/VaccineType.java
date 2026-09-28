package com.PetCareBook.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VaccineType {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vaccineId;

    private String vaccineName;
    private int intervalDays;

    public int getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        this.intervalDays = intervalDays;
    }

    public long getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(long vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
}
