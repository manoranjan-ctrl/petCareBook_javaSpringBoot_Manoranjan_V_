package com.PetCareBook.system.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vaccinationRecordId;

    private LocalDate date;
    private LocalDate nextDueDate;
    @ManyToOne
    private Pet pet;

    @ManyToOne
    private VaccineType vaccineType;

    public long getVaccinationRecordId() {
        return vaccinationRecordId;
    }

    public void setVaccinationRecordId(long vaccinationRecordId) {
        this.vaccinationRecordId = vaccinationRecordId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

}
