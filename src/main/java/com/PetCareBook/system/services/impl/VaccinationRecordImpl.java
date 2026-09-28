package com.PetCareBook.system.services.impl;

import com.PetCareBook.system.model.VaccinationRecord;
import com.PetCareBook.system.repository.VaccinationRecordRepository;
import com.PetCareBook.system.services.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinationRecordImpl implements VaccinationRecordService {
    @Autowired
    VaccinationRecordRepository vaccinationRecordRepository;


    @Override
    public VaccinationRecord addVaccinationRecord(VaccinationRecord record) {

        int interval = record.getVaccineType().getIntervalDays();

        LocalDate nextDueDate = record.getDate().plusDays(interval);

        record.setNextDueDate(nextDueDate);

        return vaccinationRecordRepository.save(record);
    }

    @Override
    public List<VaccinationRecord> getAllVaccinationRecords() {
        return List.of();
    }
}
