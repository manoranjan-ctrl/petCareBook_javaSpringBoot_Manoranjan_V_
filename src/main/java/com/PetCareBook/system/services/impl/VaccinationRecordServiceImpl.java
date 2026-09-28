package com.PetCareBook.system.services.impl;

import com.PetCareBook.system.model.VaccinationRecord;
import com.PetCareBook.system.repository.VaccinationRecordRepository;
import com.PetCareBook.system.services.VaccinationRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinationRecordServiceImpl
        implements VaccinationRecordService {

    @Autowired
    VaccinationRecordRepository vaccinationRecordRepository;

    @Override
    public VaccinationRecord addVaccinationRecord(
            VaccinationRecord record) {

        validateRecord(record);

        int interval =
                record.getVaccineType().getIntervalDays();

        LocalDate nextDueDate =
                record.getDate().plusDays(interval);

        record.setNextDueDate(nextDueDate);

        return vaccinationRecordRepository.save(record);
    }

    @Override
    public List<VaccinationRecord> getAllVaccinationRecords() {
        return vaccinationRecordRepository.findAll();
    }

    @Override
    public VaccinationRecord getVaccinationRecordById(Long id) {
        return vaccinationRecordRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<VaccinationRecord> getUpcomingVaccinations() {

        LocalDate today = LocalDate.now();
        LocalDate next7Days = today.plusDays(7);

        return vaccinationRecordRepository
                .findByNextDueDateBetween(today, next7Days);
    }

    @Override
    public VaccinationRecord updateVaccinationRecord(
            Long id,
            VaccinationRecord record) {

        VaccinationRecord existingRecord =
                vaccinationRecordRepository
                        .findById(id)
                        .orElse(null);

        if (existingRecord != null) {

            validateRecord(record);

            existingRecord.setDate(record.getDate());
            existingRecord.setPet(record.getPet());
            existingRecord.setVaccineType(record.getVaccineType());

            int interval =
                    record.getVaccineType().getIntervalDays();

            LocalDate nextDueDate =
                    record.getDate().plusDays(interval);

            existingRecord.setNextDueDate(nextDueDate);

            return vaccinationRecordRepository.save(existingRecord);
        }

        return null;
    }

    @Override
    public void deleteVaccinationRecord(Long id) {
        vaccinationRecordRepository.deleteById(id);
    }

    private void validateRecord(VaccinationRecord record) {

        if (record.getDate() == null) {
            throw new RuntimeException(
                    "Vaccination date cannot be empty");
        }

        if (record.getDate().isAfter(LocalDate.now())) {
            throw new RuntimeException(
                    "Vaccination date cannot be in the future");
        }

        if (record.getPet() == null) {
            throw new RuntimeException(
                    "Pet is required");
        }

        if (record.getVaccineType() == null) {
            throw new RuntimeException(
                    "Vaccine type is required");
        }
    }
}