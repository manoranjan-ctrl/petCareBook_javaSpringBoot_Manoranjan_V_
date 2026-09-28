package com.PetCareBook.system.services.impl;

import com.PetCareBook.system.model.VaccinationRecord;
import com.PetCareBook.system.repository.VaccinationRecordRepository;
import com.PetCareBook.system.services.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationRecordImpl implements VaccinationRecordService {
    @Autowired
    VaccinationRecordRepository vaccinationRecordRepository;


    @Override
    public VaccinationRecord addVaccinationRecord(VaccinationRecord vaccinationRecord) {
        int interval = vaccinationRecord.getVaccineType().getIntervalDays();


        return vaccinationRecordRepository.save(vaccinationRecord);
    }
}
