package com.PetCareBook.system.services;

import com.PetCareBook.system.model.VaccinationRecord;

import java.util.List;

public interface VaccinationRecordService {

    VaccinationRecord addVaccinationRecord(VaccinationRecord vaccinationRecord);
    List<VaccinationRecord> getAllVaccinationRecords();
    List<VaccinationRecord> getUpcomingVaccinations();
    VaccinationRecord getVaccinationRecordById(Long id);
    VaccinationRecord updateVaccinationRecord(
            Long id,
            VaccinationRecord record);

    void deleteVaccinationRecord(Long id);
}


