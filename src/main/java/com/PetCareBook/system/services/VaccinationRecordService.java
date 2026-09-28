package com.PetCareBook.system.services;

import com.PetCareBook.system.model.VaccinationRecord;

import java.util.List;

public interface VaccinationRecordService {

    VaccinationRecord addVaccinationRecord(
            VaccinationRecord record);

    List<VaccinationRecord> getAllVaccinationRecords();

    VaccinationRecord getVaccinationRecordById(Long id);

    List<VaccinationRecord> getUpcomingVaccinations();

    VaccinationRecord updateVaccinationRecord(
            Long id,
            VaccinationRecord record);

    void deleteVaccinationRecord(Long id);
}